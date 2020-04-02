package com.innercirclesoftware.food

data class FoodDto(
        val fdcId: Int,
        //val dataType: DataType, TODO
        val description: String,
        val foodCategoryId: String?
)