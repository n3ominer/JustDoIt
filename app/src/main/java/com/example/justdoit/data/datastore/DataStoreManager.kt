package com.example.justdoit.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension qui permet de créer un DataStore depuis un context / de le récupérer
private val Context.dataStore by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    // Getters of data from DataStore
    val darkModeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[UserPreferences.DARK_MODE] ?: false
    }

    val usernameFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[UserPreferences.USERNAME] ?: "Invité"
    }

    // Setters of data in DataStore
    suspend fun saveDarkMode(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferences.DARK_MODE] = enabled
        }
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferences.USERNAME] = username
        }
    }

}