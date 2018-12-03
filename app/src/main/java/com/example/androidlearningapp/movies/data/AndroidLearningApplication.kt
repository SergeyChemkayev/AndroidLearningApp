package com.example.androidlearningapp.movies.data

import android.content.Context
import android.support.multidex.MultiDexApplication

class AndroidLearningApplication : MultiDexApplication() {

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