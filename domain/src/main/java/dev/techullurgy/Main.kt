package dev.techullurgy

import dev.techullurgy.data.initDB
import dev.techullurgy.data.repositories
import dev.techullurgy.domain.services.MessageService
import dev.techullurgy.domain.services.UserService
import dev.techullurgy.domain.services.impl.MessageServiceImpl
import dev.techullurgy.domain.services.impl.UserServiceImpl

suspend fun initServices(dbConfiguration: DBConfiguration) {
    initDB(
        url = dbConfiguration.url,
        username = dbConfiguration.username,
        password = dbConfiguration.password
    )

    val userDetailsRepository = repositories?.userDetailsRepository!!
    val chatRepository = repositories?.chatRepository!!
    val blockingsRepository = repositories?.blockingsRepository!!

    val userService: UserService = UserServiceImpl(userDetailsRepository, blockingsRepository)
    val messageService: MessageService = MessageServiceImpl(userDetailsRepository, blockingsRepository, chatRepository)

    services = Services(
        userService = userService,
        messageService = messageService
    )
}

data class DBConfiguration(
    val url: String,
    val username: String,
    val password: String
)

data class Services(
    val userService: UserService,
    val messageService: MessageService
)

var services: Services? = null
    private set