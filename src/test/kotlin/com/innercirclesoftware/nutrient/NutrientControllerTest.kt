package com.innercirclesoftware.nutrient

import com.innercirclesoftware.utils.get
import com.innercirclesoftware.utils.post
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class NutrientControllerTest {

    @Inject
    @field:Client("/nutrients")
    private lateinit var client: RxHttpClient

    @Test
    internal fun `test findById returns Nutrient when exists`() {
        val nutrient = testNutrient()
        client.post<Any, Nutrient>(body = nutrient)
                .test()
                .await()
                .assertComplete()

        client.get<Nutrient>("/${nutrient.id}")
                .test()
                .await()
                .assertValue(nutrient)
    }

    @Test
    internal fun `test findById returns NotFound when does not exist`() {
        client.get<Any>("/1")
                .test()
                .await()
                .assertError { error ->
                    (error as HttpClientResponseException).status == HttpStatus.NOT_FOUND
                }
    }
}