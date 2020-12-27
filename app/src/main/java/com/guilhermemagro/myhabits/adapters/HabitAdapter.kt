package com.guilhermemagro.myhabits.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.databinding.ItemHabitBinding

class HabitAdapter(
    lifecycle: LifecycleOwner,
    private val allHabits: LiveData<List<Habit>>
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    init {
        allHabits.observe(lifecycle, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemHabitBinding.inflate(layoutInflater, parent, false)
        return HabitViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = allHabits.value?.get(position)
            ?: throw IllegalStateException("Null HabitList not expected")
        holder.bind(habit)
    }

    override fun getItemCount(): Int {
        return allHabits.value?.size ?: 0
    }

    class HabitViewHolder(private var binding: ItemHabitBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.habit = habit
            binding.executePendingBindings()
        }
    }
}