package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.schema.MessagesTable
import dev.techullurgy.data.init.schema.MessagesTableStructure
import dev.techullurgy.data.init.schema.TableStructure
import org.ktorm.database.Database

internal object MessagesTableInitiation: TableInitiation {
    override val tableName: String
        get() = MessagesTable

    override val tableStructure: TableStructure
        get() = MessagesTableStructure

    override suspend fun initiate(database: Database): Boolean = super.initiate(database)
}