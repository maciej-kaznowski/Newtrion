package com.innercirclesoftware.food.category

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import javax.inject.Inject

@Controller("/foods/categories")
class FoodCategoryController {

    @Inject
    private lateinit var foodCategoryRepository: FoodCategoryRepository

    @Post
    fun saveAll(@Body entities: List<FoodCategory>): Single<List<Int>> {
        return Single.fromCallable { foodCategoryRepository.saveAll(entities) }
                .map { saved -> saved.map { category -> category.id } }
    }

    @Get
    fun getAll(): List<FoodCategory> = foodCategoryRepository.findAll().toList()

}