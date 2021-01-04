package com.guilhermemagro.myhabits.viewmodels

import androidx.lifecycle.*
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.data.HabitRepository
import kotlinx.coroutines.launch

class HabitViewModel(
    private val habitRepository: HabitRepository
): ViewModel() {

    val allHabits: LiveData<List<Habit>> = habitRepository.getHabits()

    private val _habitToDelete = MutableLiveData<Habit?>()

    val habitToDelete: LiveData<Habit?>
        get() = _habitToDelete

    fun onSnackbarDeleteHabitShown() {
        _habitToDelete.value = null
    }

    fun showSnackbarDeleteHabit(habit: Habit) {
        _habitToDelete.value = habit
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        habitRepository.deleteHabit(habit)
    }

    fun insertHabit(habit: Habit) = viewModelScope.launch {
            habitRepository.insertHabit(habit)
    }

    fun setHabitDone(habit: Habit) = viewModelScope.launch {
        habit.isDone = !habit.isDone
        habitRepository.updateHabit(habit)
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