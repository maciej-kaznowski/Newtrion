package main.kotlin.com.innercirclesoftware.food

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository

@Repository
interface FoodRepository : PageableRepository<Food, Int>