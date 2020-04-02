package com.innercirclesoftware.food

import com.innercirclesoftware.food.food_nutrient.FoodNutrient
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

@Controller("/foods")
class FoodController {

    @Inject
    private lateinit var foodService: FoodService

    //TODO conflicts with saveFood()
//    @Post
//    fun findAll(@Body pageable: Pageable): Page<Food> = foodRepository.findAll(pageable)

    @Post
    @Status(HttpStatus.CREATED)
    fun saveFood(@Body food: Food): Single<Food> = foodService.save(food)

    @Get(uri = "/{fdcId}")
    fun getFood(fdcId: Int): Maybe<Food> = foodService.findById(fdcId)

    @Post("/{fdcId}/nutrients")
    @Status(HttpStatus.CREATED)
    fun saveNutrients(fdcId: Int, @Body nutrients: List<FoodNutrient>): Single<List<Int>> {
        return foodService.saveAllNutrients(fdcId, nutrients).map { nutrient -> nutrient.id }.toList()
    }

    @Get("/{fdcId}/nutrients")
    fun getNutrients(fdcId: Int): Flowable<FoodNutrient> {
        return foodService.findAllNutrients(fdcId)
    }

    @Delete("/{fdcId}/nutrients")
    fun deleteNutrients(fdcId: Int, @Body nutrientIds: Set<Int>): Completable {
        return foodService.deleteAllNutrients(fdcId, nutrientIds)
    }
}