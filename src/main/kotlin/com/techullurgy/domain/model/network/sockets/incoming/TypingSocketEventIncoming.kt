package com.techullurgy.domain.model.network.sockets.incoming

import com.techullurgy.domain.model.network.sockets.TypingEvent
import kotlinx.serialization.Serializable

@Serializable
data class TypingSocketEventIncoming(
    val event: TypingEvent,
    val typingFromId: Long,
    val typingToId: Long
)
