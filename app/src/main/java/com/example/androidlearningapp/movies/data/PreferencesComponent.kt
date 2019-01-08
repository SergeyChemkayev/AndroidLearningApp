package com.example.androidlearningapp.movies.data

import android.content.SharedPreferences
import com.example.androidlearningapp.movies.data.api.PreferencesApi
import com.example.androidlearningapp.movies.data.api.PreferencesManager
import com.example.androidlearningapp.movies.ui.activity.PreferencesActivity
import dagger.Component

@Component
interface PreferencesComponent {

    fun inject(app: PreferencesActivity)

    fun getPreferencesManager(sPref: SharedPreferences): PreferencesApi {
        return PreferencesManager(sPref)
    }
}