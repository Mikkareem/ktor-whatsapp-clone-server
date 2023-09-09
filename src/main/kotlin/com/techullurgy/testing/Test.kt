package com.techullurgy.testing

import dev.techullurgy.domain.models.ChatMessage
import dev.techullurgy.domain.models.ChatMessageStatus
import dev.techullurgy.domain.models.ChatMessageType
import dev.techullurgy.domain.services.MessageService
import dev.techullurgy.domain.services.UserService
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun test(
    userService: UserService,
    messageService: MessageService
) {
    runBlocking {
        userService.registerUser(username = "Irsath", email = "abc@gmail.com", password = "free")
        userService.registerUser(username = "Deepika", email = "gdf@gmail.com", password = "free")
        userService.registerUser(username = "Rajesh", email = "jjf@gmail.com", password = "free")
        userService.registerUser(username = "Guru", email = "eyu@gmail.com", password = "free")
        userService.registerUser(username = "Prasad", email = "asa@gmail.com", password = "free")
        userService.registerUser(username = "Riyas", email = "kdu@gmail.com", password = "free")
        userService.registerUser(username = "Faisal", email = "ier@gmail.com", password = "free")
        userService.registerUser(username = "Rahman", email = "uir@gmail.com", password = "free")

        messageService.postChat(
            chatMessage = ChatMessage(
                senderId = 2,
                receiverId = 4,
                payload = "Hi da how are you?",
                type = ChatMessageType.TEXT,
                status = ChatMessageStatus.SENT
            )
        )

        messageService.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 4,
                payload = "Hi da Free are you?",
                type = ChatMessageType.TEXT,
                status = ChatMessageStatus.SENT,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(24L)
            )
        )

        messageService.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.IMAGE,
                status = ChatMessageStatus.SENT,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(20L)
            )
        )

        messageService.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.TEXT,
                status = ChatMessageStatus.SENT,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(14L)
            )
        )
    }
}