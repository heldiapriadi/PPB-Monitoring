package com.ppb.reminderplus.database

import androidx.annotation.NonNull
import androidx.room.*
import java.util.*


@Entity(
    tableName = "assigments",
)
data class Assigment(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "assigment_id")
    val assigment_id: Int,

    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "task_priority") val taskPriority: String,
    @ColumnInfo(name = "task_date") val taskDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "task_notify") val taskNotify: String,
    @ColumnInfo(name = "status_assigment") val statusAssigment: String,

    @ColumnInfo(name = "fk_course_id") val fkCourseId: Int,
)