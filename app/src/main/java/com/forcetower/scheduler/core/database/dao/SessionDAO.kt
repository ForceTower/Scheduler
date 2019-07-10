package com.forcetower.scheduler.core.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.forcetower.scheduler.core.model.Session

@Dao
abstract class SessionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<Session>)

    @Query("SELECT * FROM Session")
    abstract fun getSessions(): LiveData<List<Session>>
}