package com.rtech.myshoppy.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppyRegisterViewModel : ViewModel() {

    private var _unameLiveData = MutableLiveData<Boolean>()
    val unameLiveData: LiveData<Boolean> = _unameLiveData

    private var _mobileLiveData = MutableLiveData<Boolean>()
    val mobileLiveData: LiveData<Boolean> = _mobileLiveData

    private var _passwordLiveData= MutableLiveData<Boolean>()
    val passwordLiveData: LiveData<Boolean> = _passwordLiveData

    private  var _confpasswordLiveData= MutableLiveData<Boolean>()
    val confpasswordLiveData: LiveData<Boolean> = _confpasswordLiveData

    private var _enableLiveData = MutableLiveData<Boolean>()
    val enableLiveData: LiveData<Boolean> = _enableLiveData

    fun checkName(name: String) {
        _unameLiveData.value = name.length >= 3
        enableRegistration()
    }

    fun checkMobileNumber(mobile: String) {
        _mobileLiveData.value = mobile.length == 10
        enableRegistration()
    }
    fun checkPassword(password:String){
        _passwordLiveData.value= password.length >= 8
        enableRegistration()
    }
    fun checkConfPassword(confpassword:String){
        _confpasswordLiveData.value= confpassword.length >= 8
        enableRegistration()
    }

    private fun enableRegistration() {
        if (_unameLiveData.value == false) {
            _enableLiveData.value = false
        } else if(_mobileLiveData.value == false) {
            _enableLiveData.value = false
        } else if(_passwordLiveData.value==false){
            _enableLiveData.value=false
        }else if(_confpasswordLiveData.value==false){
            _enableLiveData.value=false
        }else {
            _enableLiveData.value = true
        }
    }
}