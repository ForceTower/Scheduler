package com.forcetower.scheduler.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forcetower.scheduler.feature.SessionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SessionViewModel::class)
    abstract fun bindSessionViewModel(vm: SessionViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ScheduleViewModelFactory): ViewModelProvider.Factory
}