package com.example.demo.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.demo.base.BaseViewModel

/**
 * This is Login Screen view model
 */
class LoginViewModel : BaseViewModel() {

    val userValueRegister: MutableLiveData<Boolean> = MutableLiveData()
    val userValueLogin: MutableLiveData<Boolean> = MutableLiveData()
    val userValue: MutableLiveData<Boolean> = MutableLiveData()
    val chatResponseValue: MutableLiveData<Boolean> = MutableLiveData()

    fun loginWithGoogle(){

    }

}