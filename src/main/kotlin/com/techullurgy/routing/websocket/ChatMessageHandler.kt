package com.techullurgy.routing.websocket

import com.techullurgy.domain.model.network.response.NetworkChatMessageResponse
import com.techullurgy.domain.model.network.response.toNetworkChatMessageResponse
import com.techullurgy.domain.model.network.sockets.incoming.TypingSocketEventIncoming
import com.techullurgy.domain.model.network.sockets.outgoing.TypingSocketEventOutgoing
import com.techullurgy.plugins.UserPrincipal
import dev.techullurgy.domain.services.MessageService
import dev.techullurgy.services
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

private const val WEBSOCKET_UNREAD_MESSAGE_SEND_DELAY = 300L

private val connectedUsers = ConcurrentHashMap<Long, WebSocketSession>()

fun Route.setupChatSession() {
    webSocket("/chat") {
        addToConnectedUsersIfNotExists()
        for (frame in incoming) {
            if(frame is Frame.Text) {
                val text = frame.readText()
                if(text.lowercase().startsWith("typing")) {
                    handleTypingEvent(text)
                }
            }
        }
    }
}

private suspend fun DefaultWebSocketServerSession.addToConnectedUsersIfNotExists() {
    val userId = call.principal<UserPrincipal>()?.user?.id
    userId?.let {
        synchronized(connectedUsers) {
            if (!connectedUsers.containsKey(it)) {
                connectedUsers[it] = this
            }
        }
        sendUnreadMessagesToUser(it)
    }
}

private suspend fun sendUnreadMessagesToUser(receiver: Long) {
    val messageService: MessageService? = services?.messageService
    messageService?.let {
        val messages = it.getUnreadChatMessagesForUser(receiver)
        messages?.forEach { message ->
            delay(WEBSOCKET_UNREAD_MESSAGE_SEND_DELAY)
            connectedUsers[receiver]?.send(
                Frame.Text(
                    Json.encodeToString(
                        NetworkChatMessageResponse.serializer(),
                        message.toNetworkChatMessageResponse()
                    )
                )
            )
        }
    }
}

private suspend fun handleTypingEvent(text: String) {
    val data = text.substringAfter("#")
    val typingEvent = Json.decodeFromString<TypingSocketEventIncoming>(data)
    val canReceive = services!!.userService.canReceiveTypingEvent(typingEvent.typingFromId, typingEvent.typingToId)
    if(canReceive) {
        val typingSocketEventOutgoing = TypingSocketEventOutgoing(
            event = typingEvent.event,
            typingFromId = typingEvent.typingFromId,
            typingToId = typingEvent.typingToId
        )
        val json = Json.encodeToString(typingSocketEventOutgoing)
        connectedUsers[typingEvent.typingToId]?.send(Frame.Text("typing#$json"))
    }
}