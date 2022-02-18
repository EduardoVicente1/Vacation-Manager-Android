package com.example.vacation_manager_android.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "workers")
data class Workers (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "Nombres") val username: String?,
    @ColumnInfo(name = "CI") val ci: Int?,
    @ColumnInfo(name = "Correo") val correo: String?,
    @ColumnInfo(name = "Equipo") val equipo: String?,
    @ColumnInfo(name = "Fecha de Ingreso") val fecha_ingreso: Date?,
    @ColumnInfo(name = "Antigüedad AÑOS") val antig_age: Int?
)