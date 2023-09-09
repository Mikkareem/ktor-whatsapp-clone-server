package com.techullurgy.plugins

import dev.techullurgy.DBConfiguration
import dev.techullurgy.initServices
import io.ktor.server.application.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun Application.configureDatabases() {
    val urlProperty = environment.config.propertyOrNull("db.url")
    val userProperty = environment.config.propertyOrNull("db.username")
    val passwordProperty = environment.config.propertyOrNull("db.password")

    if(urlProperty == null || userProperty == null || passwordProperty == null) {
        throw RuntimeException(
            message = "Please provide all(db.url, db.username, db.password) properties in application.conf to setup database."
        )
    }

    val url = urlProperty.getString()
    val user = userProperty.getString()
    val password = passwordProperty.getString()

    val dbConfiguration = DBConfiguration(url = url, username = user, password = password)

    val deferredJob = async {
        initServices(dbConfiguration)
    }

    runBlocking { deferredJob.await() }
}