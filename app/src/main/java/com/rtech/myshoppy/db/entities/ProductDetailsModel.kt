package com.rtech.myshoppy.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

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

@Entity(tableName = "tblCart")
data class CartModel(
    @PrimaryKey(autoGenerate = true)
    val cartId: Int = 0,
    var productId: Int? = null
)

data class ProductAndCart(
    @Embedded val product: ProductDetailsModel?,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val cart: CartModel?
)