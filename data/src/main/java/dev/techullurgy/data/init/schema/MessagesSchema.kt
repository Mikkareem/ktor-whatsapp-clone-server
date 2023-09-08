package dev.techullurgy.data.init.schema

import dev.techullurgy.data.utils.ForeignKey
import dev.techullurgy.data.utils.References

internal const val MessagesTable = "messages"

internal object MessagesTableStructure: TableStructure {
    const val MESSAGE_ID: String = "message_id"
    const val PAYLOAD: String = "payload"
    const val TYPE: String = "msg_type"
    const val IS_RECEIVED: String = "is_received"
    const val SENDER_ID: String = "sender_id"
    const val RECEIVER_ID: String = "receiver_id"
    const val SENT_TIME_FROM_SENDER: String = "sent_time_from_sender"
    const val RECEIVED_TIME_TO_RECEIVER: String = "received_time_to_receiver"

    override val typeMap: Map<String, String>
        get() = HashMap<String, String>().apply {
            put(MESSAGE_ID, "bigint(20) auto_increment primary key")
            put(PAYLOAD, "varchar(1000) not null")
            put(TYPE, "enum('IMAGE', 'TEXT', 'VOICE', 'VIDEO') not null")
            put(IS_RECEIVED, "bool default false not null")
            put(SENDER_ID, "int")
            put(RECEIVER_ID, "int")
            put(SENT_TIME_FROM_SENDER, "datetime")
            put(RECEIVED_TIME_TO_RECEIVER, "datetime default null")
        }

    override val foreignKeys: List<ForeignKey>
        get() = listOf(
            ForeignKey(
                columnName = SENDER_ID,
                references = References(
                    tableName = UserDetailsTable,
                    columnName = UserDetailsTableStructure.USER_ID
                )
            ),
            ForeignKey(
                columnName = RECEIVER_ID,
                references = References(
                    tableName = UserDetailsTable,
                    columnName = UserDetailsTableStructure.USER_ID
                )
            )
        )
}