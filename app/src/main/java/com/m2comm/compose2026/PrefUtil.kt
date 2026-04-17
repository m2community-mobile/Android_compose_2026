package com.m2comm.compose2026

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.m2comm.compose2026.common.UrlData

class PrefUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(UrlData.CODE, Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit { putString(key, str) }
    }

    fun clearData(){
        prefs.edit { clear() }
    }
}