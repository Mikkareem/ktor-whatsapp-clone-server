package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.schema.BlockingsTable
import dev.techullurgy.data.init.schema.BlockingsTableStructure
import dev.techullurgy.data.init.schema.TableStructure

internal object BlockingsTableInitiation: TableInitiation {
    override val tableName: String
        get() = BlockingsTable
    override val tableStructure: TableStructure
        get() = BlockingsTableStructure
}