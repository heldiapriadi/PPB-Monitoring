package com.ppb.reminderplus

import java.util.ArrayList

object AssigmentData {
    private val titles = arrayOf(
            "Tugas PPB",
            "Tugas APSI"
    )

    private val descriptions = arrayOf(
            "None",
            "Tidak Ada"
    )

    private val courses = arrayOf(
            Course("PPB",0),
            Course("PPB",0)
    )

    private  val  taskPriority = arrayOf(
            Priority.LOW,
            Priority.HIGH
    )

    private  val taskDate = arrayOf(
            "04-04-2021",
            "04-05-2021"
    )

    private val taskNotify = arrayOf(
            1,
            2
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
                list.add(assigment)
            }
            return list
        }
}