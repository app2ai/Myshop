package com.rtech.myshoppy.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtech.myshoppy.cache.CachePref
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.entities.UserDetailsModel
import com.rtech.myshoppy.utils.SingleLiveDataEvent
import kotlinx.coroutines.launch

class ShoppyLoginViewModel : ViewModel() {
    private var _userLiveData = MutableLiveData<UserLoginState>()
    val userLiveData: LiveData<UserLoginState> = _userLiveData

    private var _userLogoutLiveData = SingleLiveDataEvent<Boolean>()
    val userLogoutLiveData: LiveData<Boolean> = _userLogoutLiveData

    fun loginUserFromDb(username: String, password: String, context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context).userDao()
            val userdata = db.loginUser(username, password)
            if (userdata == null) {
                _userLiveData.value = LoginFailed
            } else {
                _userLiveData.value = LoginSuccess(userdata)
            }
        }
    }

    fun updateLoginStatus(userdata: UserDetailsModel, context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context).userDao()
            db.updateLoginStatue(userdata)
        }
    }

    fun logoutUser(context: Context) {
        viewModelScope.launch {
            val db = ShoppyRoomDatabase.getDbInstance(context).userDao()
            val sp = CachePref.getCacheInstance(context)
            val affectedRows = db.logoutUser(CachePref.getUserIdFromCache(sp))
            _userLogoutLiveData.value = affectedRows >= 1
        }
    }
}

sealed class UserLoginState
object LoginFailed: UserLoginState()
data class LoginSuccess(val obj: UserDetailsModel): UserLoginState()