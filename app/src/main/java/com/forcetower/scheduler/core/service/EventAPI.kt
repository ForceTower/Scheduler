package com.forcetower.scheduler.core.service

import com.forcetower.scheduler.core.model.Session
import retrofit2.Call
import retrofit2.http.GET

interface EventAPI {
    @GET("siecomp/list_sessions")
    fun listSessions(): Call<List<Session>>

}