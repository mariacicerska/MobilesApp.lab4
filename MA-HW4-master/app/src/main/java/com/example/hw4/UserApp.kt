package com.example.hw4

import android.app.Application
import com.example.hw4.data.koinModules.DataModule
import com.example.hw4.domain.koinModules.DomainModule
import com.example.hw4.presentation.koinModules.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UsersApp)
            modules(DataModule, DomainModule, PresentationModule)
        }
    }
}