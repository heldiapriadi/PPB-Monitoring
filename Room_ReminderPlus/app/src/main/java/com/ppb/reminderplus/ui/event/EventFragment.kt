package com.ppb.reminderplus.ui.event

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppb.reminderplus.ParentAssigmentAdapter
import com.ppb.reminderplus.R
import com.ppb.reminderplus.type.Event
import com.ppb.reminderplus.ui.reminder.EventUtility

class EventFragment : Fragment() {
    private lateinit var rvEvent: RecyclerView
    var listNameofEvent = ArrayList<Event>()
    private val REQUEST_READ_CALENDER = 123
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_event, container, false)

        rvEvent = root.findViewById(R.id.rv_event)
        rvEvent.setHasFixedSize(true)

        if (ContextCompat.checkSelfPermission(requireActivity().applicationContext, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CALENDAR), REQUEST_READ_CALENDER)
        } else {
            listNameofEvent = EventUtility.readCalendarEvent(requireActivity().applicationContext)
        }

        val adapter = EventAdapter(listNameofEvent)
        rvEvent.adapter = adapter
        rvEvent.layoutManager = LinearLayoutManager(context)

        return root


    }
}