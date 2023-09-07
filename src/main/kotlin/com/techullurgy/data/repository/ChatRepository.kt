package com.techullurgy.data.repository

import com.techullurgy.data.model.database.ChatMessage
import com.techullurgy.data.model.database.NetworkChatMessage
import java.time.LocalDateTime

interface ChatRepository {
    suspend fun getUnreadTextMessagesForUser(receiverId: Long): List<NetworkChatMessage>
    suspend fun postChat(chatMessage: ChatMessage)
    suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime = LocalDateTime.now())
}