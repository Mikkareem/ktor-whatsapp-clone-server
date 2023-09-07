package com.techullurgy.plugins

import com.techullurgy.exceptions.genericExceptionHandler
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        genericExceptionHandler()
    }
    routing {
    }
}
