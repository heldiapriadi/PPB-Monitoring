package com.ppb.reminderplus.ui.reminder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.ppb.reminderplus.R
import com.ppb.reminderplus.type.Event
import com.ppb.reminderplus.ui.reminder.EventUtility.Companion.readCalendarEvent
import com.ppb.reminderplus.ui.reminder.InstancesUtility.Companion.readCalendarInstances



class ReminderFragment : Fragment() {

    private lateinit var reminderPagerAdapter: ReminderPagerAdapter
    private lateinit var viewPager: ViewPager



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text ="Reminder"
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reminderPagerAdapter = ReminderPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.view_page_reminder)
        viewPager.adapter = reminderPagerAdapter
    }
}