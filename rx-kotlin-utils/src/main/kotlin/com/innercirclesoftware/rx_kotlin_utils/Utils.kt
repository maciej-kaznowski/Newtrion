package com.innercirclesoftware.rx_kotlin_utils

import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

fun <T : Any> Single<Optional<T>>.flatMapMaybe(): Maybe<T> {
    return this.flatMapMaybe { it.toMaybe() }
}

private fun <T> Optional<T>.toMaybe(): Maybe<T> {
    val value = this.orElse(null)
    return if (value != null) Maybe.just(value) else Maybe.empty()
}