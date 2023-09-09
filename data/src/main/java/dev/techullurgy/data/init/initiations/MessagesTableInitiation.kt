package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.schema.MessagesTable
import dev.techullurgy.data.init.schema.MessagesTableStructure
import dev.techullurgy.data.init.schema.TableStructure

internal object MessagesTableInitiation: TableInitiation {
    override val tableName: String
        get() = MessagesTable

    override val tableStructure: TableStructure
        get() = MessagesTableStructure
}