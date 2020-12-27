package com.guilhermemagro.myhabits.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.data.HabitRepository
import kotlinx.coroutines.launch

class HabitViewModel(
    private val habitRepository: HabitRepository
): ViewModel() {

    val allHabits: LiveData<List<Habit>> = habitRepository.getHabits()

    fun insertHabit(habit: Habit) = viewModelScope.launch {
            habitRepository.insertHabit(habit)
    }
}

class HabitViewModelFactory(private val repository: HabitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}