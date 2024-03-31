package com.sulsul.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
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
    val recordId: Int,
    val drinkType: String,
    val quantity: Int
)

fun DrinkInfoEntity.asExternalModel() = DrinkInfo(
    recordId = recordId,
    drinkType = drinkType,
    quantity = quantity
)

fun DrinkInfo.asEntity() = DrinkInfoEntity(
    recordId = recordId,
    drinkType = drinkType,
    quantity = quantity
)
