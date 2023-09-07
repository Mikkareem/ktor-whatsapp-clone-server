package com.techullurgy.data.model.database

data class User(
    val id: Long = 0,
    val name: String,
    val email: String,
    val password: String
)
