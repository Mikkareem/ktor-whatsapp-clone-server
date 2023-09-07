package com.techullurgy.data.entities

internal const val MessagesTable = "messages"
internal const val UserDetailsTable = "user_details"

internal object MessagesColumns {
    const val MESSAGE_ID: String = "message_id"
    const val PAYLOAD: String = "payload"
    const val TYPE: String = "msg_type"
    const val IS_RECEIVED: String = "is_received"
    const val SENDER_ID: String = "sender_id"
    const val RECEIVER_ID: String = "receiver_id"
    const val SENT_TIME_FROM_SENDER: String = "sent_time_from_sender"
    const val RECEIVED_TIME_TO_RECEIVER: String = "received_time_to_receiver"
}

internal object UserDetailsColumns {
    const val USER_ID: String = "user_id"
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "user_password"
    const val EMAIL_ID: String = "email_id"
    const val REGISTERED_AT: String = "registered_at"
    const val LAST_LOGIN: String = "last_login"
}