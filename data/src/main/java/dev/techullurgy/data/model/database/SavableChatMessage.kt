package dev.techullurgy.data.model.database

import java.time.LocalDateTime

data class SavableChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val payload: String,
    val type: ChatMessageType,
    val sentTimeFromSender: LocalDateTime = LocalDateTime.now(),
    val receivedTimeToReceiver: LocalDateTime? = null,
    val isReceived: Boolean = false
)