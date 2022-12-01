package com.rtech.myshoppy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.rtech.myshoppy.db.entities.UserDetailsModel

@Dao
interface UserDao {
    @Insert
    fun addUser(user: UserDetailsModel)
}