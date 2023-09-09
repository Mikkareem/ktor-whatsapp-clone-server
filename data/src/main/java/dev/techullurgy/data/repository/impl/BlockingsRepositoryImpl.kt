package dev.techullurgy.data.repository.impl

import dev.techullurgy.data.entities.Blockings
import dev.techullurgy.data.entities.UserDetails
import dev.techullurgy.data.init.ext.hasRecords
import dev.techullurgy.data.model.database.SavableBlocking
import dev.techullurgy.data.model.database.User
import dev.techullurgy.data.repository.BlockingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.*

class BlockingsRepositoryImpl(
    private val database: Database
): BlockingsRepository {
    override suspend fun blockUser(blocking: SavableBlocking): Boolean = withContext(Dispatchers.IO) {
        val count = database.insert(Blockings) {
            set(it.blockedBy, blocking.blockedBy.id)
            set(it.blockedUser, blocking.blockedUser.id)
            set(it.blockTime, blocking.blockedTime)
            set(it.isActive, blocking.isActive)
        }
        count > 0
    }

    override suspend fun getBlockedUsersForUser(user: User): List<User>? = withContext(Dispatchers.IO) {
        val query = database
            .from(Blockings)
            .leftJoin(UserDetails, on = UserDetails.id eq Blockings.blockedUser)
            .select(UserDetails.id, UserDetails.name, UserDetails.email)
            .where {
                (Blockings.blockedBy eq user.id) and (Blockings.isActive eq true)
            }

        if(query.hasRecords()) {
            query.map { row ->
                User(row[UserDetails.id]!!, row[UserDetails.name]!!, row[UserDetails.email]!!)
            }
        } else null
    }
}