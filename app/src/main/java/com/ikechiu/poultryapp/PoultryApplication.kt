package com.ikechiu.poultryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PoultryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}