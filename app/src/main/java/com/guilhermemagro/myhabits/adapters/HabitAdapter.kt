package com.guilhermemagro.myhabits.adapters

import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.databinding.ItemHabitBinding
import com.guilhermemagro.myhabits.utilities.ActionType.NONE
import com.guilhermemagro.myhabits.viewmodels.HabitViewModel

class HabitAdapter(
    lifecycle: LifecycleOwner,
    private val viewModel: HabitViewModel,
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    companion object {
        private const val TAG = "HABIT_ADAPTER"
    }

    private var habitsList: List<Habit> = listOf()

    init {
        setHasStableIds(true)
        viewModel.isOnEditMode.observe(lifecycle, {
            Log.println(DEBUG, TAG, "- notifyDataSetChanged() -> isOnEditMode")
            notifyDataSetChanged()
        })

        viewModel.habitsLiveData.observe(lifecycle, { newHabitsList ->
            Log.println(DEBUG, TAG, "- notifyDataSetChanged() -> habitsLiveData")
            val adapterHelper = AdapterMovementHandler(this)
            val oldList = habitsList.toMutableList()
            adapterHelper.itemsChanged(oldList, newHabitsList, viewModel.lastAction)
            habitsList = newHabitsList.toMutableList()
            if (viewModel.lastAction == NONE) notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        Log.println(DEBUG, TAG, "--- onCreateViewHolder()")
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemHabitBinding.inflate(layoutInflater, parent, false)
        return HabitViewHolder(itemBinding, viewModel)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        Log.println(DEBUG, TAG, "--- onBindViewHolder(position: $position)")
        val habit = habitsList[position]
        holder.bind(habit)
    }

    override fun getItemCount() = habitsList.size

    override fun getItemId(position: Int) = habitsList[position].id.toLong()

    inner class HabitViewHolder(private var binding: ItemHabitBinding, private val viewModel: HabitViewModel)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.habit = habit
            binding.viewModel = viewModel
            binding.imageviewStatusImage.isSelected = habit.isDone
            binding.executePendingBindings()
        }
    }
}