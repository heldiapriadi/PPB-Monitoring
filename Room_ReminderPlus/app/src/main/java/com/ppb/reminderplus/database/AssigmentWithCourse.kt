package com.ppb.reminderplus.database

import androidx.room.Embedded
import androidx.room.Relation

data class AssigmentWithCourse(
        @Embedded val course: Course,
        @Relation(
                parentColumn = "course_id",
                entityColumn = "fk_course_id"
        )
        val assigments: List<Assigment>
)