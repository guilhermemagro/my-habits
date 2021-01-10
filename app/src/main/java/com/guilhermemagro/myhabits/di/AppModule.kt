package com.guilhermemagro.myhabits.di

import android.app.Application
import com.guilhermemagro.myhabits.MyApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    // Makes Dagger provide MyApplication when a Application type is requested
    @Binds
    abstract fun provideApplication(myApplication: MyApplication): Application
}