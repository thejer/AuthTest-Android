package com.appstyx.authtest.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.appstyx.authtest.utils.Constants.ACCESS_TOKEN
import com.appstyx.authtest.utils.Constants.IS_LOGGED_IN
import com.google.gson.Gson

class PrefsUtils (private val sharedPref: SharedPreferences, private val gson: Gson) {

    fun putString(key: String, value: String?) {
        sharedPref.edit {
            putString(key, value)
        }
    }

    fun getString(key: String, defaultValue: String?): String? {
        return sharedPref.getString(key, defaultValue)
    }


    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPref.edit {
            putBoolean(key, value)
        }
    }

    fun remove(key: String) {
        sharedPref.edit().remove(key).apply()
    }

    var accessToken: String?
        get() = getString(ACCESS_TOKEN, null)
        set(value) {
            putString(ACCESS_TOKEN, value)
        }

    var isLoggedIn: Boolean
        get() = getBoolean(IS_LOGGED_IN, false)
        set(value) {
            putBoolean(IS_LOGGED_IN, value)
        }
}
