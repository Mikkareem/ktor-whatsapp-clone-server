package dev.techullurgy.data.model.database.savables

import dev.techullurgy.data.model.database.servables.ChatMessageStatusDTO
import dev.techullurgy.data.model.database.servables.ChatMessageTypeDTO
import java.time.LocalDateTime

data class SavableChatMessage(
    val senderId: Long,
    val receiverId: Long,
    val payload: String,
    val type: ChatMessageTypeDTO,
    val status: ChatMessageStatusDTO = ChatMessageStatusDTO.SENT,
    val sentTimeFromSender: LocalDateTime = LocalDateTime.now(),
    val receivedTimeToReceiver: LocalDateTime? = null,
    val readTimeByReceiver: LocalDateTime? = null
)