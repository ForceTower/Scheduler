package com.forcetower.scheduler.core.model

data class Session(
    val id: Int,
    val title: String,
    val description: String,
    val room: String,
    val picture: String?
)