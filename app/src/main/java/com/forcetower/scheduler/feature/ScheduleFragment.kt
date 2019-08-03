package com.forcetower.scheduler.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.forcetower.scheduler.databinding.FragmentEventScheduleBinding
import com.forcetower.scheduler.feature.day.ScheduleDayFragment
import com.forcetower.scheduler.injection.Injectable
import com.forcetower.scheduler.injection.ScheduleViewModelFactory
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class ScheduleFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentEventScheduleBinding
    private lateinit var viewModel: SessionViewModel
    @Inject
    lateinit var factory: ScheduleViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(SessionViewModel::class.java)
        return FragmentEventScheduleBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ScheduleDayAdapter(childFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        viewModel.events.observe(this, Observer { vals -> println(vals) })
        viewModel.eventDates.observe(this, Observer { dates ->
            adapter.dates = dates.toMutableList()
        })
    }

    inner class ScheduleDayAdapter(
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        var dates = mutableListOf<Long>()
        set(value) {
            dates.clear()
            dates.addAll(value)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int) = ScheduleDayFragment.newInstance(dates[position])

        override fun getCount() = dates.size

        override fun getPageTitle(position: Int): CharSequence? {
            val time = ZonedDateTime.ofInstant(Instant.ofEpochSecond(dates[position]), ZoneId.systemDefault())
            return time.format(DateTimeFormatter.ISO_LOCAL_DATE)
        }

    }
}