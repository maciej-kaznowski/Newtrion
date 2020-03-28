package com.innercirclesoftware.food

import com.innercirclesoftware.utils.flatMapMaybe
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

@Controller("/foods")
class FoodController @Inject constructor(private val foodRepository: FoodRepository) {

    @Post
    fun findAll(@Body pageable: Pageable): Page<Food> = foodRepository.findAll(pageable)

    @Get(uri = "/{id}")
    fun get(id: Int = 356430): Maybe<Food> {
        return Single.fromCallable { foodRepository.findById(id) }.flatMapMaybe()
    }
}