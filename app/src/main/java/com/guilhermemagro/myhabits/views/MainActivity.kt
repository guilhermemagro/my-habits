package com.guilhermemagro.myhabits.views

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.guilhermemagro.myhabits.MyApplication
import com.guilhermemagro.myhabits.R
import com.guilhermemagro.myhabits.adapters.HabitAdapter
import com.guilhermemagro.myhabits.databinding.ActivityMainBinding
import com.guilhermemagro.myhabits.viewmodels.HabitViewModel
import com.guilhermemagro.myhabits.viewmodels.HabitViewModelFactory
import com.guilhermemagro.myhabits.views.dialogs.AddHabitDialog
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var habitViewModelFactory: HabitViewModelFactory

    private val habitViewModel by viewModels<HabitViewModel> { habitViewModelFactory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
        setupSnackbar()
    }

    private fun injectDagger() {
        (applicationContext as MyApplication).appComponent.inject(this)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        val habitAdapter = HabitAdapter(this, habitViewModel).apply { setHasStableIds(true) }
        val recyclerView = binding.habitsRecyclerview
        with(recyclerView) {
            layoutManager = linearLayoutManager
            adapter = habitAdapter
        }
    }

    private fun setupSnackbar() {
        habitViewModel.habitToDelete.observe(this) { habit ->
            habit?.let {
                Snackbar.make(
                    binding.coordinator,
                    getString(R.string.delete_habit_text, habit.description),
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.delete_habit_action) {
                        habitViewModel.deleteHabit(habit)
                    }.apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        anchorView = binding.fab
                        show()
                    }
                habitViewModel.onSnackbarDeleteHabitShown()
            }
        }
    }

    fun showAddHabitDialog(view: View) {
        AddHabitDialog(habitViewModel).show(supportFragmentManager, "addHabitDialog")
    }
}