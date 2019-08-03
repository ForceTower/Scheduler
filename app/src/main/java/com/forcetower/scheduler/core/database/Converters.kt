package com.forcetower.scheduler.core.database

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime

object Converters {
    @TypeConverter
    @JvmStatic
    fun zonedToString(zonedDateTime: ZonedDateTime): String {
        return zonedDateTime.toString()
    }

    @TypeConverter
    @JvmStatic
    fun stringToZoned(string: String): ZonedDateTime {
        return ZonedDateTime.parse(string)
    }
}