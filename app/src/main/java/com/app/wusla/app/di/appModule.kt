package com.app.wusla.app.di

import com.app.data.di.dataModule
import com.app.domain.di.domainModule
import com.app.presentation.di.presentationModule

val appModule = listOf(
    dataModule,
    domainModule,
    presentationModule
)