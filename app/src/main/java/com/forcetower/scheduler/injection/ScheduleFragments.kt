package com.forcetower.scheduler.injection

import com.forcetower.scheduler.feature.ScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScheduleFragmentsModule {
    @ContributesAndroidInjector
    abstract fun schedule(): ScheduleFragment
}
