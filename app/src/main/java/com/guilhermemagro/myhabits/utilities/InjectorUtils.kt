package com.guilhermemagro.myhabits.utilities

import android.content.Context
import com.guilhermemagro.myhabits.data.AppDatabase
import com.guilhermemagro.myhabits.data.HabitRepository

object InjectorUtils {

    fun getHabitRepository(context: Context): HabitRepository {
        return HabitRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).habitDao()
        )
    }
}