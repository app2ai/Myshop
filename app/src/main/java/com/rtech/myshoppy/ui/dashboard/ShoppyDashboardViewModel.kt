package com.rtech.myshoppy.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import kotlinx.coroutines.launch

class ShoppyDashboardViewModel : ViewModel() {
    private var _productsLiveData = MutableLiveData<List<ProductDetailsModel>>()
    val productsLiveData: LiveData<List<ProductDetailsModel>> = _productsLiveData

    fun getAllProductsFromDb(context: Context){
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context, viewModelScope)
            _productsLiveData.value =  db.productDao().getAllProducts()
        }
    }
}