package com.innercirclesoftware.food.category

import io.kotlintest.shouldBe
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class FoodCategoryRepositoryTest {

    @Inject
    private lateinit var foodCategoryRepository: FoodCategoryRepository

    @BeforeEach
    internal fun setUp() = foodCategoryRepository.deleteAll()

    @Test
    internal fun `test findAll()`() {
        with(foodCategoryRepository) {
            findAll() shouldBe emptyList()

            val expected = testFoodCategory()
            save(expected)

            findAll() shouldBe listOf(expected)
        }
    }
}