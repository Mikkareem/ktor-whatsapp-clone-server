package com.techullurgy.routing.websocket

import com.techullurgy.plugins.UserPrincipal
import io.ktor.server.auth.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

private val connectedUsers = ConcurrentHashMap<String, WebSocketSession>()

suspend fun DefaultWebSocketServerSession.addToConnectedUsersIfNotExists() {
    val username = call.principal<UserPrincipal>()?.user!!.name
    if(!connectedUsers.containsKey(username)) {
        connectedUsers[username] = this
        sendUnreadMessagesToClient()
    }
}

suspend fun DefaultWebSocketServerSession.sendUnreadMessagesToClient() {

}