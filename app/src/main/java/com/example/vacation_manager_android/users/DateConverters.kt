package com.example.vacation_manager_android.users

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.sql.Date
//creamos una clase convertidora de datos tipo long a Date y viceversa
@ProvidedTypeConverter
class DateConverters{
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun toTimestamp(value: Date?): Long?{
        return value?.let { value.time }
    }
}