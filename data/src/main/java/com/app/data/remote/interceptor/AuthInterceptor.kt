package com.app.data.remote.interceptor

import com.app.data.utlis.BENEFICIARY_AUTH
import com.app.domain.repository.PreferencesRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val preferencesRepository: PreferencesRepository
) : Interceptor {

    private val excludedEndpoints = listOf(
        "$BENEFICIARY_AUTH/Login",
        "$BENEFICIARY_AUTH/Signup",
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // Skip adding token for excluded endpoints
        if (excludedEndpoints.any { url.contains(it, ignoreCase = true) }) {
            return chain.proceed(request)
        }

        val token = preferencesRepository.getAccessToken()
        val newRequest = if (!token.isNullOrBlank()) {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }
}
