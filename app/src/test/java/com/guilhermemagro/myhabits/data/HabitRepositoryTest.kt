package com.guilhermemagro.myhabits.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class HabitRepositoryTest {

    lateinit var database: AppDatabase
    lateinit var habitRepository: HabitRepository

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        habitRepository = HabitRepository(database.habitDao())
    }

    @After
    fun cleanUp() {
        database.close()
    }

    // TODO - Implement the integration tests for HabitRepository
}