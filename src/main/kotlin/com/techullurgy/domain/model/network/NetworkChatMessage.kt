package com.techullurgy.domain.model.network

import dev.techullurgy.data.model.database.ChatMessageType
import kotlinx.serialization.Serializable

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