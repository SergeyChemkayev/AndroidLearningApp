package com.example.androidlearningapp.movies.data

import com.example.androidlearningapp.movies.data.api.PreferencesApi
import com.example.androidlearningapp.movies.data.api.PreferencesManager
import com.example.androidlearningapp.movies.ui.activity.PreferencesActivity
import dagger.Component

@Component (modules = arrayOf(PreferencesModule::class))
interface PreferencesComponent {

    fun inject(app: PreferencesActivity)

    fun getPreferencesManager(): PreferencesApi {
        return PreferencesManager()
    }
}