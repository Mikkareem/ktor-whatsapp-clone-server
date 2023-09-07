package com.techullurgy.data.repository

import com.techullurgy.data.entities.ChatMessageType
import com.techullurgy.data.entities.Messages
import com.techullurgy.data.entities.UserDetails
import com.techullurgy.data.model.database.ChatMessage
import com.techullurgy.data.model.database.NetworkChatMessage
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatRepositoryImpl(
    private val database: Database
): ChatRepository {
    override suspend fun getUnreadTextMessagesForUser(receiverId: Long): List<NetworkChatMessage> {
        data class TempMessage(
            val messageId: Long,
            val type: ChatMessageType,
            val sender: Long,
            val receiver: Long,
            val sentTimeFromSender: LocalDateTime,
            val payload: String,
            val receivedTimeToReceiver: LocalDateTime?
        )

        val unreadNewMessagesForUser: List<TempMessage> = database
            .from(Messages)
            .select(
                Messages.id, Messages.type, Messages.sender, Messages.receiver, Messages.sentTimeFromSender, Messages.payload, Messages.receivedTimeToReceiver
            )
            .where {
                (Messages.receiver eq receiverId) and
                (Messages.isReceived eq false) and
                (Messages.type eq ChatMessageType.TEXT)
            }
            .map {
                TempMessage(
                    messageId = it.getLong(1),
                    type = ChatMessageType.valueOf(it.getString(2)!!),
                    sender = it.getLong(3),
                    receiver = it.getLong(4),
                    sentTimeFromSender = it.getLocalDateTime(5)!!,
                    payload = it.getString(6)!!,
                    receivedTimeToReceiver = it.getLocalDateTime(7)
                )
            }

        val setOfSenders: Set<Long> = unreadNewMessagesForUser.map { it.sender }.toSet()

        val senderNames: HashMap<Long, String> = HashMap()
        setOfSenders.forEach { senderId ->
            val senderName = database
                .from(UserDetails)
                .selectDistinct(UserDetails.name)
                .where { (UserDetails.id eq senderId) }
                .map { row ->
                    row.getString(1)!!
                }
                .first()

            senderNames[senderId] = senderName
        }

        val receiverName = database
            .from(UserDetails)
            .selectDistinct(UserDetails.name)
            .where { (UserDetails.id eq receiverId) }
            .map { row ->
                row.getString(1)!!
            }
            .first()

        return unreadNewMessagesForUser.map {
            NetworkChatMessage(
                senderId = it.sender,
                receiverId = it.receiver,
                senderName = senderNames.filter { sender -> sender.key == it.sender }.map { sender -> sender.value }.first(),
                receiverName = receiverName,
                id = it.messageId,
                payload = it.payload,
                type = it.type,
                sentTimeFromSender = it.sentTimeFromSender.format(DateTimeFormatter.ISO_DATE_TIME),
                receivedTimeToReceiver = it.receivedTimeToReceiver?.format(DateTimeFormatter.ISO_DATE_TIME)
            )
        }
    }

    override suspend fun postChat(chatMessage: ChatMessage) {
        database
            .insert(Messages) {
                set(Messages.id, 1L)
                set(Messages.sender, chatMessage.senderId)
                set(Messages.receiver, chatMessage.receiverId)
                set(Messages.type, chatMessage.type)
                set(Messages.payload, chatMessage.payload)
                set(Messages.isReceived, false)
                set(Messages.sentTimeFromSender, chatMessage.sentTimeFromSender)
                set(Messages.receivedTimeToReceiver, null)
            }
    }

    override suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime) {
        database
            .update(Messages) {
                set(Messages.isReceived, true)
                set(Messages.receivedTimeToReceiver, time)
                where {
                    Messages.id eq messageId
                }
            }
    }
}