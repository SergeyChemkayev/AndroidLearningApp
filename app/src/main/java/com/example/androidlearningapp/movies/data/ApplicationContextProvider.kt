package com.example.androidlearningapp.movies.data

import android.app.Application
import android.content.Context

class ApplicationContextProvider : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationContextProvider? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}