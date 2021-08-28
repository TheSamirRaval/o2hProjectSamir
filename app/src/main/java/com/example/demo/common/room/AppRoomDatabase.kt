package com.example.demo.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo.common.constant.AppConstant
import com.example.demo.common.model.Photo
import com.example.demo.common.room.dao.PhotoDao


/**
 * This is database class
 */
@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(DTCCustomerDetails::class)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java, AppConstant.DefaultValues.DatabaseName
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    abstract fun photoDao(): PhotoDao


}