package com.ppb.reminderplus

import java.time.LocalDateTime
import java.util.*


data class Assigment(
        var title: String = "",
        var description: String = "",
        var course: Course = Course("",0),
        var priority: Priority = Priority.NORMAL,
        var date: String = "",
        var notify: Int = 0
)
