package com.rtech.myshoppy.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rtech.myshoppy.db.entities.UserDetailsModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDetailsModel): Long

    @Query("Select * from tblUser where :mobile = mobile and :pass = password")
    suspend fun loginUser(mobile: String, pass:String): UserDetailsModel?

    @Update
    suspend fun updateLoginStatue(user: UserDetailsModel)

    @Query("Update tblUser Set isLogin = 0 where id = :userId")
    suspend fun logoutUser(userId: Int): Int

    @Query("Select * from tblUser where id = :uId")
    suspend fun getUserDetails(uId: Int): UserDetailsModel
}