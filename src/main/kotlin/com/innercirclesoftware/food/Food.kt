package com.innercirclesoftware.food

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

    val ALL = listOf(
            BRANDED_FOOD,
            SUB_SAMPLE_FOOD,
            SURVEY_FNDDS_FOOD,
            SR_LEGACY_FOOD,
            MARKET_ACQUISITION,
            SAMPLE_FOOD,
            AGRICULTURE_ACQUISITION,
            FOUNDATION_FOOD
    )

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