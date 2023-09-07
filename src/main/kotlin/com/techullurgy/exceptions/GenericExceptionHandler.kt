package com.techullurgy.exceptions

import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun StatusPagesConfig.genericExceptionHandler() {
    exception<Throwable> { call, cause ->
        call.respondText("500: $cause", status = HttpStatusCode.InternalServerError)
    }
}