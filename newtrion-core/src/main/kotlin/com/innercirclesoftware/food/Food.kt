package com.innercirclesoftware.food

import com.innercirclesoftware.food.food_nutrient.FoodNutrient
import com.innercirclesoftware.food.food_nutrient.FoodNutrientRepository
import com.innercirclesoftware.rx_kotlin_utils.flatMapMaybe
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

inline class DataType(val value: String)

object DataTypes {


    val BRANDED_FOOD = DataType("branded_food")
    val SUB_SAMPLE_FOOD = DataType("sub_sample_food")
    val SURVEY_FNDDS_FOOD = DataType("survey_fndds_food")
    val SR_LEGACY_FOOD = DataType("sr_legacy_food")
    val MARKET_ACQUISITION = DataType("market_acquisition")
    val SAMPLE_FOOD = DataType("sample_food")
    val AGRICULTURE_ACQUISITION = DataType("agriculture_acquisition")
    val FOUNDATION_FOOD = DataType("foundation_food")

    //should not appear in DB, forced to define for no-arg constructor
    val UNKNOWN = DataType("unknown")

    val ALL = listOf(
            BRANDED_FOOD,
            SUB_SAMPLE_FOOD,
            SURVEY_FNDDS_FOOD,
            SR_LEGACY_FOOD,
            MARKET_ACQUISITION,
            SAMPLE_FOOD,
            AGRICULTURE_ACQUISITION,
            FOUNDATION_FOOD
    )

}

@Entity
@Table(name = "food")
data class Food(
        @Id @Column(name = "fdc_id", unique = true, updatable = false) var fdcId: Int,
        @Column(name = "data_type") var dataType: DataType,
        @Column(name = "description") var description: String,
        @Column(name = "food_category_id") var foodCategoryId: String?
) {
    constructor() : this(
            fdcId = 0,
            dataType = DataTypes.UNKNOWN,
            description = "",
            foodCategoryId = null
    )
}

@Repository
internal interface FoodRepository : PageableRepository<Food, Int>

interface FoodService {

    fun save(food: Food): Single<Food>
    fun findById(fdcId: Int): Maybe<Food>

    fun saveAllNutrients(fdcId: Int, nutrients: List<FoodNutrient>): Flowable<FoodNutrient>
    fun findAllNutrients(fdcId: Int): Flowable<FoodNutrient>
    fun deleteAllNutrients(fdcId: Int, nutrientIds: Set<Int>): Completable

}

@Singleton
class FoodServiceImpl : FoodService {

    @Inject
    private lateinit var foodRepository: FoodRepository

    @Inject
    private lateinit var foodNutrientRepository: FoodNutrientRepository

    override fun save(food: Food): Single<Food> {
        return Single.fromCallable { foodRepository.save(food) }
    }

    override fun findById(fdcId: Int): Maybe<Food> {
        return Single.fromCallable { foodRepository.findById(fdcId) }.flatMapMaybe()
    }

    override fun saveAllNutrients(fdcId: Int, nutrients: List<FoodNutrient>): Flowable<FoodNutrient> {
        return nutrients.toObservable()
                .map { nutrient -> nutrient.copy(fdcId = fdcId) }
                .toList()
                .map { nutrientsWithFdcId -> foodNutrientRepository.saveAll(nutrientsWithFdcId).toList() }
                .flattenAsFlowable { it }
    }

    override fun findAllNutrients(fdcId: Int): Flowable<FoodNutrient> {
        return foodNutrientRepository.findAll().toFlowable().filter { foodNutrient -> foodNutrient.fdcId == fdcId }
    }

    override fun deleteAllNutrients(fdcId: Int, nutrientIds: Set<Int>): Completable {
        return foodNutrientRepository.findAll().toFlowable()
                .filter { foodNutrient -> foodNutrient.id in nutrientIds && foodNutrient.fdcId == fdcId }
                .buffer(1000)
                .doOnNext { foodNutrients -> foodNutrientRepository.deleteAll(foodNutrients) }
                .ignoreElements()

    }
}