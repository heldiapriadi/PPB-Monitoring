package com.ppb.reminderplus.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class AssigmentRepository(private val assigmentDao: AssigmentDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allAssigments: Flow<List<Assigment>> = assigmentDao.getAssigments()

    val assigmentsAndCourse: Flow<List<AssigmentWithCourse>> = assigmentDao.getAssigmentWithCourse()

    val findAllAssigmentCourse: Flow<List<AssigmentAndCourse>> = assigmentDao.findAllssigmentCourse()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAssigment(assigment: Assigment) {
        assigmentDao.insertAssigment(assigment)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCourse(course: Course) {
        assigmentDao.insertCourse((course))
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAssigment(assigmentId: Int) {
        assigmentDao.deleteAssigment(assigmentId)
    }
}