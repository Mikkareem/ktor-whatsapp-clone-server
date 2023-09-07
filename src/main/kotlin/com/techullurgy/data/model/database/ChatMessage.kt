package com.techullurgy.data.model.database

import com.techullurgy.data.entities.ChatMessageType
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

data class ChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val id: Long = 0,
    val payload: String,
    val type: ChatMessageType,
    val sentTimeFromSender: LocalDateTime,
    val receivedTimeToReceiver: LocalDateTime?,
    val isReceived: Boolean
)

