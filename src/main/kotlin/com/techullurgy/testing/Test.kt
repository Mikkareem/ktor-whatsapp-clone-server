package com.techullurgy.testing

import dev.techullurgy.data.model.database.ChatMessageType
import dev.techullurgy.data.model.database.SavableChatMessage
import dev.techullurgy.data.model.database.SavableUser
import dev.techullurgy.data.repository.ChatRepository
import dev.techullurgy.data.repository.UserDetailsRepository
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun test(
    userDetailsRepository: UserDetailsRepository,
    chatRepository: ChatRepository
) {
    runBlocking {
        userDetailsRepository.insertUser(SavableUser(name = "Irsath", email = "abc@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Deepika", email = "gdf@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Rajesh", email = "jjf@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Guru", email = "eyu@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Prasad", email = "asa@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Riyas", email = "kdu@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Faisal", email = "ier@gmail.com", password = "free"))
        userDetailsRepository.insertUser(SavableUser(name = "Rahman", email = "uir@gmail.com", password = "free"))

        chatRepository.postChat(
            chatMessage = SavableChatMessage(
                senderId = 2,
                receiverId = 4,
                payload = "Hi da how are you?",
                type = ChatMessageType.TEXT
            )
        )

        chatRepository.postChat(
            chatMessage = SavableChatMessage(
                senderId = 1,
                receiverId = 4,
                payload = "Hi da Free are you?",
                type = ChatMessageType.TEXT,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(10L)
            )
        )

        chatRepository.postChat(
            chatMessage = SavableChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.IMAGE,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(20L)
            )
        )

        chatRepository.postChat(
            chatMessage = SavableChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.TEXT,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(14L)
            )
        )
    }
}