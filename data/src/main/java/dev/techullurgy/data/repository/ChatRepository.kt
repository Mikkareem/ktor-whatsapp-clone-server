package dev.techullurgy.data.repository

import dev.techullurgy.data.model.database.savables.SavableChatMessage
import dev.techullurgy.data.model.database.servables.ChatMessageDTO
import java.time.LocalDateTime

interface ChatRepository {
    suspend fun getUnreadMessagesForUser(receiverId: Long): List<ChatMessageDTO>?
    suspend fun postChat(chatMessage: SavableChatMessage)
    suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime = LocalDateTime.now())
    suspend fun updateMessageRead(messageId: Long, time: LocalDateTime = LocalDateTime.now())
}