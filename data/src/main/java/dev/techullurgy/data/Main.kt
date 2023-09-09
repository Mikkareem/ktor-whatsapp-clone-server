package dev.techullurgy.data

import dev.techullurgy.data.init.initiations.BlockingsTableInitiation
import dev.techullurgy.data.init.initiations.MessagesTableInitiation
import dev.techullurgy.data.init.initiations.UserDetailsTableInitiation
import dev.techullurgy.data.repository.BlockingsRepository
import dev.techullurgy.data.repository.ChatRepository
import dev.techullurgy.data.repository.UserDetailsRepository
import dev.techullurgy.data.repository.impl.BlockingsRepositoryImpl
import dev.techullurgy.data.repository.impl.ChatRepositoryImpl
import dev.techullurgy.data.repository.impl.UserDetailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

private suspend fun Database.init() {
    UserDetailsTableInitiation.initiate(this)
    MessagesTableInitiation.initiate(this)
    BlockingsTableInitiation.initiate(this)

    println("Tables are available now")
}

suspend fun initDB(url: String, username: String, password: String) = withContext(Dispatchers.IO) {
    val database = Database.connect(url = url, user = username, password = password, logger = ConsoleLogger(LogLevel.DEBUG))
    database.init()

    val userDetailsRepository = UserDetailsRepositoryImpl(database)
    val chatRepository = ChatRepositoryImpl(database)
    val blockingsRepository = BlockingsRepositoryImpl(database)

    dev.techullurgy.data.repositories = dev.techullurgy.data.Repositories(
        userDetailsRepository = userDetailsRepository,
        chatRepository = chatRepository,
        blockingsRepository = blockingsRepository
    )
}

data class Repositories(
    val userDetailsRepository: UserDetailsRepository,
    val chatRepository: ChatRepository,
    val blockingsRepository: BlockingsRepository
)

var repositories: dev.techullurgy.data.Repositories? = null
    private set