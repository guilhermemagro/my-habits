package com.guilhermemagro.myhabits.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAllHabits(): LiveData<List<Habit>>

    @Insert
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)
}