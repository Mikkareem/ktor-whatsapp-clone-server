package com.techullurgy.domain.model.network.sockets

import kotlinx.serialization.Serializable

@Serializable
enum class TypingEvent {
    TYPING_OPEN, TYPING_CLOSE
}