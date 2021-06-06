package com.ppb.reminderplus.ui.reminder

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InstancesUtility {
    companion object{
        var nameOfEvent = ArrayList<String>()
        var startDates = ArrayList<String>()
        var endDates = ArrayList<String>()
        var descriptions = ArrayList<String>()
        fun readCalendarInstances(context: Context): ArrayList<String> {
            val INSTANCE_PROJECTION: Array<String> = arrayOf(
                    CalendarContract.Instances.EVENT_ID, // 0
                    CalendarContract.Instances.BEGIN, // 1
                    CalendarContract.Instances.TITLE // 2
            )

           val PROJECTION_TITLE_INDEX: Int = 2

            // Specify the date range you want to search for recurring
            // event instances
            val startMillis: Long = Calendar.getInstance().run {
                set(2011, 9, 23, 8, 0)
                timeInMillis
            }
            val endMillis: Long = Calendar.getInstance().run {
                set(2011, 10, 24, 8, 0)
                timeInMillis
            }


            // Construct the query with the desired date range.
            val builder: Uri.Builder = CalendarContract.Instances.CONTENT_URI.buildUpon()
            ContentUris.appendId(builder, startMillis)
            ContentUris.appendId(builder, endMillis)

            // Submit the query
            val cur: Cursor? = context.contentResolver.query(
                    builder.build(),
                    INSTANCE_PROJECTION,
                    null,
                    null, null
            )

            while (cur!!.moveToNext()) {
                // Get the field values
//                val eventID: Long = cur.getLong(PROJECTION_ID_INDEX)
//                val beginVal: Long = cur.getLong(PROJECTION_BEGIN_INDEX)
                val title: String = cur.getString(PROJECTION_TITLE_INDEX)

//                // Do something with the values.
//                Log.i(DEBUG_TAG, "Event: $title")
//                val calendar = Calendar.getInstance().apply {
//                    timeInMillis = beginVal
//                }
//                val formatter = SimpleDateFormat("MM/dd/yyyy")
//                Log.i(DEBUG_TAG, "Date: ${formatter.format(calendar.time)}")

                nameOfEvent.add(title)
            }

            return nameOfEvent
        }

        fun getDate(milliSeconds: Long): String? {
            val formatter = SimpleDateFormat(
                    "dd/MM/yyyy hh:mm:ss a")
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(milliSeconds)

            return formatter.format(calendar.getTime())
        }
    }
}