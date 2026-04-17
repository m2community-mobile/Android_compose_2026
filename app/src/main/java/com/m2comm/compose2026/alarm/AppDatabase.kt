package com.m2comm.compose2026.alarm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.m2comm.compose2026.common.UrlData.APP_TAG

@Database(entities = [AlarmDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AlarmDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        APP_TAG
                    ).build()
                }
            }
            return instance
        }
    }
}