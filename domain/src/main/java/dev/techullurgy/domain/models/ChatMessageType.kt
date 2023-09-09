package dev.techullurgy.domain.models

import dev.techullurgy.data.model.database.servables.ChatMessageTypeDTO

enum class ChatMessageType {
    VIDEO,
    TEXT,
    VOICE,
    IMAGE
}

internal fun ChatMessageTypeDTO.toChatMessageType(): ChatMessageType = ChatMessageType.valueOf(name)
internal fun ChatMessageType.toChatMessageTypeDTO(): ChatMessageTypeDTO = ChatMessageTypeDTO.valueOf(name)