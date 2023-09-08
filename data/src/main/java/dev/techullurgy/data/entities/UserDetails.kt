package dev.techullurgy.data.entities

import dev.techullurgy.data.init.schema.UserDetailsTable
import dev.techullurgy.data.init.schema.UserDetailsTableStructure
import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import java.time.LocalDateTime

internal interface UserDetail: Entity<UserDetail> {
    companion object: Entity.Factory<UserDetail>()
    val name: String
    val id: Long
    val email: String
    val password: String
    val registeredAt: LocalDateTime
    val lastLogin: LocalDateTime?
}


internal open class UserDetails(alias: String?): Table<UserDetail>(UserDetailsTable, alias) {
    companion object : UserDetails(null)

    override fun aliased(alias: String): Table<UserDetail> = UserDetails(alias)

    val id = long(UserDetailsTableStructure.USER_ID).primaryKey().bindTo { it.id }
    val name = varchar(UserDetailsTableStructure.USER_NAME).bindTo { it.name }
    val email = varchar(UserDetailsTableStructure.EMAIL_ID).bindTo { it.email }
    val password = varchar(UserDetailsTableStructure.PASSWORD).bindTo { it.password }
    val registeredAt = datetime(UserDetailsTableStructure.REGISTERED_AT).bindTo { it.registeredAt }
    val lastLogin = datetime(UserDetailsTableStructure.LAST_LOGIN).bindTo { it.lastLogin }
}

internal val Database.userDetails get() = this.sequenceOf(UserDetails)
