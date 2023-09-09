package dev.techullurgy.data.init.initiations

import dev.techullurgy.data.init.schema.TableStructure
import dev.techullurgy.data.init.schema.UserDetailsTable
import dev.techullurgy.data.init.schema.UserDetailsTableStructure

internal object UserDetailsTableInitiation: TableInitiation {
    override val tableName: String
        get() = UserDetailsTable

    override val tableStructure: TableStructure
        get() = UserDetailsTableStructure
}