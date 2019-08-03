package com.forcetower.scheduler.feature

import androidx.lifecycle.ViewModel
import com.forcetower.scheduler.core.repository.ScheduleRepository
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {
    val events = repository.getEvents()
    val eventDates = repository.getEventDates()
}