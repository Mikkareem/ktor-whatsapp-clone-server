package dev.techullurgy.data.repository

import dev.techullurgy.data.model.database.SavableUser
import dev.techullurgy.data.model.database.User

interface UserDetailsRepository {
    suspend fun getUserByUserId(userId: Long): User?
    suspend fun getUserByUserName(userName: String): User?
    suspend fun getUserByEmailId(emailId: String): User?
    suspend fun insertUser(user: SavableUser): Boolean
    suspend fun updateLastLogin(user: User): Boolean
    suspend fun isUserExists(userName: String, password: String): User?
}