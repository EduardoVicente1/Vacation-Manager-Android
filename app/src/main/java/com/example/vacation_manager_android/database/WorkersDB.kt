package com.example.vacation_manager_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vacation_manager_android.users.Workers
import com.example.vacation_manager_android.users.WorkersDAO

@Database(entities = [Workers::class], version = 1)
abstract class WorkersDB : RoomDatabase(){
    abstract fun workersDAO(): WorkersDAO
    companion object{
    private var INSTANCE: WorkersDB? = null

        fun getInstance(context: Context): WorkersDB?{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, WorkersDB::class.java, "database.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}