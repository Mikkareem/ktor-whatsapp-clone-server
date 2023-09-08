package dev.techullurgy.data.model.database

import java.time.LocalDateTime

data class ChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val id: Long = 0,
    val payload: String,
    val type: ChatMessageType,
    val sentTimeFromSender: LocalDateTime,
    val receivedTimeToReceiver: LocalDateTime?,
    val isReceived: Boolean = false,
    val senderName: String,
    val receiverName: String
)
