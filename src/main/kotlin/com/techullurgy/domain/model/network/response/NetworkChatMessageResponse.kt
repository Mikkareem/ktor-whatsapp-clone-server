package com.techullurgy.domain.model.network.response

import dev.techullurgy.domain.models.ChatMessage
import dev.techullurgy.domain.models.ChatMessageStatus
import dev.techullurgy.domain.models.ChatMessageType
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class NetworkChatMessageResponse(
    val senderId: Long,
    val receiverId: Long,
    val id: Long,
    val payload: String,
    val type: ChatMessageType,
    val status: ChatMessageStatus,
    val sentTimeFromSender: String
)

internal fun ChatMessage.toNetworkChatMessageResponse(): NetworkChatMessageResponse = NetworkChatMessageResponse(
    senderId = senderId,
    receiverId = receiverId,
    id = id,
    payload = payload,
    type = type,
    status = status,
    sentTimeFromSender = sentTimeFromSender.format(DateTimeFormatter.ISO_DATE_TIME)
)