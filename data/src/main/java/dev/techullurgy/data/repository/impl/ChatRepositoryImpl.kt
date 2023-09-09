package dev.techullurgy.data.repository.impl

import dev.techullurgy.data.entities.Messages
import dev.techullurgy.data.entities.UserDetails
import dev.techullurgy.data.model.database.ChatMessage
import dev.techullurgy.data.model.database.ChatMessageType
import dev.techullurgy.data.model.database.SavableChatMessage
import dev.techullurgy.data.repository.ChatRepository
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.time.LocalDateTime

internal class ChatRepositoryImpl(
    private val database: Database
): ChatRepository {
    override suspend fun getUnreadTextMessagesForUser(receiverId: Long): List<ChatMessage> {
        val msg = Messages.aliased("msg") as Messages
        val senders = UserDetails.aliased("senders") as UserDetails
        val receiver = UserDetails.aliased("receiver") as UserDetails

        val unreadNewMessagesForUser: List<ChatMessage> = database
            .from(msg)
            .leftJoin(senders, on = msg.sender eq senders.id)
            .leftJoin(receiver, on = msg.receiver eq receiver.id)
            .select(
                msg.id, msg.type, msg.sender, msg.receiver, senders.name, receiver.name, msg.sentTimeFromSender, msg.payload, msg.receivedTimeToReceiver
            )
            .where {
                (msg.receiver eq receiverId) and
                (msg.isReceived eq false) and
                (msg.type eq ChatMessageType.TEXT)
            }
            .map { row ->
                ChatMessage(
                    id = row[msg.id]!!,
                    type = ChatMessageType.valueOf(row[msg.type]!!.name),
                    senderId = row[msg.sender]!!,
                    receiverId = row[msg.receiver]!!,
                    senderName = row[senders.name]!!,
                    receiverName = row[receiver.name]!!,
                    sentTimeFromSender = row[msg.sentTimeFromSender]!!,
                    payload = row[msg.payload]!!,
                    receivedTimeToReceiver = row[msg.receivedTimeToReceiver]
                )
            }
        return unreadNewMessagesForUser
    }

    override suspend fun postChat(chatMessage: SavableChatMessage) {
        database
            .insert(Messages) {
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