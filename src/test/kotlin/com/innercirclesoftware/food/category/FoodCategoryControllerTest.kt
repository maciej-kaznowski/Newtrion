package com.innercirclesoftware.food.category

import com.innercirclesoftware.randoms.setOfRandomSize
import com.innercirclesoftware.utils.getSetOf
import com.innercirclesoftware.utils.post
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

    private val categories: Set<FoodCategory> = setOfRandomSize { testFoodCategory() }.invoke()

    @BeforeEach
    internal fun setUp() {
        client.post<Set<FoodCategory>, Any>("", categories)
                .test()
                .await()
                .assertComplete()
    }

    @Test
    internal fun `test GET returns all`() {
        client.getSetOf<FoodCategory>()
                .test()
                .await()
                .assertValue(categories)
    }
}