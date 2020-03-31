package com.innercirclesoftware.food.food_nutrient

import com.innercirclesoftware.randoms.Randoms.randomDouble
import com.innercirclesoftware.randoms.Randoms.randomInt
import com.innercirclesoftware.randoms.Randoms.randomString
import com.innercirclesoftware.randoms.nullable

fun testFoodNutrient(
        id: Int = randomInt().invoke(),
        fdcId: Int = randomInt().invoke(),
        nutrientId: Int = randomInt().invoke(),
        amount: Double = randomDouble().invoke(),
        dataPoints: String? = randomString().nullable().invoke(),
        derivationId: Int? = randomInt().nullable().invoke(),
        min: Double? = randomDouble().nullable().invoke(),
        max: Double? = randomDouble().nullable().invoke(),
        median: Double? = randomDouble().nullable().invoke(),
        footnote: String? = randomString().nullable().invoke(),
        minYearAcquired: Int? = randomInt().nullable().invoke()
) = FoodNutrient(
        id = id,
        fdcId = fdcId,
        nutrientId = nutrientId,
        amount = amount,
        dataPoints = dataPoints,
        derivationId = derivationId,
        min = min,
        max = max,
        median = median,
        footnote = footnote,
        minYearAcquired = minYearAcquired
)