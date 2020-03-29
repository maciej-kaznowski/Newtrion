package com.innercirclesoftware.utils

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable

inline fun <In, reified Out> RxHttpClient.retrieve(request: HttpRequest<In>): Flowable<Out> {
    return retrieve(request, Out::class.java)
}