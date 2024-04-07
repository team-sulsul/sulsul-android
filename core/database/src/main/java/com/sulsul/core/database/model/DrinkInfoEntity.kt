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
    val id: Int = 0,
    val recordId: Int = 0,
    val drinkType: String,
    val quantity: Int
)

fun DrinkInfoEntity.asExternalModel() = DrinkInfo(
    drinkType = drinkType,
    quantity = quantity
)

fun DrinkInfo.asEntity(recordId: Int) = DrinkInfoEntity(
    recordId = recordId,
    drinkType = drinkType,
    quantity = quantity
)
