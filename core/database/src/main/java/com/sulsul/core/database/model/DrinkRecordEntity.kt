package com.sulsul.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import java.time.LocalDate

@Entity(tableName = "record")
data class DrinkRecordEntity(
    @PrimaryKey
    val id: Int,
    val recordedAt: LocalDate,
    val drunkennessLevel: String,
    val drinks: List<DrinkInfo>
)

fun DrinkRecordEntity.asExternalModel() = DrinkRecord(
    id = id,
    recordedAt = recordedAt,
    drunkennessLevel = drunkennessLevel,
    drinks = drinks
)

fun DrinkRecord.asEntity() = DrinkRecordEntity(
    id = id,
    recordedAt = recordedAt,
    drunkennessLevel = drunkennessLevel,
    drinks = drinks
)
