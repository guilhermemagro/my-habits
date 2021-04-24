package com.guilhermemagro.myhabits.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    fun getHabits() = habitDao.getAllHabits()

    suspend fun resetAllHabits() = habitDao.resetAllHabits()

    suspend fun insertHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateAll(habits: List<Habit>) = habitDao.updateAll(habits)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun deleteHabit(habit: Habit) = habitDao.delete(habit)
}