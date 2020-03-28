package com.innercirclesoftware.food

import com.innercirclesoftware.randoms.Randoms.randomInt
import com.innercirclesoftware.randoms.Randoms.randomString
import com.innercirclesoftware.randoms.nullable


fun testFood(
        fdcId: Int = randomInt().invoke(),
        dataType: DataType = testDataType(),
        description: String = randomString().invoke(),
        foodCategoryId: String? = randomString().nullable().invoke()
): Food = Food(
        fdcId = fdcId,
        dataType = dataType,
        description = description,
        foodCategoryId = foodCategoryId
)

fun testDataType(): DataType = DataTypes.ALL.random()