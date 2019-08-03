package com.forcetower.scheduler.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.forcetower.scheduler.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class EventScheduleActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_schedule)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ScheduleFragment(), "schedule")
                .commit()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return androidInjector
    }
}