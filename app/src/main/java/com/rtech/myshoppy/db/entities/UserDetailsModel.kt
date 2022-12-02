package com.rtech.myshoppy.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblUser")
data class UserDetailsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uname: String,
    val age: String,
    val mobile: String,
    val password: String
)