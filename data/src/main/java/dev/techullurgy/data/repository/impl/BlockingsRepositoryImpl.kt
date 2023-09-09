package dev.techullurgy.data.repository.impl

import dev.techullurgy.data.entities.Blockings
import dev.techullurgy.data.entities.UserDetails
import dev.techullurgy.data.init.ext.hasRecords
import dev.techullurgy.data.model.database.savables.SavableBlocking
import dev.techullurgy.data.model.database.servables.BlockingDTO
import dev.techullurgy.data.model.database.servables.UserDTO
import dev.techullurgy.data.repository.BlockingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.*

internal class BlockingsRepositoryImpl(
    private val database: Database
): BlockingsRepository {
    override suspend fun blockUser(blocking: SavableBlocking): Boolean = withContext(Dispatchers.IO) {
        val count = database.update(Blockings) {
            set(it.isActive, true)
            where {
                (Blockings.blockedUser eq blocking.blockedUser.id)
                (Blockings.blockedBy eq blocking.blockedBy.id)
            }
        }

        if(count > 0) count > 0

        val count2 = database.insert(Blockings) {
            set(it.blockedBy, blocking.blockedBy.id)
            set(it.blockedUser, blocking.blockedUser.id)
            set(it.blockTime, blocking.blockedTime)
            set(it.isActive, blocking.isActive)
        }
        count2 > 0
    }

    override suspend fun getBlockedUsersForUser(user: UserDTO): List<UserDTO>? = withContext(Dispatchers.IO) {
        val query = database
            .from(Blockings)
            .leftJoin(UserDetails, on = UserDetails.id eq Blockings.blockedUser)
            .select(UserDetails.id, UserDetails.name, UserDetails.email)
            .where {
                (Blockings.blockedBy eq user.id) and (Blockings.isActive eq true)
            }

        if(query.hasRecords()) {
            query.map { row ->
                UserDTO(row[UserDetails.id]!!, row[UserDetails.name]!!, row[UserDetails.email]!!)
            }
        } else null
    }

    override suspend fun unBlockUser(blocking: BlockingDTO): Boolean = withContext(Dispatchers.IO) {
        val count = database.update(Blockings) {
            set(it.isActive, false)
            where {
                (Blockings.blockedUser eq blocking.blockedUser.id)
                (Blockings.blockedBy eq blocking.blockedBy.id)
            }
        }
        count > 0
    }

    override suspend fun isBlockedChannel(blocking: BlockingDTO): Boolean = withContext(Dispatchers.IO) {
        val query = database
            .from(Blockings)
            .select()
            .where {
                ((Blockings.blockedUser eq blocking.blockedUser.id) and (Blockings.blockedBy eq blocking.blockedBy.id) and (Blockings.isActive eq true)) or
                ((Blockings.blockedUser eq blocking.blockedBy.id) and (Blockings.blockedBy eq blocking.blockedUser.id) and (Blockings.isActive eq true))
            }
        query.hasRecords()
    }
}