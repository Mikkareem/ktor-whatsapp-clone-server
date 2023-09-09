package dev.techullurgy.domain.services

import dev.techullurgy.domain.models.ChatMessage
import java.time.LocalDateTime

interface MessageService {
    suspend fun getUnreadChatMessagesForUser(receiverId: Long): List<ChatMessage>?
    suspend fun postChat(chatMessage: ChatMessage)
    suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime = LocalDateTime.now())
    suspend fun updateMessageRead(messageId: Long, time: LocalDateTime = LocalDateTime.now())
}