package com.rtech.myshoppy.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class UserDetailsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var uname: String,
    var age: String,
    var mobile: String,
    var password: String
)