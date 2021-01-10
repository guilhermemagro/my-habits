package com.guilhermemagro.myhabits.di

import android.content.Context
import com.guilhermemagro.myhabits.data.HabitRepository
import com.guilhermemagro.myhabits.views.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, StoreModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun habitRepository(): HabitRepository

    // Tells Dagger that MainActivity requests injection
    fun inject(mainActivity: MainActivity)
}