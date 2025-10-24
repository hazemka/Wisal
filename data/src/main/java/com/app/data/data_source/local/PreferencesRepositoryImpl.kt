package com.app.data.data_source.local

import android.content.Context
import com.app.domain.repository.PreferencesRepository

class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    private val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val editor get() = sharedPrefs.edit()

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_LOGIN_SUCCESS = "login_success"
    }

    override fun setLoginSuccess() {
        editor.putBoolean(KEY_LOGIN_SUCCESS, true).apply()
    }

    override fun getLoginSuccess(): Boolean {
        return sharedPrefs.getBoolean(KEY_LOGIN_SUCCESS, false)
    }

    override fun saveToken(accessToken: String, refreshToken: String) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken)
            .putString(KEY_REFRESH_TOKEN, refreshToken)
            .apply()
    }

    override fun getAccessToken(): String? {
        return sharedPrefs.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun getRefreshToken(): String? {
        return sharedPrefs.getString(KEY_REFRESH_TOKEN, null)
    }

    override fun clearAllData() {
        editor.clear().apply()
    }
}