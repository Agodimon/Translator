package com.bignerdranch.android.translator.application

import android.app.Application
import com.bignerdranch.android.translator.di.application
import com.bignerdranch.android.translator.di.historyScreen
import com.bignerdranch.android.translator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}