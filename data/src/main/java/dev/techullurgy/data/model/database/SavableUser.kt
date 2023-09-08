package dev.techullurgy.data.model.database

import java.time.LocalDateTime

data class SavableUser(
    val name: String,
    val email: String,
    val password: String,
    val registeredAt: LocalDateTime = LocalDateTime.now(),
    val lastLogin: LocalDateTime? = null
)