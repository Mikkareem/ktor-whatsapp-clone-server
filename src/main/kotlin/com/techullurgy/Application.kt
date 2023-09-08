package com.techullurgy

import com.techullurgy.plugins.configureDatabases
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("UNUSED")
fun Application.module() {
    configureDatabases()
//    configureAuthentication()
//    configureSockets()
//    configureSerialization()
//    configureSecurity()
//    configureRouting()
}
