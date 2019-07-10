package com.forcetower.scheduler.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.forcetower.scheduler.core.database.dao.SessionDAO
import com.forcetower.scheduler.core.model.Session

@Database(entities = [
    Session::class
], version = 1, exportSchema = true)
abstract class EventDB : RoomDatabase() {
    abstract fun session(): SessionDAO
}