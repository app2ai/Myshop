package com.rtech.myshoppy.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.entities.UserDetailsModel
import kotlinx.coroutines.launch

class ShoppyLoginViewModel : ViewModel() {
    private var _userLiveData = MutableLiveData<UserLoginState>()
    val userLiveData: LiveData<UserLoginState> = _userLiveData

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
}

sealed class UserLoginState
object LoginFailed: UserLoginState()
data class LoginSuccess(val obj: UserDetailsModel): UserLoginState()