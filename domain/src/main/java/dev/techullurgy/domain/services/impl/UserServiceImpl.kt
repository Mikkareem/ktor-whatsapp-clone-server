package dev.techullurgy.domain.services.impl

import dev.techullurgy.data.model.database.savables.SavableBlocking
import dev.techullurgy.data.model.database.savables.SavableUser
import dev.techullurgy.data.repository.BlockingsRepository
import dev.techullurgy.data.repository.UserDetailsRepository
import dev.techullurgy.domain.models.*
import dev.techullurgy.domain.services.UserService

internal class UserServiceImpl(
    private val userDetailsRepository: UserDetailsRepository,
    private val blockingsRepository: BlockingsRepository
): UserService {
    override suspend fun getUserByUserId(userId: Long): User? = userDetailsRepository.getUserByUserId(userId)?.toUser()

    override suspend fun getUserByUserName(userName: String): User? = userDetailsRepository.getUserByUserName(userName)?.toUser()

    override suspend fun getUserByEmailId(emailId: String): User? = userDetailsRepository.getUserByEmailId(emailId)?.toUser()

    override suspend fun registerUser(username: String, password: String, email: String): Boolean {
        val savableUser = SavableUser(name = username, password = password, email = email)
        return userDetailsRepository.insertUser(savableUser)
    }

    override suspend fun updateLastLogin(user: User): Boolean = userDetailsRepository.updateLastLogin(user.toUserDTO())

    override suspend fun isUserExists(userName: String, password: String): User? = userDetailsRepository.isUserExists(userName, password)?.toUser()

    override suspend fun blockUser(blockedBy: User, blockedUser: User): Boolean {
        val savableBlocking = SavableBlocking(blockedBy = blockedBy.toUserDTO(), blockedUser.toUserDTO())
        return blockingsRepository.blockUser(savableBlocking)
    }

    override suspend fun getBlockedUsersForUser(user: User): List<User>? = blockingsRepository.getBlockedUsersForUser(user.toUserDTO())?.map { it.toUser() }

    override suspend fun unBlockUser(blockedBy: User, blockedUser: User): Boolean {
        val blockingDTO = Blocking(blockedBy = blockedBy, blockedUser = blockedUser).toBlockingDTO()
        return blockingsRepository.unBlockUser(blockingDTO)
    }

    override suspend fun isBlockedChannel(blockedBy: User, blockedUser: User): Boolean {
        val blockingDTO = Blocking(blockedBy = blockedBy, blockedUser = blockedUser).toBlockingDTO()
        return blockingsRepository.isBlockedChannel(blockingDTO)
    }

    override suspend fun canReceiveTypingEvent(typingFromId: Long, typingToId: Long): Boolean {
        val fromUser = userDetailsRepository.getUserByUserId(typingFromId)!!.toUser()
        val toUser = userDetailsRepository.getUserByUserId(typingToId)!!.toUser()
        // TODO: Custom Settings Privacy need to add
        return isBlockedChannel(fromUser, toUser)
    }

}