package com.techullurgy.data.model.database

import com.techullurgy.data.entities.ChatMessageType
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

data class ChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val id: Long,
    val payload: String,
    val type: ChatMessageType,
    val sentTimeFromSender: LocalDateTime,
    val receivedTimeToReceiver: LocalDateTime?,
    val isReceived: Boolean
)

@Serializable
data class NetworkChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val senderName: String,
    val receiverName: String,
    val id: Long,
    val payload: String,
    val type: ChatMessageType,
    val sentTimeFromSender: String,
    val receivedTimeToReceiver: String?
)