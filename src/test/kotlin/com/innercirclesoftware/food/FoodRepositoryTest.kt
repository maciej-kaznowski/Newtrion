package com.innercirclesoftware.food

import io.kotlintest.shouldBe
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class FoodRepositoryTest {

    @Inject
    private lateinit var foodRepository: FoodRepository

    @Test
    fun testCount() {
        foodRepository.apply {
            count() shouldBe 0L

            save(testFood())
            count() shouldBe 1L

            deleteAll()
            count() shouldBe 0L
        }
    }

    @Test
    fun testFindById() {
        val expected = testFood()
        foodRepository.save(expected)

        val actual: Food? = foodRepository.findById(expected.fdcId).orElse(null)
        actual shouldBe expected
    }
}