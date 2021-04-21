package com.guilhermemagro.myhabits.viewmodels

import androidx.lifecycle.*
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.data.HabitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitViewModel(private val habitRepository: HabitRepository): ViewModel() {

    val allHabits: LiveData<List<Habit>> = habitRepository.getHabits()

    private val _habitToDelete = MutableLiveData<Habit?>()
    val habitToDelete: LiveData<Habit?>
        get() = _habitToDelete

    private val _isOnEditMode = MutableLiveData(false)
    val isOnEditMode: LiveData<Boolean>
        get() = _isOnEditMode

    fun toggleEditMode() {
        _isOnEditMode.value = (isOnEditMode.value != true)
    }

    fun showSnackbarDeleteHabit(habit: Habit) {
        _habitToDelete.value = habit
    }

    fun onSnackbarDeleteHabitShown() {
        _habitToDelete.value = null
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        habitRepository.deleteHabit(habit)
    }

    fun insertHabit(habit: Habit) = viewModelScope.launch {
        habitRepository.insertHabit(habit)
    }

    fun toggleHabitState(habit: Habit) = viewModelScope.launch {
        habit.isDone = !habit.isDone
        habitRepository.updateHabit(habit)
    }

    fun resetHabits() = viewModelScope.launch {
        habitRepository.resetAllHabits()
    }
}

class HabitViewModelFactory @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(habitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}