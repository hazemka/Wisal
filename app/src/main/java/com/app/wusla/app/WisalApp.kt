package com.app.wusla.app

import android.app.Application
import com.app.wusla.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WisalApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WisalApp)
            modules(appModule)
        }
    }
}