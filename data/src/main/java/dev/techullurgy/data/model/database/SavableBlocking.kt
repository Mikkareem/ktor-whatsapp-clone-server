package dev.techullurgy.data.model.database

import java.time.LocalDateTime

data class SavableBlocking(
    val blockId: Long,
    val blockedBy: User,
    val blockedUser: User,
    val blockedTime: LocalDateTime,
    val isActive: Boolean = true
)
