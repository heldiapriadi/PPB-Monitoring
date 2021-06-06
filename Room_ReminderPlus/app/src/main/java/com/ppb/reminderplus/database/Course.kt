package com.ppb.reminderplus.database

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "course_id")
        val course_id: Int,

        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "photo") val photo: Bitmap?
)