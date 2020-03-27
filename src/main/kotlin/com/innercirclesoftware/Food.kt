package com.innercirclesoftware

import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.PageableRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "food")
data class Food(
        @Id @Column(name = "fdc_id", unique = true, updatable = false) var fdcId: Int,
        @Column(name = "data_type") var dataType: String,
        @Column(name = "description") var description: String,
        @Column(name = "food_category_id") var foodCategoryId: String?
) {
    constructor() : this(fdcId = 0, dataType = "", description = "", foodCategoryId = null)
}

@Repository
interface FoodRepository : PageableRepository<Food, Int>

@Controller("/foods")
class FoodController @Inject constructor(private val foodRepository: FoodRepository) {

    @Post
    fun findAll(@Body pageable: Pageable): Page<Food> = foodRepository.findAll(pageable)

    @Get(uri = "/{id}")
    fun get(id: Int = 356430): Food? = foodRepository.findById(id).orElse(null)

}