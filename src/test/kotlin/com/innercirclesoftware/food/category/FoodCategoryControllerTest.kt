package com.innercirclesoftware.food.category

import com.innercirclesoftware.randoms.setOfRandomSize
import com.innercirclesoftware.utils.getSetOf
import com.innercirclesoftware.utils.post
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class FoodCategoryControllerTest {

    @Inject
    @field:Client("/foods/categories")
    private lateinit var client: RxHttpClient

    @Inject
    private lateinit var foodCategoryRepository: FoodCategoryRepository

    private val categories = setOfRandomSize { testFoodCategory() }.invoke()

    @BeforeEach
    internal fun setUp() = foodCategoryRepository.deleteAll()

    @Test
    internal fun `test GET returns all`() {
        foodCategoryRepository.saveAll(categories)
        foodCategoryRepository.saveAll(emptyList())
        foodCategoryRepository.findAll().toSet<FoodCategory>() shouldNotBe emptySet<FoodCategory>()

        client.getSetOf<FoodCategory>().test().await()
                .assertValue(categories)
    }

    @Test
    internal fun `test POST creates categories`() {
        foodCategoryRepository.findAll() shouldNotBe categories

        client.post<Set<FoodCategory>, Any>(body = categories).test().await()
                .assertComplete()

        foodCategoryRepository.findAll().toSet() shouldBe categories
    }
}