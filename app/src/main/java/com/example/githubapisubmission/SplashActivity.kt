package com.example.githubapisubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubapisubmission.data.database.SettingPreferences
import com.example.githubapisubmission.data.database.dataStore
import com.example.githubapisubmission.data.viewmodel.PreferencesViewModel
import com.example.githubapisubmission.data.viewmodel.PreferencesViewModelFactory

class SplashActivity : AppCompatActivity() {
    private lateinit var preferencesViewModel: PreferencesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingPreferences.getInstance(application.dataStore)
        preferencesViewModel =
            ViewModelProvider(
                this,
                PreferencesViewModelFactory(pref)
            )[PreferencesViewModel::class.java]
        preferencesViewModel.getThemeSettings().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000L)
    }
}