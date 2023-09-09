package dev.techullurgy.data.model.database.servables

import java.time.LocalDateTime

data class ChatMessageDTO(
    val senderId: Long,
    val receiverId: Long,
    val id: Long = 0,
    val payload: String,
    val type: ChatMessageTypeDTO,
    val status: ChatMessageStatusDTO,
    val sentTimeFromSender: LocalDateTime
)
