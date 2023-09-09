package dev.techullurgy.domain.services

import dev.techullurgy.domain.models.User

interface UserService {
    suspend fun getUserByUserId(userId: Long): User?
    suspend fun getUserByUserName(userName: String): User?
    suspend fun getUserByEmailId(emailId: String): User?
    suspend fun registerUser(username: String, password: String, email: String): Boolean
    suspend fun updateLastLogin(user: User): Boolean
    suspend fun isUserExists(userName: String, password: String): User?

    suspend fun blockUser(blockedBy: User, blockedUser: User): Boolean

    suspend fun getBlockedUsersForUser(user: User): List<User>?

    suspend fun unBlockUser(blockedBy: User, blockedUser: User): Boolean

    suspend fun isBlockedChannel(blockedBy: User, blockedUser: User): Boolean

    suspend fun canReceiveTypingEvent(typingFromId: Long, typingToId: Long): Boolean
}