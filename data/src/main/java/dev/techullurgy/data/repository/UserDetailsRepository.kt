package dev.techullurgy.data.repository

import dev.techullurgy.data.model.database.savables.SavableUser
import dev.techullurgy.data.model.database.servables.UserDTO

interface UserDetailsRepository {
    suspend fun getUserByUserId(userId: Long): UserDTO?
    suspend fun getUserByUserName(userName: String): UserDTO?
    suspend fun getUserByEmailId(emailId: String): UserDTO?
    suspend fun insertUser(user: SavableUser): Boolean
    suspend fun updateLastLogin(user: UserDTO): Boolean
    suspend fun isUserExists(userName: String, password: String): UserDTO?
}