package dev.techullurgy.data.repository.impl

import dev.techullurgy.data.entities.UserDetails
import dev.techullurgy.data.entities.userDetails
import dev.techullurgy.data.model.database.savables.SavableUser
import dev.techullurgy.data.model.database.servables.UserDTO
import dev.techullurgy.data.model.database.servables.toUserDTO
import dev.techullurgy.data.repository.UserDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.find
import java.time.LocalDateTime

internal class UserDetailsRepositoryImpl(
    private val database: Database
): UserDetailsRepository {
    override suspend fun getUserByUserId(userId: Long): UserDTO?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.id eq userId }?.toUserDTO() }

    override suspend fun getUserByUserName(userName: String): UserDTO?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.name eq userName }?.toUserDTO() }

    override suspend fun getUserByEmailId(emailId: String): UserDTO?
            = withContext(Dispatchers.IO) { database.userDetails.find { it.email eq emailId }?.toUserDTO() }

    override suspend fun insertUser(user: SavableUser): Boolean = withContext(Dispatchers.IO) {
        val count = database.insert(UserDetails) {
            set(it.name, user.name)
            set(it.email, user.email)
            set(it.password, user.password)
            set(it.registeredAt, LocalDateTime.now())
            set(it.lastLogin, null)
        }
        count == 1
    }

    override suspend fun updateLastLogin(user: UserDTO): Boolean = withContext(Dispatchers.IO) {
        val count = database.update(UserDetails) {
            set(it.lastLogin, LocalDateTime.now())
            where { it.id eq user.id }
        }
        count == 1
    }

    override suspend fun isUserExists(userName: String, password: String): UserDTO?
            = withContext(Dispatchers.IO) { database.userDetails.find { (it.name eq userName) and (it.password eq password) } }?.toUserDTO()
}