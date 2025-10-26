package com.app.data.di

import com.app.data.data_source.local.PreferencesRepositoryImpl
import com.app.data.data_source.remote.AuthenticationRemoteDataSource
import com.app.data.data_source.remote.BeneficiaryRemoteDataSource
import com.app.data.remote.data_source_impl.AuthenticationRemoteDataSourceImpl
import com.app.data.remote.data_source_impl.BeneficiaryRemoteDataSourceImpl
import com.app.data.remote.interceptor.AuthInterceptor
import com.app.data.remote.service.AuthenticationService
import com.app.data.remote.service.BeneficiaryService
import com.app.data.repository.AuthenticationRepositoryImpl
import com.app.data.repository.BeneficiaryRepositoryImpl
import com.app.data.utlis.BASE_URL
import com.app.domain.repository.AuthenticationRepository
import com.app.domain.repository.BeneficiaryRepository
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

    single { AuthInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
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
    single<BeneficiaryService> { get<Retrofit>().create(BeneficiaryService::class.java) }

    // Data Sources:
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<BeneficiaryRemoteDataSource> { BeneficiaryRemoteDataSourceImpl(get(),get()) }

    // Repositories:
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(),get()) }
    single<BeneficiaryRepository> { BeneficiaryRepositoryImpl(get()) }

    single<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }
}