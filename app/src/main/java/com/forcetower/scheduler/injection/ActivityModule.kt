package com.forcetower.scheduler.injection

import com.forcetower.scheduler.MainActivity
import com.forcetower.scheduler.feature.EventScheduleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [ScheduleFragmentsModule::class])
    abstract fun eventSchedule(): EventScheduleActivity
}