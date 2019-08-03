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
            println("Elevated")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val list = response.body()
                    println("The network is $list")
                    val corrected = list ?: emptyList()
                    database.session().insert(corrected)
                } else {
                    println("The network code ${response.code()}")
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
        return database.session().getSessions()
    }

    fun getEventDates(): LiveData<List<Long>> {
        val data = MutableLiveData<List<Long>>()
        executor.execute {
            val sessions = database.session().getSessionsDirect()
            val list = sessions.distinctBy { it.startTime.dayOfYear }
                .map { it.startTime.toInstant().epochSecond }
            data.postValue(list)
        }
        return data
    }
}