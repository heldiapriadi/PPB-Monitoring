package com.ppb.reminderplus.database

import androidx.room.Embedded
import androidx.room.Relation

data class AssigmentAndCourse(
        @Embedded
        val course: Course,
        @Embedded
        val assigments: Assigment
)