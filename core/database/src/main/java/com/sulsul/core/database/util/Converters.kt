package com.sulsul.core.database.util

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun localDateToLong(value: LocalDate?): Long? {
        return value?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
    }

    @TypeConverter
    fun longToLocalDate(value: Long?): LocalDate? {
        return value?.let { Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate() }
    }
}
