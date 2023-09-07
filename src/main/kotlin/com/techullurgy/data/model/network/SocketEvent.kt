package com.techullurgy.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class Event<T>(
    val event: String,
    val data: @Serializable T
)