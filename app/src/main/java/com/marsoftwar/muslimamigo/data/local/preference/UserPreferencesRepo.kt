package com.marsoftwar.muslimamigo.data.local.preference

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepo(
    private val dataStore: DataStore<Preferences>
) {

    val preferenceState:Flow<UserPreference> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG,"Error reading preference.")
                emit(emptyPreferences())
            }
        }
        .map { preference->
            val isSignIn = preference[IS_SIGN_IN]
            val userName = preference[USER_NAME]
            val userEmail = preference[USER_EMAIL]
            UserPreference(isSignIn,userName,userEmail)
        }

    companion object {
        val IS_SIGN_IN = booleanPreferencesKey("IS_SIGN_IN")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        const val TAG = "preference_tag"
    }

    suspend fun saveUserPreference(
        userPreference: UserPreference
    ){
        dataStore.edit { preferences->
            preferences[IS_SIGN_IN]=userPreference.isSignIn?:false
            preferences[USER_NAME]=userPreference.userName?:""
            preferences[USER_EMAIL]=userPreference.userEmail?:""
        }
    }
}