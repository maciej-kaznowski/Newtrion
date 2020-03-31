package com.innercirclesoftware.food

import com.innercirclesoftware.food.food_nutrient.FoodNutrient
import com.innercirclesoftware.food.food_nutrient.FoodNutrientRepository
import com.innercirclesoftware.food.food_nutrient.testFoodNutrient
import com.innercirclesoftware.randoms.Randoms
import com.innercirclesoftware.randoms.listOfRandomSize
import com.innercirclesoftware.utils.deleteForResponse
import com.innercirclesoftware.utils.getSetOf
import com.innercirclesoftware.utils.postForResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class FoodControllerTest {

    @Inject
    @field:Client("/foods")
    private lateinit var client: RxHttpClient

    @Inject
    private lateinit var foodRepository: FoodRepository

    @Inject
    private lateinit var foodNutrientRepository: FoodNutrientRepository

    @BeforeEach
    internal fun setUp() {
        foodRepository.deleteAll()
        foodNutrientRepository.deleteAll()

    }

    @Test
    internal fun `test saveFood() returns 201 created`() {
        val food = testFood()
        client.postForResponse(body = food).test().await()
                .assertValue { response -> response.status == HttpStatus.CREATED }
                .assertComplete()
    }

    @Test
    internal fun `test saveNutrients() returns 201 created`() {
        val food = testFood()
        client.postForResponse(body = food).test().await()
                .assertComplete()

        val nutrients: List<FoodNutrient> = listOfRandomSize(min = 2, maxInclusive = 2) { index -> testFoodNutrient(id = index) }.invoke()
        client.postForResponse(uri = "/${food.fdcId}/nutrients", body = nutrients).test().await()
                .assertNoErrors()
                .assertValue { response -> response.status == HttpStatus.CREATED }
                .assertComplete()
    }

    @Test
    internal fun `test getNutrients() for existing Food returns all nutrients`() {
        val food = testFood()
        val nutrients = listOfRandomSize(min = 1) { index -> testFoodNutrient(id = index) }.invoke()

        client.postForResponse(body = food).test().await()
                .assertComplete()

        client.postForResponse(uri = "/${food.fdcId}/nutrients", body = nutrients).test().await()
                .assertComplete()

        client.getSetOf<FoodNutrient>("/${food.fdcId}/nutrients").test().await()
                .assertValue { createdNutrients ->
                    createdNutrients.size == nutrients.size
                }
                .assertComplete()
    }

    @Test
    fun `test deleteNutrients() for existing Food`() {
        val food1 = testFood()
        foodRepository.save(food1)
        val food1Nutrients = listOfRandomSize { index -> testFoodNutrient(id = index, fdcId = food1.fdcId) }.invoke()
        foodNutrientRepository.saveAll(food1Nutrients)

        val food2 = testFood(fdcId = Randoms.randomInt(min = food1.fdcId + 1).invoke())
        foodRepository.save(food2)
        val food2Nutrients: List<FoodNutrient> = listOfRandomSize(min = 1) { index ->
            testFoodNutrient(id = (food1Nutrients.map { it.id }.max() ?: 0) + index + 1)
        }.invoke()
        foodNutrientRepository.saveAll(food2Nutrients)

        client.deleteForResponse<List<Int>, Void>(uri = "/${food2.fdcId}/nutrients", body = food2Nutrients.map { it.id }).test().await()
                .assertComplete()
    }
}