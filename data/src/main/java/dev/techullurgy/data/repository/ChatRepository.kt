package dev.techullurgy.data.repository

import dev.techullurgy.data.model.database.ChatMessage
import dev.techullurgy.data.model.database.SavableChatMessage
import java.time.LocalDateTime

interface ChatRepository {
    suspend fun getUnreadTextMessagesForUser(receiverId: Long): List<ChatMessage>
    suspend fun postChat(chatMessage: SavableChatMessage)
    suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime = LocalDateTime.now())
}