package com.forcetower.scheduler.injection

import androidx.lifecycle.ViewModel
import com.forcetower.scheduler.feature.SessionViewModel
import dagger.MapKey
import java.lang.annotation.ElementType
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
