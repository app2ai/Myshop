package com.rtech.myshoppy.db.dao

import androidx.room.*
import com.rtech.myshoppy.db.entities.CartModel
import com.rtech.myshoppy.db.entities.ProductAndCart
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

    @Transaction
    @Query("Select * from tblProduct")
    suspend fun getProductsInCart(): List<ProductAndCart>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToCart(products: CartModel)

  /*  @Delete
    suspend fun deleteProductToCart(cartModel: CartModel)

   */
}