package com.forcetower.scheduler.injection

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.forcetower.scheduler.BaseApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {
    fun inject(application: BaseApplication): AppComponent {
        val component = DaggerAppComponent.builder()
            .application(application)
            .build()

        component.inject(application)

        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) = Unit
            override fun onActivityResumed(activity: Activity?) = Unit
            override fun onActivityStarted(activity: Activity?) = Unit
            override fun onActivityDestroyed(activity: Activity?) = Unit
            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit
            override fun onActivityStopped(activity: Activity?) = Unit
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }
        })

        return component
    }

    private fun handleActivity(activity: Activity) {
        if (activity is AppCompatActivity && activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                        super.onFragmentAttached(fm, f, context)
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
            }, true)

        }
    }
}