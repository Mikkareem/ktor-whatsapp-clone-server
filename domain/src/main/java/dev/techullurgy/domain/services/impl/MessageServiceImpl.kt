package dev.techullurgy.domain.services.impl

import dev.techullurgy.data.repository.BlockingsRepository
import dev.techullurgy.data.repository.ChatRepository
import dev.techullurgy.data.repository.UserDetailsRepository
import dev.techullurgy.domain.models.ChatMessage
import dev.techullurgy.domain.models.toChatMessage
import dev.techullurgy.domain.models.toSavableChatMessage
import dev.techullurgy.domain.services.MessageService
import java.time.LocalDateTime

internal class MessageServiceImpl(
    private val userDetailsRepository: UserDetailsRepository,
    private val blockingsRepository: BlockingsRepository,
    private val chatRepository: ChatRepository
): MessageService {
    override suspend fun getUnreadChatMessagesForUser(receiverId: Long): List<ChatMessage>? {
        val chatMessages = chatRepository.getUnreadMessagesForUser(receiverId)?.map { it.toChatMessage() }

        return chatMessages?.let {
            val user = userDetailsRepository.getUserByUserId(receiverId)!!
            val blockedSenders = blockingsRepository.getBlockedUsersForUser(user)?.map { it.id }?.toSet()
            blockedSenders?.let {
                chatMessages.filter { chatMessage -> !it.contains(chatMessage.senderId) }
            } ?: chatMessages
        }
    }

    override suspend fun postChat(chatMessage: ChatMessage) {
        chatRepository.postChat(chatMessage.toSavableChatMessage())
    }

    override suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime) {
        chatRepository.updateMessageReceived(messageId, time)
    }

    override suspend fun updateMessageRead(messageId: Long, time: LocalDateTime) {
        chatRepository.updateMessageRead(messageId, time)
    }

}