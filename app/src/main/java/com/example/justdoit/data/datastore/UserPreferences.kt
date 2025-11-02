package com.example.justdoit.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferences {
    val DARK_MODE = booleanPreferencesKey("dar_mode")
    val USERNAME = stringPreferencesKey("username")
    val LOGIN_AMOUNT = intPreferencesKey("login_count")
}