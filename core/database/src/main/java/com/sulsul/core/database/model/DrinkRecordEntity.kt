package com.sulsul.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sulsul.core.model.DrinkRecord
import java.time.LocalDate

@Entity(tableName = "record")
data class DrinkRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recordedAt: LocalDate,
    val drunkennessLevel: String
)

fun DrinkRecordEntity.asExternalModel() = DrinkRecord(
    id = id,
    recordedAt = recordedAt,
    drunkennessLevel = drunkennessLevel,
    drinks = emptyList()
)

fun DrinkRecord.asEntity() = DrinkRecordEntity(
    recordedAt = recordedAt,
    drunkennessLevel = drunkennessLevel,
)