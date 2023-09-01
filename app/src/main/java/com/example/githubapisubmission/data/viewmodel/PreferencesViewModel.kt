package com.example.githubapisubmission.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubapisubmission.data.database.SettingPreferences
import kotlinx.coroutines.launch

class PreferencesViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun getThemeSettings():LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive:Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}