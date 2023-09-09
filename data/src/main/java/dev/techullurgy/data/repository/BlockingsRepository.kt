package dev.techullurgy.data.repository

import dev.techullurgy.data.model.database.savables.SavableBlocking
import dev.techullurgy.data.model.database.servables.BlockingDTO
import dev.techullurgy.data.model.database.servables.UserDTO

interface BlockingsRepository {
    /**
     * This function is used to block the user, It inserts new Blocking Entry in "Blockings" Table
     *
     * Returns true, if it successfully inserts the new blocking entry.
     */
    suspend fun blockUser(blocking: SavableBlocking): Boolean

    /**
     * This function returns the list of users that are blocked by incoming user
     * Returns null, if there is no blocked users for incoming user
     */
    suspend fun getBlockedUsersForUser(user: UserDTO): List<UserDTO>?

    /**
     * This function is used to Unblock the user.
     */
    suspend fun unBlockUser(blocking: BlockingDTO): Boolean

    /**
     * This function is used to find whether the Channel between two users are valid.
     * It checks both the way, Like
     *      1) Incoming ----> Outgoing
     *      2) Outgoing ----> Incoming
     * Both the cases, we need to Block the channel like (conversation...)
     */
    suspend fun isBlockedChannel(blocking: BlockingDTO): Boolean
}