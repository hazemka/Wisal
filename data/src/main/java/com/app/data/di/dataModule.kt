package com.app.data.di

import com.app.data.data_source.local.PreferencesRepositoryImpl
import com.app.data.data_source.remote.AuthenticationRemoteDataSource
import com.app.data.remote.data_source.AuthenticationRemoteDataSourceImpl
import com.app.data.remote.service.AuthenticationService
import com.app.data.repository.AuthenticationRepositoryImpl
import com.app.data.utlis.BASE_URL
import com.app.domain.repository.AuthenticationRepository
import com.app.domain.repository.PreferencesRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val dataModule = module {

    val contentType = "application/json".toMediaType()

    single {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .client(get())
            .build()
    }

    // Services:
    single<AuthenticationService> { get<Retrofit>().create(AuthenticationService::class.java) }

    // Data Sources:
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }

    // Repositories:
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }

    single<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }
}