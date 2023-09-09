package dev.techullurgy.data.repository.impl

import dev.techullurgy.data.entities.Messages
import dev.techullurgy.data.entities.UserDetails
import dev.techullurgy.data.init.ext.hasRecords
import dev.techullurgy.data.model.database.savables.SavableChatMessage
import dev.techullurgy.data.model.database.servables.ChatMessageDTO
import dev.techullurgy.data.model.database.servables.ChatMessageStatusDTO
import dev.techullurgy.data.model.database.servables.ChatMessageTypeDTO
import dev.techullurgy.data.repository.ChatRepository
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.time.LocalDateTime

internal class ChatRepositoryImpl(
    private val database: Database
): ChatRepository {
    override suspend fun getUnreadMessagesForUser(receiverId: Long): List<ChatMessageDTO>? {
        val msg = Messages.aliased("msg") as Messages
        val senders = UserDetails.aliased("senders") as UserDetails
        val receiver = UserDetails.aliased("receiver") as UserDetails

        val query: Query = database
            .from(msg)
            .leftJoin(senders, on = msg.sender eq senders.id)
            .leftJoin(receiver, on = msg.receiver eq receiver.id)
            .select(
                msg.id, msg.type, msg.sender, msg.receiver, senders.name, receiver.name, msg.sentTimeFromSender, msg.payload, msg.status
            )
            .where {
                (msg.receiver eq receiverId) and (msg.status eq ChatMessageStatusDTO.SENT)
            }

        return if(query.hasRecords()) {
            query.map { row ->
                ChatMessageDTO(
                    id = row[msg.id]!!,
                    type = ChatMessageTypeDTO.valueOf(row[msg.type]!!.name),
                    status = ChatMessageStatusDTO.valueOf(row[msg.status]!!.name),
                    senderId = row[msg.sender]!!,
                    receiverId = row[msg.receiver]!!,
                    sentTimeFromSender = row[msg.sentTimeFromSender]!!,
                    payload = row[msg.payload]!!
                )
            }
        } else null
    }

    override suspend fun postChat(chatMessage: SavableChatMessage) {
        database
            .insert(Messages) {
                set(Messages.sender, chatMessage.senderId)
                set(Messages.receiver, chatMessage.receiverId)
                set(Messages.type, chatMessage.type)
                set(Messages.status, chatMessage.status)
                set(Messages.payload, chatMessage.payload)
                set(Messages.sentTimeFromSender, chatMessage.sentTimeFromSender)
                set(Messages.receivedTimeToReceiver, chatMessage.receivedTimeToReceiver)
                set(Messages.readTimeByReceiver, chatMessage.readTimeByReceiver)
            }
    }

    override suspend fun updateMessageReceived(messageId: Long, time: LocalDateTime) {
        database
            .update(Messages) {
                set(Messages.receivedTimeToReceiver, time)
                set(Messages.status, ChatMessageStatusDTO.RECEIVED)
                where {
                    Messages.id eq messageId
                }
            }
    }

    override suspend fun updateMessageRead(messageId: Long, time: LocalDateTime) {
        database
            .update(Messages) {
                set(Messages.status, ChatMessageStatusDTO.READ)
                set(Messages.readTimeByReceiver, time)
                where {
                    (Messages.id eq messageId) and (Messages.status eq ChatMessageStatusDTO.RECEIVED)
                }
            }
    }
}