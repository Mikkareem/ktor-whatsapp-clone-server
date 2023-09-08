package com.techullurgy.plugins

import dev.techullurgy.data.initDB
import io.ktor.server.application.*
import kotlinx.coroutines.launch

fun Application.configureDatabases() {
    val url = environment.config.propertyOrNull("db.url")?.getString() ?: ""
    val user = environment.config.propertyOrNull("db.username")?.getString() ?: "root"
    val password = environment.config.propertyOrNull("db.password")?.getString() ?: ""

    println(url)
    println(password)
    println(user)

    launch {
        initDB(url, user, password)
    }
}