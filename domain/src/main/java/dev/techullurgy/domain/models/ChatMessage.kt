package dev.techullurgy.domain.models

import dev.techullurgy.data.model.database.savables.SavableChatMessage
import dev.techullurgy.data.model.database.servables.ChatMessageDTO
import java.time.LocalDateTime

data class ChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val id: Long = 0,
    val payload: String,
    val type: ChatMessageType,
    val status: ChatMessageStatus,
    val sentTimeFromSender: LocalDateTime = LocalDateTime.now()
)

internal fun ChatMessageDTO.toChatMessage(): ChatMessage = ChatMessage(
    senderId = senderId,
    receiverId = receiverId,
    id = id,
    payload = payload,
    type = type.toChatMessageType(),
    status = status.toChatMessageStatus(),
    sentTimeFromSender = sentTimeFromSender
)

internal fun ChatMessage.toChatMessageDTO(): ChatMessageDTO = ChatMessageDTO(
    senderId = senderId,
    receiverId = receiverId,
    id = id,
    payload = payload,
    type = type.toChatMessageTypeDTO(),
    status = status.toChatMessageStatusDTO(),
    sentTimeFromSender = sentTimeFromSender
)

internal fun ChatMessage.toSavableChatMessage(): SavableChatMessage = SavableChatMessage(
    senderId = senderId,
    receiverId = receiverId,
    payload = payload,
    type = type.toChatMessageTypeDTO(),
    status = status.toChatMessageStatusDTO(),
    sentTimeFromSender = sentTimeFromSender
)