package dev.techullurgy.data.entities

import dev.techullurgy.data.init.schema.BlockingsTable
import dev.techullurgy.data.init.schema.BlockingsTableStructure
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.datetime
import org.ktorm.schema.long
import java.time.LocalDateTime

internal interface BlockingsEntity: Entity<BlockingsEntity> {
    companion object: Entity.Factory<BlockingsEntity>()
    val blockId: Long
    val blockedBy: UserDetail
    val blockedUser: UserDetail
    val blockedTime: LocalDateTime
    val isActive: Boolean
}

internal open class Blockings(alias: String?): Table<BlockingsEntity>(BlockingsTable, alias) {
    companion object: Blockings(null)

    override fun aliased(alias: String): Table<BlockingsEntity> = Blockings(alias)

    val blockId = long(BlockingsTableStructure.BLOCK_ID).primaryKey().bindTo { it.blockId }
    val blockedBy = long(BlockingsTableStructure.BLOCKED_BY).references(UserDetails) { it.blockedBy }
    val blockedUser = long(BlockingsTableStructure.BLOCKED_USER).references(UserDetails) { it.blockedUser }
    val blockTime = datetime(BlockingsTableStructure.BLOCKED_TIME).bindTo { it.blockedTime }
    val isActive = boolean(BlockingsTableStructure.IS_ACTIVE).bindTo { it.isActive }
}