package com.example.demo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.MyApplication
import com.example.demo.common.api.ApiInterface
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<Throwable> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    protected val apiInterface: ApiInterface = MyApplication.getInstance().getApiClient()

//    val userDao = MyApplication.getAppRoomDB().userDao()
//    val chatResponseDao = MyApplication.getAppRoomDB().chatResponseDao()
    val photoDao = MyApplication.getAppRoomDB().photoDao()

    var filterUpdated: MutableLiveData<Boolean> = MutableLiveData()

}