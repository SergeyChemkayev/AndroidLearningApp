package com.example.androidlearningapp.movies.data

import com.example.androidlearningapp.movies.data.api.PreferencesApi
import com.example.androidlearningapp.movies.data.api.PreferencesManager
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun providePreferencesManager(): PreferencesApi {
        return PreferencesManager()
    }
}