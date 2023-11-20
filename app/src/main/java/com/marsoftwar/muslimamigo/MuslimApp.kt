package com.marsoftwar.muslimamigo

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.marsoftwar.muslimamigo.data.local.preference.UserPreferencesRepo
import dagger.hilt.android.HiltAndroidApp

private const val AUTH_PREFERENCE_NAME = "auth_preferences"
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = AUTH_PREFERENCE_NAME
)

@HiltAndroidApp
class MuslimApp : Application() {

     lateinit var userPreferencesRepo: UserPreferencesRepo
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepo = UserPreferencesRepo(dataStore)
    }
}