package com.example.androidlearningapp.movies.data

import android.app.Application
import android.content.Context

class AndroidLearningApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AndroidLearningApplication

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}