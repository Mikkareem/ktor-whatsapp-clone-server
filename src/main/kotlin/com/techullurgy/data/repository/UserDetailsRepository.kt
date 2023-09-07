package com.techullurgy.data.repository

import com.techullurgy.data.model.database.User

interface UserDetailsRepository {
    suspend fun getUserByUserId(userId: Long): User?
    suspend fun getUserByUserName(userName: String): User?
    suspend fun getUserByEmailId(emailId: String): User?
    suspend fun insertUser(user: User): Boolean
    suspend fun updateLastLogin(user: User): Boolean
    suspend fun isUserExists(userName: String, password: String): User?
}