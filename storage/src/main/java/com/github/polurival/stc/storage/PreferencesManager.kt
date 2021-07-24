package com.github.polurival.stc.storage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * todo move initialization to DI
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class PreferencesManager private constructor(private val preferences: SharedPreferences) {

    fun putString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    companion object {

        private var INSTANCE: PreferencesManager? = null

        fun create(context: Context): PreferencesManager {
            if (INSTANCE == null) {
                val prefs = context.getSharedPreferences(PREFS_FILE, Activity.MODE_PRIVATE)
                INSTANCE = PreferencesManager(prefs)
            }
            return INSTANCE!!
        }

        private const val PREFS_FILE = "user_prefs"
        const val USER_NAME = "userName"
    }
}
