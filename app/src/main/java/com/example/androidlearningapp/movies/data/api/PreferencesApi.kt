package com.example.androidlearningapp.movies.data.api

import android.content.SharedPreferences

interface PreferencesApi {

    var sPref: SharedPreferences

    fun setText(text: String)
    fun getText(): String
    fun setCheckBoxValue(value: Boolean)
    fun getCheckBoxValue(): Boolean
}