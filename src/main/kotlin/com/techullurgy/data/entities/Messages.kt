package com.techullurgy.data.entities

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDateTime

interface Message: Entity<Message> {
    companion object: Entity.Factory<Message>()
    val sender: UserDetail
    val receiver: UserDetail
    val id: Long
    val payload: String
    val type: ChatMessageType
    val sentTimeFromSender: LocalDateTime
    val receivedTimeToReceiver: LocalDateTime?
    val isReceived: Boolean
}

object Messages: Table<Message>(MessagesTable) {
    val id = long(MessagesColumns.MESSAGE_ID).primaryKey().bindTo { it.id }
    val payload = varchar(MessagesColumns.PAYLOAD).bindTo { it.payload }
    val type = enum<ChatMessageType>(MessagesColumns.TYPE).bindTo { it.type }
    val isReceived = boolean(MessagesColumns.IS_RECEIVED).bindTo { it.isReceived }
    val sender = long(MessagesColumns.SENDER_ID).references(UserDetails) { it.sender }
    val receiver = long(MessagesColumns.RECEIVER_ID).references(UserDetails) { it.receiver }
    val sentTimeFromSender = datetime(MessagesColumns.SENT_TIME_FROM_SENDER).bindTo { it.sentTimeFromSender }
    val receivedTimeToReceiver = datetime(MessagesColumns.RECEIVED_TIME_TO_RECEIVER).bindTo { it.receivedTimeToReceiver }
}

val Database.chatMessages get() = this.sequenceOf(Messages)

enum class ChatMessageType {
    VIDEO,
    TEXT,
    VOICE,
    IMAGE
}