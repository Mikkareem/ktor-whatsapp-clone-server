package dev.techullurgy.data.entities

import dev.techullurgy.data.init.schema.MessagesTable
import dev.techullurgy.data.init.schema.MessagesTableStructure
import dev.techullurgy.data.model.database.ChatMessageType
import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDateTime

internal interface MessageEntity: Entity<MessageEntity> {
    companion object: Entity.Factory<MessageEntity>()
    val sender: UserDetail
    val receiver: UserDetail
    val id: Long
    val payload: String
    val type: ChatMessageType
    val sentTimeFromSender: LocalDateTime
    val receivedTimeToReceiver: LocalDateTime?
    val isReceived: Boolean
}

internal open class Messages(alias: String?): Table<MessageEntity>(MessagesTable, alias) {
    companion object : Messages(null)

    override fun aliased(alias: String): Table<MessageEntity> = Messages(alias)

    val id = long(MessagesTableStructure.MESSAGE_ID).primaryKey().bindTo { it.id }
    val payload = varchar(MessagesTableStructure.PAYLOAD).bindTo { it.payload }
    val type = enum<ChatMessageType>(MessagesTableStructure.TYPE).bindTo { it.type }
    val isReceived = boolean(MessagesTableStructure.IS_RECEIVED).bindTo { it.isReceived }
    val sender = long(MessagesTableStructure.SENDER_ID).references(UserDetails) { it.sender }
    val receiver = long(MessagesTableStructure.RECEIVER_ID).references(UserDetails) { it.receiver }
    val sentTimeFromSender = datetime(MessagesTableStructure.SENT_TIME_FROM_SENDER).bindTo { it.sentTimeFromSender }
    val receivedTimeToReceiver = datetime(MessagesTableStructure.RECEIVED_TIME_TO_RECEIVER).bindTo { it.receivedTimeToReceiver }
}