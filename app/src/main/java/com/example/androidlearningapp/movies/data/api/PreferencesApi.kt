package com.example.androidlearningapp.movies.data.api

interface PreferencesApi {

    fun setText(text: String)
    fun getText(): String
    fun setCheckBoxValue(value: Boolean)
    fun getCheckBoxValue(): Boolean
}