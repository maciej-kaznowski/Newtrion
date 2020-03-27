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

inline class DataType(val value: String)

object DataTypes {
    val BRANDED_FOOD = DataType("branded_food")
    val SUB_SAMPLE_FOOD = DataType("sub_sample_food")
    val SURVEY_FNDDS_FOOD = DataType("survey_fndds_food")
    val SR_LEGACY_FOOD = DataType("sr_legacy_food")
    val MARKET_ACQUISITION = DataType("market_acquisition")
    val SAMPLE_FOOD = DataType("sample_food")
    val AGRICULTURE_ACQUISITION = DataType("agriculture_acquisition")
    val FOUNDATION_FOOD = DataType("foundation_food")

    //should not appear in DB, forced to define for no-arg constructor
    val UNKNOWN = DataType("unknown")
}

@Entity
@Table(name = "food")
data class Food(
        @Id @Column(name = "fdc_id", unique = true, updatable = false) var fdcId: Int,
        @Column(name = "data_type") var dataType: DataType,
        @Column(name = "description") var description: String,
        @Column(name = "food_category_id") var foodCategoryId: String?
) {
    constructor() : this(
            fdcId = 0,
            dataType = DataTypes.UNKNOWN,
            description = "",
            foodCategoryId = null
    )
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