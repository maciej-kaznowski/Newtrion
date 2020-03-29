package com.innercirclesoftware.nutrient

import io.kotlintest.shouldBe
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class NutrientRepositoryTest {

    @Inject
    private lateinit var nutrientRepository: NutrientRepository

    @BeforeEach
    internal fun setUp() = nutrientRepository.deleteAll()

    @Test
    internal fun testFindById() {
        with(nutrientRepository) {
            val inserted = testNutrient()
            save(inserted)

            findById(inserted.id).orElse(null) shouldBe inserted
            findById(inserted.id + 1).isPresent shouldBe false
        }
    }
}