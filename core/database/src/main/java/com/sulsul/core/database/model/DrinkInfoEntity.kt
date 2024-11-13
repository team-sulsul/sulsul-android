package com.sulsul.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sulsul.core.model.DrinkInfo

@Entity(
    tableName = "record_drink",
    foreignKeys = [
        ForeignKey(
            entity = DrinkRecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["recordId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DrinkInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val recordId: Long = 0L,
    val drinkType: String,
    val quantity: Int
)

fun DrinkInfoEntity.asExternalModel() = DrinkInfo(
    recordId = recordId,
    drinkType = drinkType,
    quantity = quantity
)

fun DrinkInfo.asEntity(recordId: Long) = DrinkInfoEntity(
    recordId = recordId,
    drinkType = drinkType,
    quantity = quantity
)
