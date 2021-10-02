package com.github.polurival.stc.storage

import android.content.SharedPreferences
import com.github.polurival.stc.storageapi.PreferencesManager

/**
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class PreferencesManagerImpl(
    private val preferences: SharedPreferences
) : PreferencesManager {

    override fun putString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, default: String): String {
        return preferences.getString(key, null) ?: default
    }
}
