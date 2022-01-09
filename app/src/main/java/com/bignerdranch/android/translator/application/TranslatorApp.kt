package com.bignerdranch.android.translator.application

import android.app.Application
import com.bignerdranch.android.translator.di.application
import com.bignerdranch.android.translator.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}