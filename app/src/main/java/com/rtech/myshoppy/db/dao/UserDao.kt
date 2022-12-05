package com.rtech.myshoppy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rtech.myshoppy.db.entities.UserDetailsModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDetailsModel): Long

    @Query("Select * from tblUser where :mobile = mobile and :pass = password")
    suspend fun loginUser(mobile: String, pass:String): UserDetailsModel?
}