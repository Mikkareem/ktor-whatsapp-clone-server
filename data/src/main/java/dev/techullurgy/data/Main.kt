package dev.techullurgy.data

import dev.techullurgy.data.init.initiations.BlockingsTableInitiation
import dev.techullurgy.data.init.initiations.MessagesTableInitiation
import dev.techullurgy.data.init.initiations.UserDetailsTableInitiation
import dev.techullurgy.data.repository.ChatRepository
import dev.techullurgy.data.repository.ChatRepositoryImpl
import dev.techullurgy.data.repository.UserDetailsRepository
import dev.techullurgy.data.repository.UserDetailsRepositoryImpl
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

    repositories = Repositories(
        userDetailsRepository = userDetailsRepository,
        chatRepository = chatRepository
    )
}

data class Repositories(
    val userDetailsRepository: UserDetailsRepository,
    val chatRepository: ChatRepository
)

var repositories: Repositories? = null
    private set