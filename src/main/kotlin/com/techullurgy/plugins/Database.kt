package com.techullurgy.plugins

import com.techullurgy.data.repository.ChatRepository
import com.techullurgy.data.repository.ChatRepositoryImpl
import com.techullurgy.data.repository.UserDetailsRepository
import com.techullurgy.data.repository.UserDetailsRepositoryImpl
import io.ktor.server.application.*
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

fun Application.configureDatabases() {
    val database = Database.connect(
        url = environment.config.propertyOrNull("db.url")?.getString() ?: "",
        driver = environment.config.propertyOrNull("db.driver")?.getString(),
        user = environment.config.propertyOrNull("db.username")?.getString() ?: "root",
        password = environment.config.propertyOrNull("db.password")?.getString() ?: "",
        logger = ConsoleLogger(threshold = LogLevel.DEBUG)
    )

    val userDetailsRepository = UserDetailsRepositoryImpl(database)
    val chatRepository = ChatRepositoryImpl(database)

    repositories = Repositories(
        userDetailsRepository = userDetailsRepository,
        chatRepository = chatRepository
    )
}

data class Repositories(
    val userDetailsRepository: UserDetailsRepository,
    val chatRepository: ChatRepository
)

var repositories: Repositories? = null
    private set