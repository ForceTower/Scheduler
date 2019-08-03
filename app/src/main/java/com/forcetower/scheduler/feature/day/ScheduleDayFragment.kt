package com.forcetower.scheduler.feature.day

import androidx.fragment.app.Fragment
import com.forcetower.scheduler.injection.Injectable

class ScheduleDayFragment : Fragment() {
    companion object {
        fun newInstance(day: Long): ScheduleDayFragment {
            return ScheduleDayFragment()
        }
    }
}