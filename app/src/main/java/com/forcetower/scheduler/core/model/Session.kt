package com.forcetower.scheduler.core.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Session(
    @PrimaryKey
    val id: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    val title: String,
    val room: String,
    val abstract: String,
    @SerializedName("photo_url")
    val picture: String?,
    val type: Int,
    val uuid: String
)