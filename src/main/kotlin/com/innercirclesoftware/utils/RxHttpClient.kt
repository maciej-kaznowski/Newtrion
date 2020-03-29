package com.innercirclesoftware.utils

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable

inline fun <In, reified Out> RxHttpClient.post(uri: String = "", body: In): Flowable<Out> {
    return retrieve(HttpRequest.POST(uri, body), Out::class.java)
}

inline fun <reified Out> RxHttpClient.get(uri: String = ""): Flowable<Out> {
    return retrieve(HttpRequest.GET<Any>(uri), Out::class.java)
}

inline fun <reified Out> RxHttpClient.getSetOf(uri: String = ""): Flowable<Set<Out>> {
    return retrieve(HttpRequest.GET<Any>(uri), Argument.setOf(Out::class.java))
}