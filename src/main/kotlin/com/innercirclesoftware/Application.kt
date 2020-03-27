package com.innercirclesoftware

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server

@OpenAPIDefinition(
        servers = [Server(url = "http://localhost:8080"
        )],
        info = Info(
                title = "Newtrion",
//                version = "0.0",
                description = "Newtrion micronaut server"
        )
)

/*
@OpenAPIDefinition(
        info = Info(
                title = "Hello World",
                version = "0.0",
                description = "My API",
                license = License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
        )
)
*/
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.innercirclesoftware")
                .mainClass(Application.javaClass)
                .start()
    }
}