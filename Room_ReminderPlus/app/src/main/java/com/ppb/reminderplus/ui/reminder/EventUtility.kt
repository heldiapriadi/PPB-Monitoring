package com.ppb.reminderplus.ui.reminder

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log
import com.ppb.reminderplus.type.Event
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventUtility {
    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    companion object{
        var nameOfEvent = ArrayList<String>()
        var startDates = ArrayList<String>()
        var endDates = ArrayList<String>()
        var descriptions = ArrayList<String>()
        var eventList = ArrayList<Event>()
        fun readCalendarEvent(context: Context): ArrayList<Event> {
            val EVENT_PROJECTION: Array<String> = arrayOf(
                CalendarContract.Events.CALENDAR_ID,                     // 0
                CalendarContract.Events.TITLE,            // 1
                CalendarContract.Events.DESCRIPTION,   // 2
                CalendarContract.Events.DTSTART,           // 3
                CalendarContract.Events.DTEND,
                CalendarContract.Events.EVENT_LOCATION,
            )

            // Run query
            val uri: Uri = CalendarContract.Events.CONTENT_URI
            val cursor: Cursor? = context.contentResolver.query(
                uri,
                EVENT_PROJECTION,
                null,
                null,
                CalendarContract.Events.DTSTART + " ASC"
            )

            cursor?.moveToFirst()
            // fetching calendars name
            val CNames = arrayOfNulls<String>(cursor!!.getCount())
            // fetching calendars id
            nameOfEvent.clear()
            startDates.clear()
            endDates.clear()
            descriptions.clear()
            for (i in CNames.indices) {
                val now = cursor.getString(3).toLong()  - Calendar.getInstance().timeInMillis
                Log.d("MyTag", cursor.getString(1) + "=" + now.toString() + " TEST")
                if(now>=0){
                    val event:Event = Event(
                        cursor.getString(1),
                        getDate(cursor.getString(3).toLong())!!,
                        getDate(cursor.getString(4).toLong())!!,
                        cursor.getString(2)
                    )
//                    nameOfEvent.add(cursor.getString(1))
//                    startDates.add(getDate(cursor.getString(3).toLong())!!)
//                    endDates.add(getDate(cursor.getString(4).toLong())!!)
//                    descriptions.add(cursor.getString(2))
                    eventList.add(event)
                    CNames[i] = cursor.getString(1)
                }
                cursor.moveToNext()
            }
            return eventList
        }

        fun getDate(milliSeconds: Long): String? {
            val formatter = SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a"
            )
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(milliSeconds)

            return formatter.format(calendar.getTime())
        }
    }

}