package com.innercirclesoftware.food

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Validated
interface FoodOperations {

    @Post
    @Status(HttpStatus.CREATED)
    fun saveFood(@Body food: FoodDto): Single<FoodDto>

    @Get(uri = "/{fdcId}")
    fun getFood(fdcId: Int): Maybe<FoodDto>

    @Post("/{fdcId}/nutrients")
    @Status(HttpStatus.CREATED)
    fun saveNutrients(fdcId: Int, @Body nutrients: List<FoodNutrientDto>): Single<List<Int>>

    @Get("/{fdcId}/nutrients")
    fun getNutrients(fdcId: Int): Flowable<FoodNutrientDto>

    @Delete("/{fdcId}/nutrients")
    fun deleteNutrients(fdcId: Int, @Body nutrientIds: Set<Int>): Completable

}