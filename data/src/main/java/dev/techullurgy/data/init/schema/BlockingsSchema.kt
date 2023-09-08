package dev.techullurgy.data.init.schema

import dev.techullurgy.data.utils.ForeignKey
import dev.techullurgy.data.utils.References

internal const val BlockingsTable = "blockings"

internal object BlockingsTableStructure: TableStructure {
    const val BLOCK_ID: String = "block_id"
    const val BLOCKED_BY: String = "blocked_by"
    const val BLOCKED_USER: String = "blocked_user"
    const val BLOCKED_TIME: String = "blocked_time"
    const val IS_ACTIVE: String = "is_active"

    override val typeMap: Map<String, String>
        get() = HashMap<String, String>().apply {
            put(BLOCK_ID, "bigint(20) auto_increment primary key")
            put(BLOCKED_BY, "int")
            put(BLOCKED_USER, "int")
            put(BLOCKED_TIME, "datetime")
            put(IS_ACTIVE, "bool default true")
        }

    override val foreignKeys: List<ForeignKey>
        get() = listOf(
            ForeignKey(
                columnName = BLOCKED_BY,
                references = References(
                    tableName = UserDetailsTable,
                    columnName = UserDetailsTableStructure.USER_ID
                )
            ),
            ForeignKey(
                columnName = BLOCKED_USER,
                references = References(
                    tableName = UserDetailsTable,
                    columnName = UserDetailsTableStructure.USER_ID
                )
            )
        )
}