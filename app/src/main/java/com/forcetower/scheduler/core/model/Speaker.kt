package com.forcetower.scheduler.core.model

data class Speaker(
    val id: Int,
    val name: String,
    val picture: String,
    val description: String,
    val github: String?,
    val facebook: String?
)