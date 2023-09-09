package dev.techullurgy.domain.models

import dev.techullurgy.data.model.database.servables.BlockingDTO

internal data class Blocking(
    val blockedBy: User,
    val blockedUser: User
)

internal fun Blocking.toBlockingDTO(): BlockingDTO = BlockingDTO(blockedBy = blockedBy.toUserDTO(), blockedUser = blockedUser.toUserDTO())