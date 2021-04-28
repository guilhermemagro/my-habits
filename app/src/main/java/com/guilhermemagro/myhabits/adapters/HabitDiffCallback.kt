package com.guilhermemagro.myhabits.adapters

import androidx.recyclerview.widget.DiffUtil
import com.guilhermemagro.myhabits.data.Habit

class HabitDiffCallback(): DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem == newItem
    }
}