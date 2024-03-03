package com.sulsul.core.database.dao

import androidx.room.Dao

@Dao
interface UserDao {
    fun getUserList()
}