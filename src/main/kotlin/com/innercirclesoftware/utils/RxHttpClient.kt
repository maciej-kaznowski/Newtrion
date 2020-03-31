package com.innercirclesoftware.utils

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable


//POST
inline fun <In, reified Out> RxHttpClient.post(uri: String = "", body: In): Flowable<Out> {
    return retrieve(HttpRequest.POST(uri, body), Out::class.java)
}

inline fun <In> RxHttpClient.postForResponse(uri: String = "", body: In): Flowable<HttpResponse<Any>> {
    return exchange(HttpRequest.POST(uri, body), Any::class.java)
}


//GET
inline fun <reified Out> RxHttpClient.get(uri: String = ""): Flowable<Out> {
    return retrieve(HttpRequest.GET<Any>(uri), Out::class.java)
}

inline fun <reified Out> RxHttpClient.getSetOf(uri: String = ""): Flowable<Set<Out>> {
    return retrieve(HttpRequest.GET<Any>(uri), Argument.setOf(Out::class.java))
}

//DELETE
inline fun <In, reified Out> RxHttpClient.delete(uri: String = "", body: In): Flowable<Out> {
    return retrieve(HttpRequest.DELETE(uri, body), Out::class.java)
}

inline fun <In, reified Out> RxHttpClient.deleteForResponse(uri: String = "", body: In): Flowable<HttpResponse<Out>> {
    return exchange(HttpRequest.DELETE(uri, body), Out::class.java)
}