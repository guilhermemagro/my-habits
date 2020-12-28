package com.guilhermemagro.myhabits.data

class HabitRepository private constructor(private val habitDao: HabitDao) {

    fun getHabits() = habitDao.getAllHabits()

    suspend fun insertHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun deleteHabit(habit: Habit) = habitDao.delete(habit)

    companion object {
        @Volatile private var instance: HabitRepository? = null

        fun getInstance(habitDao: HabitDao) =
            instance ?: synchronized(this) {
                instance ?: HabitRepository(habitDao).also { instance = it }
            }
    }
}