package dev.techullurgy.data.utils

internal data class ForeignKey(
    val columnName: String,
    val references: References
)

internal data class References(
    val tableName: String,
    val columnName: String
)
