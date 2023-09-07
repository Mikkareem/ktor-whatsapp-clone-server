package com.techullurgy

import com.techullurgy.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("UNUSED")
fun Application.module() {
    val repositories = configureDatabases()

    val userDetailsRepository = repositories.userDetailsRepository

    configureAuthentication(userDetailsRepository)
    configureSockets()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
