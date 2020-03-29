package com.innercirclesoftware.nutrient

import com.innercirclesoftware.randoms.Randoms

fun testNutrient(
        id: Int = Randoms.randomInt().invoke(),
        name: String = Randoms.randomString().invoke(),
        unitName: String = Randoms.randomString().invoke(),
        nutrientNbr: Double = Randoms.randomDouble().invoke(),
        rank: String = Randoms.randomString().invoke()
): Nutrient = Nutrient(
        id = id,
        name = name,
        unitName = unitName,
        nutrientNbr = nutrientNbr,
        rank = rank
)