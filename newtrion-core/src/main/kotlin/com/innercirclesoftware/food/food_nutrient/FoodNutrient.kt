package main.kotlin.com.innercirclesoftware.food.food_nutrient

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "food_nutrient")
data class FoodNutrient(
        @Id @Column(name = "id") val id: Int,
        @Column(name = "fdc_id") val fdcId: Int,
        @Column(name = "nutrient_id") val nutrientId: Int,
        @Column(name = "amount") val amount: Double,
        @Column(name = "data_points") val dataPoints: String?,
        @Column(name = "derivation_id") val derivationId: Int?,
        @Column(name = "min") val min: Double?,
        @Column(name = "max") val max: Double?,
        @Column(name = "median") val median: Double?,
        @Column(name = "footnote") val footnote: String?,
        @Column(name = "min_year_acquired") val minYearAcquired: Int?
) {

    constructor() : this(
            id = 0,
            fdcId = 0,
            nutrientId = 0,
            amount = 0.0,
            dataPoints = null,
            derivationId = null,
            min = null,
            max = null,
            median = null,
            footnote = null,
            minYearAcquired = null
    )
}

@Repository
interface FoodNutrientRepository : CrudRepository<FoodNutrient, Int>