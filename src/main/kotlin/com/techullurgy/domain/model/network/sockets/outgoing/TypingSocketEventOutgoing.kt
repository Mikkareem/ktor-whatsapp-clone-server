package com.techullurgy.domain.model.network.sockets.outgoing

import com.techullurgy.domain.model.network.sockets.TypingEvent
import kotlinx.serialization.Serializable

@Serializable
data class TypingSocketEventOutgoing(
    val event: TypingEvent,
    val typingFromId: Long,
    val typingToId: Long
)
