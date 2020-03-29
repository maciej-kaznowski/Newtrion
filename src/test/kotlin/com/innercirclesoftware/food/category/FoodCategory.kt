package com.innercirclesoftware.food.category

import com.innercirclesoftware.randoms.Randoms

fun testFoodCategory(
        id: Int = Randoms.randomInt().invoke(),
        code: Int = Randoms.randomInt().invoke(),
        description: String = Randoms.randomString().invoke()
) = FoodCategory(id = id, code = code, description = description)