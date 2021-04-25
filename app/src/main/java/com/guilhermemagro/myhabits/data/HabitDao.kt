package com.guilhermemagro.myhabits.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit ORDER BY position ASC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("UPDATE habit SET is_done = 0")
    suspend fun resetAllHabits()

    @Insert
    suspend fun insert(habit: Habit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(habits: List<Habit>)

    @Update
    suspend fun updateHabits(vararg habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Transaction
    suspend fun deleteAndUpdateAll(habitToBeDeleted: Habit, habitsToBeUpdated: List<Habit>) {
        delete(habitToBeDeleted)
        updateAll(habitsToBeUpdated)
    }
}