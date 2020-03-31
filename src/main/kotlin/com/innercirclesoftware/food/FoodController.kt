package com.innercirclesoftware.food

import com.innercirclesoftware.food.food_nutrient.FoodNutrient
import com.innercirclesoftware.food.food_nutrient.FoodNutrientRepository
import com.innercirclesoftware.utils.toMaybe
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.toFlowable
import javax.inject.Inject

@Controller("/foods")
class FoodController {

    @Inject
    private lateinit var foodRepository: FoodRepository

    @Inject
    private lateinit var foodNutrientRepository: FoodNutrientRepository

    //TODO conflicts with saveFood()
//    @Post
//    fun findAll(@Body pageable: Pageable): Page<Food> = foodRepository.findAll(pageable)

    @Post
    @Status(HttpStatus.CREATED)
    fun saveFood(@Body food: Food) = foodRepository.save(food)

    @Get(uri = "/{fdcId}")
    fun getFood(fdcId: Int) = foodRepository.findById(fdcId).toMaybe()

    @Post("/{fdcId}/nutrients")
    @Status(HttpStatus.CREATED)
    fun saveNutrients(fdcId: Int, @Body nutrients: Set<FoodNutrient>) {
        val toSave = nutrients.map { nutrient -> nutrient.copy(fdcId = fdcId) }
        foodNutrientRepository.saveAll(toSave)
    }

    @Get("/{fdcId}/nutrients")
    fun getNutrients(fdcId: Int): Flowable<FoodNutrient> {
        return foodNutrientRepository.findAll().toFlowable().filter { foodNutrient -> foodNutrient.fdcId == fdcId }
    }

    @Delete("/{fdcId}/nutrients")
    fun deleteNutrients(fdcId: Int, @Body nutrientIds: Set<Int>): Completable {
        return foodNutrientRepository.findAll().toFlowable()
                .filter { foodNutrient -> foodNutrient.id in nutrientIds && foodNutrient.fdcId == fdcId }
                .buffer(1000)
                .doOnNext { foodNutrients -> foodNutrientRepository.deleteAll(foodNutrients) }
                .ignoreElements()
    }
}