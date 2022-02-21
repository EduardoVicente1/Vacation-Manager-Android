package com.example.vacation_manager_android.users

import androidx.room.*

@Dao
interface WorkersDAO {
    @Insert
    fun insertWorkers(vararg workers: Workers)

    @Delete
    fun deleteWorkers(workers: Workers)

    @Update
    fun updateWorkers(workers: Workers)

    @Query("SELECT * FROM workers")
    fun getAllWorkers(): List<Workers>

    @Query("SELECT * FROM workers WHERE username LIKE :username")
    fun findWorkersByName(username: String): Workers

}