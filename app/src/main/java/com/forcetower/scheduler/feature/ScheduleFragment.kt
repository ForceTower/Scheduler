package com.forcetower.scheduler.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.forcetower.scheduler.databinding.FragmentEventScheduleBinding

class ScheduleFragment : Fragment() {
    private lateinit var binding: FragmentEventScheduleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentEventScheduleBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }
}