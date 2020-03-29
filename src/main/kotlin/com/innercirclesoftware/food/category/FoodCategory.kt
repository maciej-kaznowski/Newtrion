package com.innercirclesoftware.food.category

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "food_category")
data class FoodCategory(
        @Id @Column(name = "id") val id: Int,
        @Column(name = "code") val code: Int,
        @Column(name = "description") val description: String
) {
    constructor() : this(id = 0, code = 0, description = "")
}

@Repository
interface FoodCategoryRepository : CrudRepository<FoodCategory, Int>