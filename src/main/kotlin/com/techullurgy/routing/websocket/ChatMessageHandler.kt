package com.techullurgy.routing.websocket

import com.techullurgy.data.model.network.NetworkChatMessage
import com.techullurgy.plugins.UserPrincipal
import com.techullurgy.plugins.repositories
import io.ktor.server.auth.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

private val connectedUsers = ConcurrentHashMap<Long, WebSocketSession>()

suspend fun DefaultWebSocketServerSession.addToConnectedUsersIfNotExists() {
    val userId = call.principal<UserPrincipal>()?.user!!.id
    userId.let {
        if(!connectedUsers.containsKey(it)) {
            connectedUsers[it] = this
            sendUnreadMessagesToClient(it)
        }
    }
}

suspend fun sendUnreadMessagesToClient(receiver: Long) {
    val chatRepository = repositories?.chatRepository
    chatRepository?.let {
        val messages = it.getUnreadTextMessagesForUser(receiver)
        messages.forEach { message ->
            delay(300)
            connectedUsers[receiver]?.send(Frame.Text(Json.encodeToString(NetworkChatMessage.serializer(), message)))
        }
    }
}