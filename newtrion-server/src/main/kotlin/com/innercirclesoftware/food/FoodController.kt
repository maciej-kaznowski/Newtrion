package com.innercirclesoftware.food

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

@Controller("/foods")
class FoodController : FoodOperations {

    @Inject
    private lateinit var foodService: FoodService

    //TODO conflicts with saveFood()
//    @Post
//    fun findAll(@Body pageable: Pageable): Page<Food> = foodRepository.findAll(pageable)

    override fun saveFood(@Body food: FoodDto): Single<FoodDto> {
        return Single.fromCallable { food.toModel() }
                .flatMap { foodModel -> foodService.save(foodModel) }
                .map { foodModel -> foodModel.toApi() }
    }

    override fun getFood(fdcId: Int): Maybe<FoodDto> {
        return foodService.findById(fdcId)
                .map { foodModel -> foodModel.toApi() }
    }

    override fun saveNutrients(fdcId: Int, nutrients: List<FoodNutrientDto>): Single<List<Int>> {
        return nutrients.toObservable()
                .map { dto -> dto.toModel() }
                .toList()
                .flatMap { nutrientModels -> foodService.saveAllNutrients(fdcId, nutrientModels).toList() }
                .flatMapObservable { list -> list.toObservable() }
                .map { nutrient -> nutrient.id }
                .toList()
    }

    override fun getNutrients(fdcId: Int): Flowable<FoodNutrientDto> {
        return foodService.findAllNutrients(fdcId).map { it.toApi() }
    }

    override fun deleteNutrients(fdcId: Int, nutrientIds: Set<Int>): Completable {
        return foodService.deleteAllNutrients(fdcId, nutrientIds)
    }
}