package com.techullurgy.plugins

import com.techullurgy.data.model.database.User
import com.techullurgy.data.repository.UserDetailsRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    val userDetailsRepository: UserDetailsRepository? = repositories?.userDetailsRepository
    userDetailsRepository?.let {
        install(Authentication) {
            basicAuthentication("basic_auth", it)
        }
    }
}

private fun AuthenticationConfig.basicAuthentication(
    authName: String? = null,
    userDetailsRepository: UserDetailsRepository
) {
    basic(authName) {
        validate { credentials ->
            val name = credentials.name
            val password = credentials.password
            val user = userDetailsRepository.isUserExists(userName = name, password = password)
            user?.let { UserPrincipal(it) }
        }
    }
}

data class UserPrincipal(val user: User): Principal