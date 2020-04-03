package com.innercirclesoftware.food

import com.innercirclesoftware.food.food_nutrient.FoodNutrient

fun Food.toApi() = FoodDto(
        fdcId = fdcId,
        description = description,
        foodCategoryId = foodCategoryId
)

fun FoodDto.toModel() = Food(
        fdcId = fdcId,
        dataType = DataTypes.UNKNOWN, //TODO DataType
        description = description,
        foodCategoryId = foodCategoryId
)

fun FoodNutrient.toApi() = FoodNutrientDto(
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

fun FoodNutrientDto.toModel() = FoodNutrient(
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