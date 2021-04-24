package com.ppb.reminderplus.data

import com.ppb.reminderplus.R
import com.ppb.reminderplus.type.Assigment
import com.ppb.reminderplus.type.Course
import com.ppb.reminderplus.type.Priority
import java.util.ArrayList

object AssigmentData {
    private val titles = arrayOf(
            "Tugas PPB",
            "Tugas APSI",
            "Tugas Dosen",
            "Tugas PKN",
            "Tugas MTK",
            "TUgas PPL1"
    )

    private val descriptions = arrayOf(
            "None",
            "Tidak Ada",
            "1234567890",
            "!@#$%^&*()",
            "QWERTYUIOP{",
            "ASDFGHJKL"
    )

    private val courses = arrayOf(
            Course("PPB", R.drawable.mobila_programming),
            Course("APSI",R.drawable.sistem_informasi),
            Course("Dosen", 0),
            Course("PKN", 0),
            Course("MTK", 0),
            Course("PPL1", 0)
    )

    private  val  taskPriority = arrayOf(
            Priority.LOW,
            Priority.HIGH,
            Priority.NORMAL,
            Priority.LOW,
            Priority.HIGH,
            Priority.NORMAL
    )

    private  val taskDate = arrayOf(
            "18/07/2019 16:20",
            "18/08/2019 16:20",
            "18/07/2019 16:20",
            "18/08/2019 16:20",
            "18/07/2019 16:20",
            "18/08/2019 16:20",
    )

    private val taskNotify = arrayOf(
            2,
            1,
            2,
            1,
            2,
            1,
    )

    private val statusAssigment = arrayOf(
            0,
            0,
            1,
            0,
            0,
            1,
    )

    val listData: ArrayList<Assigment>
        get() {
            val list = arrayListOf<Assigment>()
            for (position in titles.indices) {
                val assigment = Assigment()
                assigment.title = titles[position]
                assigment.course = courses[position]
                assigment.date = taskDate[position]
                assigment.description = descriptions[position]
                assigment.notify = taskNotify[position]
                assigment.priority = taskPriority[position]
                assigment.status = statusAssigment[position]
                list.add(assigment)
            }
            return list
        }
}