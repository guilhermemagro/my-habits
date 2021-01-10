package com.guilhermemagro.myhabits.di

import android.content.Context
import com.guilhermemagro.myhabits.data.AppDatabase
import com.guilhermemagro.myhabits.data.HabitDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StoreModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideHabitDao(database: AppDatabase): HabitDao {
        return database.habitDao()
    }
}