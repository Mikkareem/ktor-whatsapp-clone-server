package dev.techullurgy.data.model.database.servables

data class BlockingDTO(
    val blockedBy: UserDTO,
    val blockedUser: UserDTO
)