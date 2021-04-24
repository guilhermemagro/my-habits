package com.guilhermemagro.myhabits.adapters

import androidx.recyclerview.widget.DiffUtil
import com.guilhermemagro.myhabits.data.Habit

class HabitDiffCallback(
    var oldList: List<Habit>,
    var newList: List<Habit>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size


    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}