package com.rtech.myshoppy.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblProduct")
data class ProductDetailsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productName: String,
    val originalPrice: Double,
    val sellingPrice: Double,
    val productImagePath: String,
    val productDesc: String,
    val rating: Int = 0,
    val discount: Int
)