package com.example.vacation_manager_android.users

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface WorkersDAO {
    @Insert
    fun insertWorkers(vararg workers: Workers)

    @Delete
    fun deleteWorkers(workers: Workers)

    @Update
    fun updateWorkers(workers: Workers)

    @Query("SELECT * FROM workers")
    fun getAllWorkers(): List<Workers>

    @Query("SELECT * FROM workers WHERE Nombres LIKE :username")
    fun findWorkersByName(username: String): Workers
}