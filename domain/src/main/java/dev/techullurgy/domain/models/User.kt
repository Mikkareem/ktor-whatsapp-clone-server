package dev.techullurgy.domain.models

import dev.techullurgy.data.model.database.servables.UserDTO

data class User(
    val id: Long,
    val name: String,
    val email: String
)

internal fun UserDTO.toUser(): User = User(id = id, name = name, email = email)
internal fun User.toUserDTO(): UserDTO = UserDTO(id = id, name = name, email = email)