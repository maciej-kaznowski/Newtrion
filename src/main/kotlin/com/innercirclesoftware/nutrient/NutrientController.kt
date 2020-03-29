package com.innercirclesoftware.nutrient

import com.innercirclesoftware.utils.toMaybe
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.reactivex.Maybe
import javax.inject.Inject

@Controller("/nutrients")
class NutrientController @Inject constructor(private val nutrientRepository: NutrientRepository) {

    @Post
    fun createNutrient(@Body nutrient: Nutrient): Nutrient {
        return nutrientRepository.save(nutrient)
    }

    @Get("/{id}")
    fun getNutrientForId(id: Int): Maybe<Nutrient> {
        return nutrientRepository.findById(id).toMaybe()
    }

}