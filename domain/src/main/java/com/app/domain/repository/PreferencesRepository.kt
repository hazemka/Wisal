package com.app.domain.repository

interface PreferencesRepository {
    fun setLoginSuccess()
    fun getLoginSuccess(): Boolean
    fun saveToken(accessToken: String, refreshToken: String)
    fun getAccessToken():String?
    fun getRefreshToken(): String?
    fun clearAllData()
}