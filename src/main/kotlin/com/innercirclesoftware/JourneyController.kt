package com.innercirclesoftware

import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.annotation.*
//import io.reactiverse.reactivex.pgclient.PgPool
import io.reactivex.Single
import javax.inject.Inject

@Controller("/journeys")
class JourneyController @Inject constructor(/*private val pool: PgPool*/) {

    @Post
    @Consumes(TEXT_PLAIN)
    @Produces(TEXT_PLAIN)
    fun create(requestBody: String) = "Hello $requestBody"

    @Get(uri = "/hello/{who}", produces = [TEXT_PLAIN])
    fun get(who: String): String = "Hello $who"

//    @Get(uri = "/test", produces = [TEXT_PLAIN], consumes = [TEXT_PLAIN])
//    fun test(): Single<String> = pool.rxQuery("SELECT version();").map { pgRowSet -> pgRowSet.joinToString() }


}