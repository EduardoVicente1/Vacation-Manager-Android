package com.example.vacation_manager_android.users

import android.app.DatePickerDialog
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "workers")
data class Workers (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "ci") val ci: Int?,
    @ColumnInfo(name = "correo") val correo: String?,
    @ColumnInfo(name = "equipo") val equipo: String?,
    @ColumnInfo(name = "fecha_ingreso") val fecha_ingreso: Long?,
    @ColumnInfo(name = "antig_age") val antig_age: Int?
)