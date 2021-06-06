package com.ppb.reminderplus.database

import android.graphics.Bitmap
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface AssigmentDao {
    @Query("SELECT * FROM assigments ORDER BY task_date ASC")
    fun getAssigments(): Flow<List<Assigment>>

    @Query("SELECT * FROM courses ORDER BY name ASC")
    fun getCourses(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssigment(assigment: Assigment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Query("DELETE FROM assigments")
    suspend fun deleteAllAssigment()

    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()

    @Query("SELECT photo FROM courses WHERE course_id= :courseId")
    fun getPhoto(courseId: Int): Bitmap?

    @Query("SELECT assigments.*, courses.* FROM assigments INNER JOIN courses ON fk_course_id = course_id")
    fun findAllssigmentCourse(): Flow<List<AssigmentAndCourse>>

    @Transaction
    @Query("SELECT * FROM courses")
    fun getAssigmentWithCourse(): Flow<List<AssigmentWithCourse>>

    @Transaction
    @Query("SELECT * FROM courses WHERE name = :name")
    suspend fun getAssigmentAndCourseWithName(name: String): List<AssigmentWithCourse>

    @Query("DELETE FROM assigments WHERE assigment_id = :assigment_id")
    suspend fun deleteAssigment(assigment_id: Int)

}