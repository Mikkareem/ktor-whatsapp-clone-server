package com.techullurgy.plugins


import dev.techullurgy.domain.models.User
import dev.techullurgy.domain.services.UserService
import dev.techullurgy.services
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    val userService: UserService? = services?.userService
    userService?.let {
        install(Authentication) {
            basicAuthentication("basic_auth", it)
        }
    }
}

private fun AuthenticationConfig.basicAuthentication(
    authName: String? = null,
    userService: UserService
) {
    basic(authName) {
        validate { credentials ->
            val name = credentials.name
            val password = credentials.password
            val user = userService.isUserExists(userName = name, password = password)
            user?.let { UserPrincipal(it) }
        }
    }
}

data class UserPrincipal(val user: User): Principal