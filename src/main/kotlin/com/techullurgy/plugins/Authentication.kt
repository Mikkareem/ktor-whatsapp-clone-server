package com.techullurgy.plugins

import com.techullurgy.data.model.database.User
import com.techullurgy.data.repository.UserDetailsRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication(userDetailsRepository: UserDetailsRepository) {
    install(Authentication) {
        basicAuthentication("basic_auth", userDetailsRepository)
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
} // docker run -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -p 3306:3306 mysql

data class UserPrincipal(val user: User): Principal