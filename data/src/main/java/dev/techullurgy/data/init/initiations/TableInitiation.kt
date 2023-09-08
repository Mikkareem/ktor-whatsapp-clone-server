package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.createTable
import dev.techullurgy.data.init.isTableExists
import dev.techullurgy.data.init.schema.TableStructure
import org.ktorm.database.Database

internal interface TableInitiation {
    val tableName: String
    val tableStructure: TableStructure
    suspend fun initiate(database: Database): Boolean
        = if(!database.isTableExists(tableName)) createTable(database, printSql = true) else true
}