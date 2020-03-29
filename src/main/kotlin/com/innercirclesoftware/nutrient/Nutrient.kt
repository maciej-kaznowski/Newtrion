package com.innercirclesoftware.nutrient

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "nutrient")
data class Nutrient(
        @Id @Column(name = "id") val id: Int,
        @Column(name = "name") val name: String,
        @Column(name = "unit_name") val unitName: String,
        @Column(name = "nutrient_nbr") val nutrientNbr: Double,
        @Column(name = "rank") val rank: String
) {

    constructor() : this(
            id = 0,
            name = "",
            unitName = "",
            nutrientNbr = 0.0,
            rank = ""
    )
}

@Repository
interface NutrientRepository : CrudRepository<Nutrient, Int>