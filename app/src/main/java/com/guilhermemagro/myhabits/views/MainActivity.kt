package com.guilhermemagro.myhabits.views

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilhermemagro.myhabits.R
import com.guilhermemagro.myhabits.adapters.HabitAdapter
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.data.HabitRepository
import com.guilhermemagro.myhabits.databinding.ActivityMainBinding
import com.guilhermemagro.myhabits.utilities.InjectorUtils
import com.guilhermemagro.myhabits.viewmodels.HabitViewModel
import com.guilhermemagro.myhabits.viewmodels.HabitViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var habitRepository: HabitRepository

    private val habitViewModel: HabitViewModel by viewModels {
        HabitViewModelFactory(habitRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        habitRepository = InjectorUtils.getHabitRepository(this)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        val habitAdapter = HabitAdapter(this, habitViewModel.allHabits, habitViewModel)
        val recyclerView = binding.habitsRecyclerview
        with(recyclerView) {
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = habitAdapter
        }
    }

    fun insertHabit(view: View) {
        val habit = Habit(binding.habitNameEdt.text.toString())
        habitViewModel.insertHabit(habit)
        binding.habitNameEdt.text?.clear()
    }
}