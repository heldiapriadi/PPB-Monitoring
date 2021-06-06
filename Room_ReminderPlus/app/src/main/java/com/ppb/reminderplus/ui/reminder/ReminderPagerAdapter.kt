package com.ppb.reminderplus.ui.reminder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ppb.reminderplus.ui.event.EventFragment
import com.ppb.reminderplus.ui.schedule.ScheduleFragment
import com.ppb.reminderplus.ui.study.StudyFragment

class ReminderPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        ScheduleFragment(),
        StudyFragment(),
        EventFragment(),
    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Schedule Tab"
            1 -> "Study Tab"
            2 -> "Event Tab"
            else -> "Tab"
        }
    }
}