package com.techullurgy.data.entities

import com.techullurgy.data.model.database.User
import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import java.time.LocalDateTime

interface UserDetail: Entity<UserDetail> {
    companion object: Entity.Factory<UserDetail>()
    val name: String
    val id: Long
    val email: String
    val password: String
    val registeredAt: LocalDateTime
    val lastLogin: LocalDateTime?

    fun toUser(): User {
        return User(id, name, email, password)
    }
}


open class UserDetails(alias: String?): Table<UserDetail>(UserDetailsTable, alias) {
    companion object : UserDetails(null)
    override fun aliased(alias: String): Table<UserDetail> = UserDetails(alias)

    val id = long(UserDetailsColumns.USER_ID).primaryKey().bindTo { it.id }
    val name = varchar(UserDetailsColumns.USER_NAME).bindTo { it.name }
    val email = varchar(UserDetailsColumns.EMAIL_ID).bindTo { it.email }
    val password = varchar(UserDetailsColumns.PASSWORD).bindTo { it.password }
    val registeredAt = datetime(UserDetailsColumns.REGISTERED_AT).bindTo { it.registeredAt }
    val lastLogin = datetime(UserDetailsColumns.LAST_LOGIN).bindTo { it.lastLogin }
}

val Database.userDetails get() = this.sequenceOf(UserDetails)
