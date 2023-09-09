package dev.techullurgy.data.model.database.savables

import dev.techullurgy.data.model.database.servables.UserDTO
import java.time.LocalDateTime

data class SavableBlocking(
    val blockedBy: UserDTO,
    val blockedUser: UserDTO,
    val blockedTime: LocalDateTime = LocalDateTime.now(),
    val isActive: Boolean = true
)
