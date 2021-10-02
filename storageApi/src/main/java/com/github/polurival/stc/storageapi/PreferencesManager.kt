package com.github.polurival.stc.storageapi

/**
 *
 *
 * @author Юрий Польщиков on 12.09.2021
 */
interface PreferencesManager {

    fun putString(key: String, value: String?)

    fun getString(key: String, default: String = ""): String
}

const val USER_NAME = "userName"
const val TRIVIA_GAME = "triviaGame"
