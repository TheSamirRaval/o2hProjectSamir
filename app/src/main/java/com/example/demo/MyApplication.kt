package com.example.demo

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.demo.common.api.ApiInterface
import com.example.demo.common.api.AppService
import com.example.demo.common.room.AppRoomDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : MultiDexApplication() {

    private lateinit var apiClient: ApiInterface
    companion object {
        lateinit var context: Context
        private lateinit var myInstance: MyApplication
        fun getInstance(): MyApplication = myInstance

        private lateinit var appRoomDB: AppRoomDatabase
        fun getAppRoomDB(): AppRoomDatabase = appRoomDB
    }

    fun getApiClient() = apiClient

    override fun onCreate() {
        super.onCreate()
        context = this
        myInstance = this
        MultiDex.install(this)
        AppService.createService(context).let { apiClient = it }
        appRoomDB = AppRoomDatabase.getInstance(this)

        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}