package dev.techullurgy.data.model.database.servables

import dev.techullurgy.data.entities.UserDetail

data class UserDTO(
    val id: Long = 0,
    val name: String,
    val email: String
)

internal fun UserDetail.toUserDTO(): UserDTO =
    UserDTO(
        id = this.id,
        name = this.name,
        email = this.email
    )