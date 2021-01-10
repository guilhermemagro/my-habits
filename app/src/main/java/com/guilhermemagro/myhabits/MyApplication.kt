package com.guilhermemagro.myhabits

import android.app.Application
import com.guilhermemagro.myhabits.di.AppComponent
import com.guilhermemagro.myhabits.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}