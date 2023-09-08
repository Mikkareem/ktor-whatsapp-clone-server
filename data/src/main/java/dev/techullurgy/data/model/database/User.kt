package dev.techullurgy.data.model.database

import dev.techullurgy.data.entities.UserDetail

data class User(
    val id: Long = 0,
    val name: String,
    val email: String
)

internal fun UserDetail.toUser(): User =
    User(
        id = this.id,
        name = this.name,
        email = this.email
    )