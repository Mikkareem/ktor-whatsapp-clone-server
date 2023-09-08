package dev.techullurgy.data.init.schema

import dev.techullurgy.data.utils.ForeignKey

const val UserDetailsTable = "user_details"

internal object UserDetailsTableStructure: TableStructure {
    const val USER_ID: String = "user_id"
    const val USER_NAME: String = "user_name"
    const val PASSWORD: String = "user_password"
    const val EMAIL_ID: String = "email_id"
    const val REGISTERED_AT: String = "registered_at"
    const val LAST_LOGIN: String = "last_login"

    override val typeMap: Map<String, String>
        get() = HashMap<String, String>().apply {
            put(USER_ID, "int auto_increment primary key")
            put(USER_NAME, "varchar(100) not null")
            put(PASSWORD, "varchar(100) not null")
            put(EMAIL_ID, "varchar(100) not null")
            put(REGISTERED_AT, "datetime")
            put(LAST_LOGIN, "datetime default null")
        }

    override val foreignKeys: List<ForeignKey>?
        get() = null
}