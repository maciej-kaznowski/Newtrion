package com.innercirclesoftware.food

data class FoodNutrientDto(
        val id: Int = 0,
        val fdcId: Int = 0,
        val nutrientId: Int = 0,
        val amount: Double = 0.0,
        val dataPoints: String? = null,
        val derivationId: Int? = null,
        val min: Double? = null,
        val max: Double? = null,
        val median: Double? = null,
        val footnote: String? = null,
        val minYearAcquired: Int? = null
)
