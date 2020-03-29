package com.innercirclesoftware.nutrient

import com.innercirclesoftware.utils.retrieve
import io.micronaut.http.HttpRequest
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
    @field:Client("/")
    private lateinit var client: RxHttpClient

    @Test
    internal fun `test findById returns Nutrient when exists`() {
        val nutrient = testNutrient()
        client.exchange(HttpRequest.POST("/nutrients", nutrient))
                .test()
                .await()
                .assertComplete()

        client.retrieve<Any, Nutrient>(HttpRequest.GET("/nutrients/${nutrient.id}"))
                .test()
                .await()
                .assertValue(nutrient)
    }

    @Test
    internal fun `test findById returns NotFound when does not exist`() {
        client.exchange("/nutrients/1")
                .test()
                .await()
                .assertError { error ->
                    (error as HttpClientResponseException).status == HttpStatus.NOT_FOUND
                }
    }
}