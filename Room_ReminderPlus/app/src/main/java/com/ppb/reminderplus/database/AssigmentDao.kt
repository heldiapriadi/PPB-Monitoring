package com.ppb.reminderplus.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface AssigmentDao {
    @Query("SELECT * FROM assigments ORDER BY title ASC")
    fun getAssigments(): Flow<List<Assigment>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(assigment: Assigment)

    @Query("DELETE FROM assigments")
    suspend fun deleteAll()
}