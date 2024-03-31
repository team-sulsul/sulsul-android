package com.sulsul.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
