package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.ext.createTable
import dev.techullurgy.data.init.ext.isTableExists
import dev.techullurgy.data.init.schema.TableStructure
import org.ktorm.database.Database

internal interface TableInitiation {
    val tableName: String
    val tableStructure: TableStructure
    suspend fun initiate(database: Database): Boolean
        = if(!database.isTableExists(tableName)) createTable(database, printSql = true) else true
}