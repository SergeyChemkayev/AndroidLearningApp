package com.example.androidlearningapp.movies.data.api

import android.content.SharedPreferences
import javax.inject.Inject

private const val SAVED_TEXT = "saved_text"
private const val SAVED_CHECK = "saved_check"

class PreferencesManager @Inject constructor() : PreferencesApi {

    override lateinit var sPref: SharedPreferences

    override fun setText(text: String) {
        sPref.edit().putString(SAVED_TEXT, text).apply()
    }

    override fun getText(): String {
        return sPref.getString(SAVED_TEXT, "") ?: ""
    }

    override fun setCheckBoxValue(value: Boolean) {
        sPref.edit().putBoolean(SAVED_CHECK, value).apply()
    }

    override fun getCheckBoxValue(): Boolean {
        return sPref.getBoolean(SAVED_CHECK, false)
    }
}