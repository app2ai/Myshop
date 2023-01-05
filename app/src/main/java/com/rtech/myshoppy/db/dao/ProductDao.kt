package com.rtech.myshoppy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.db.entities.UserDetailsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(products: ProductDetailsModel)

    @Query("DELETE FROM tblProduct")
    suspend fun deleteAllProducts()

    @Query("Select * from tblProduct")
    suspend fun getAllProducts(): List<ProductDetailsModel>

    @Query("Select * from tblProduct where :productId = id")
    suspend fun getSelectedProduct(productId: Int): ProductDetailsModel?

}