package com.example.justdoit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justdoit.data.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SessionViewModel(private val dataStore: DataStoreManager): ViewModel() {

    val isDarkMode = dataStore.darkModeFlow.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        false
    )

    val username = dataStore.usernameFlow.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        "Invité"
    )

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.saveDarkMode(enabled)
        }
    }

    fun updateUsername(newName: String) {
        viewModelScope.launch {
            dataStore.saveUsername(newName)
        }
    }
}