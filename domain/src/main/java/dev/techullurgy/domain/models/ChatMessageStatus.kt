package dev.techullurgy.domain.models

import dev.techullurgy.data.model.database.servables.ChatMessageStatusDTO

enum class ChatMessageStatus {
    SENT, RECEIVED, READ
}

fun ChatMessageStatusDTO.toChatMessageStatus(): ChatMessageStatus = ChatMessageStatus.valueOf(name)
fun ChatMessageStatus.toChatMessageStatusDTO(): ChatMessageStatusDTO = ChatMessageStatusDTO.valueOf(name)