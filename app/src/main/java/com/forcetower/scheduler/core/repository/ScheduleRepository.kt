package com.forcetower.scheduler.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.forcetower.scheduler.core.database.EventDB
import com.forcetower.scheduler.core.model.Session
import com.forcetower.scheduler.core.service.EventAPI
import java.util.concurrent.Executor
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val service: EventAPI,
    private val executor: Executor,
    private val database: EventDB
) {

    fun getEvents(): LiveData<List<Session>> {
        executor.execute {
            val call = service.listSessions()
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val list = response.body()
                    val corrected = list ?: emptyList()
                    database.session().insert(corrected)
                }
            } catch (throwable: Throwable) { }
        }
        return database.session().getSessions()
    }
}