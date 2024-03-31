package com.sulsul.core.database.model

import androidx.room.Entity

@Entity
data class UserEntity(
    val id: String,
    val pw: String
)
