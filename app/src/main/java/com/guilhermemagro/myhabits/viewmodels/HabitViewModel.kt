package com.guilhermemagro.myhabits.viewmodels

import androidx.lifecycle.*
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.data.HabitRepository
import com.guilhermemagro.myhabits.utilities.ActionType
import com.guilhermemagro.myhabits.utilities.ActionType.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitViewModel(private val repository: HabitRepository): ViewModel() {

    val habitsLiveData: LiveData<List<Habit>> = repository.getHabits()

    var lastAction: ActionType = INITIALIZED

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
        lastAction = DELETED
        habitsLiveData.value?.let { habitsLiveData ->
            val habitsList = habitsLiveData.toMutableList()
            habitsList.map { item -> if (item.position > habit.position) item.position-- }
            habitsList.remove(habit)
            repository.deleteAndUpdateAll(habitToBeDeleted = habit, habitsToBeUpdated = habitsList)
        }
    }

    fun insertHabit(habit: Habit) = viewModelScope.launch {
        lastAction = ADDED
        repository.insertHabit(habit)
    }

    fun toggleHabitState(habit: Habit) = viewModelScope.launch {
        lastAction = CLICKED
        habit.isDone = !habit.isDone
        repository.updateHabits(habit)
    }

    fun resetHabits() = viewModelScope.launch {
        lastAction = RESET
        repository.resetAllHabits()
    }

    fun moveItemUp(habitMovedUp: Habit) = viewModelScope.launch {
        habitsLiveData.value?.let { habits ->
            val habitMovedDownIndex = habits.indexOf(habitMovedUp) - 1
            val habitMovedDown = habits[habitMovedDownIndex]
            moveHabitsAndUpdate(habitMovedDown, habitMovedUp)
        }
    }

    fun moveItemDown(habitMovedDown: Habit) = viewModelScope.launch {
        habitsLiveData.value?.let { habits ->
            val habitMovedDownIndex = habits.indexOf(habitMovedDown) + 1
            val habitMovedUp = habits[habitMovedDownIndex]
            moveHabitsAndUpdate(habitMovedDown, habitMovedUp)
        }
    }

    private suspend fun moveHabitsAndUpdate(
        habitMovedDown: Habit,
        habitMovedUp: Habit
    ) {
        lastAction = MOVED
        habitMovedDown.position++
        habitMovedUp.position--
        repository.updateHabits(habitMovedDown, habitMovedUp)
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