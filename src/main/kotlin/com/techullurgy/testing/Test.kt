package com.techullurgy.testing

import com.techullurgy.data.entities.ChatMessageType
import com.techullurgy.data.model.database.ChatMessage
import com.techullurgy.data.model.database.User
import com.techullurgy.data.repository.ChatRepository
import com.techullurgy.data.repository.UserDetailsRepository
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun test(
    userDetailsRepository: UserDetailsRepository,
    chatRepository: ChatRepository
) {
    runBlocking {
        userDetailsRepository.insertUser(User(name = "Irsath", email = "abc@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Deepika", email = "gdf@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Rajesh", email = "jjf@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Guru", email = "eyu@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Prasad", email = "asa@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Riyas", email = "kdu@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Faisal", email = "ier@gmail.com", password = "free"))
        userDetailsRepository.insertUser(User(name = "Rahman", email = "uir@gmail.com", password = "free"))

        chatRepository.postChat(
            chatMessage = ChatMessage(
                senderId = 2,
                receiverId = 4,
                payload = "Hi da how are you?",
                type = ChatMessageType.text,
                isReceived = false,
                sentTimeFromSender = LocalDateTime.now(),
                receivedTimeToReceiver = null
            )
        )

        chatRepository.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 4,
                payload = "Hi da Free are you?",
                type = ChatMessageType.text,
                isReceived = false,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(10L),
                receivedTimeToReceiver = null
            )
        )

        chatRepository.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.image,
                isReceived = false,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(20L),
                receivedTimeToReceiver = null
            )
        )

        chatRepository.postChat(
            chatMessage = ChatMessage(
                senderId = 1,
                receiverId = 2,
                payload = "http::/localhost:4444/get",
                type = ChatMessageType.text,
                isReceived = false,
                sentTimeFromSender = LocalDateTime.now().minusMinutes(14L),
                receivedTimeToReceiver = null
            )
        )
    }
}