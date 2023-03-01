package com.rtech.myshoppy.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.entities.CartModel
import com.rtech.myshoppy.db.entities.ProductAndCart
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppyDashboardViewModel : ViewModel() {
    private var _productsLiveData = MutableLiveData<List<ProductDetailsModel>>()
    val productsLiveData: LiveData<List<ProductDetailsModel>> = _productsLiveData

    private var _productLiveData = MutableLiveData<ProductDetailsModel>()
    val productLiveData: LiveData<ProductDetailsModel> = _productLiveData

    private var _cartFlow = MutableStateFlow<List<ProductAndCart>?>(null)
    val cartFlow: StateFlow<List<ProductAndCart>?> = _cartFlow

    fun getAllProductsFromDb(context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, this)
            _productsLiveData.value = db.productDao().getAllProducts()
        }
    }

    fun getSelectedProductsFromDb(context: Context, productId: Int) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, this)
            _productLiveData.value = db.productDao().getSelectedProduct(productId)
        }
    }

    fun getCartProductsFromDb(context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, this)
            _cartFlow.value = db.productDao().getProductsInCart()
        }
    }

    fun addProductToCart(productId: Int, context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, this)
            db.productDao().addProductToCart(CartModel(productId = productId))
        }
    }
    /*
    fun deleteProductToCart(productId: Int, context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, this)
            db.productDao().deleteProductToCart(CartModel(productId = productId))
        }
    }

     */
}