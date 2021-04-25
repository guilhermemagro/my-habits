package com.guilhermemagro.myhabits.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    fun getHabits() = habitDao.getAllHabits()

    suspend fun resetAllHabits() = habitDao.resetAllHabits()

    suspend fun insertHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabits(vararg habit: Habit) = habitDao.updateHabits(*habit)

    suspend fun deleteAndUpdateAll(habitToBeDeleted: Habit, habitsToBeUpdated: List<Habit>) =
        habitDao.deleteAndUpdateAll(habitToBeDeleted, habitsToBeUpdated)
}