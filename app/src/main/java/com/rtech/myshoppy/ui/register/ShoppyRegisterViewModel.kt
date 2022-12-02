package com.rtech.myshoppy.ui.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtech.myshoppy.db.ShoppyRoomDatabase
import com.rtech.myshoppy.db.entities.UserDetailsModel
import kotlinx.coroutines.launch

class ShoppyRegisterViewModel: ViewModel() {

    private var _unameLiveData = MutableLiveData<Boolean>()
    val unameLiveData: LiveData<Boolean> = _unameLiveData

    private var _mobileLiveData = MutableLiveData<Boolean>()
    val mobileLiveData: LiveData<Boolean> = _mobileLiveData

    private var _savingLiveData = MutableLiveData<Long>()
    val savingLiveData: LiveData<Long> = _savingLiveData

    private var _passwordLiveData = MutableLiveData<Boolean>()
    val passwordLiveData: LiveData<Boolean> = _passwordLiveData

    private var _confPasswordLiveData = MutableLiveData<Boolean>()
    val confPasswordLiveData: LiveData<Boolean> = _confPasswordLiveData

    private var _enableLiveData = MutableLiveData<Boolean>()
    val enableLiveData: LiveData<Boolean> = _enableLiveData

    private var password: String
    private var confPassword: String

    init {
        _enableLiveData.value = false
        _unameLiveData.value = false
        password = ""
        confPassword = ""
    }

    fun addUser(obj: UserDetailsModel, context: Context) {
        val db = ShoppyRoomDatabase.getDbInstance(context)
        viewModelScope.launch {
            val id = db.userDao().addUser(obj)
            _savingLiveData.value = id
        }
    }

    fun checkName(name: String) {
        _unameLiveData.value = name.length >= 3
        enableRegistration()
    }

    fun checkMobileNumber(mobile: String) {
        _mobileLiveData.value = mobile.length == 10
        enableRegistration()
    }

    fun checkPassword(password: String) {
        this.password = password
        _confPasswordLiveData.value = confPassword == password && password.length >= 4
        enableRegistration()
    }

    fun checkConfPassword(confPassword: String) {
        this.confPassword = confPassword
        _confPasswordLiveData.value = confPassword == password && confPassword.length >= 4
        enableRegistration()
    }

    private fun enableRegistration() {
        if (_unameLiveData.value == false) {
            _enableLiveData.value = false
        } else if (_mobileLiveData.value == false) {
            _enableLiveData.value = false
        } else
            _enableLiveData.value = password == confPassword && password.isNotEmpty() && confPassword.isNotEmpty()
    }
}