package com.techullurgy.data.repository

import com.techullurgy.data.entities.UserDetails
import com.techullurgy.data.entities.userDetails
import com.techullurgy.data.model.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.find
import java.time.LocalDateTime

class UserDetailsRepositoryImpl(
    private val database: Database
): UserDetailsRepository {
    override suspend fun getUserByUserId(userId: Long): User?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.id eq userId }?.toUser() }

    override suspend fun getUserByUserName(userName: String): User?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.name eq userName }?.toUser() }

    override suspend fun getUserByEmailId(emailId: String): User?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.email eq emailId }?.toUser() }

    override suspend fun insertUser(user: User): Boolean = withContext(Dispatchers.IO) {
        val count = database.insert(UserDetails) {
            set(it.name, user.name)
            set(it.email, user.email)
            set(it.password, user.password)
            set(it.registeredAt, LocalDateTime.now())
            set(it.lastLogin, null)
        }
        count == 1
    }

    override suspend fun updateLastLogin(user: User): Boolean = withContext(Dispatchers.IO) {
        val count = database.update(UserDetails) {
            set(it.lastLogin, LocalDateTime.now())
            where { it.id eq user.id }
        }
        count == 1
    }

    override suspend fun isUserExists(userName: String, password: String): User?
            = withContext(Dispatchers.IO) { database.userDetails.find { (it.name eq userName) and (it.password eq password) } }?.toUser()
}