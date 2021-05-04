package com.guilhermemagro.myhabits.adapters

import androidx.recyclerview.widget.RecyclerView
import com.guilhermemagro.myhabits.utilities.ActionType
import com.guilhermemagro.myhabits.utilities.ActionType.*
import com.guilhermemagro.myhabits.utilities.getIndexOfFirstDifferent

class AdapterMovementHandler(private val adapter: RecyclerView.Adapter<*>) {

    fun itemsChanged(oldList: List<*>, newList: List<*>, lastActionType: ActionType) {
        when(lastActionType) {
            ADDED -> onItemAdded(newList)
            CLICKED -> onItemClicked(oldList, newList)
            DELETED -> onItemDeleted(oldList, newList)
            MOVED -> onItemMoved(oldList, newList)
            RESET -> onItemReset()
            INITIALIZED -> onInitialize()
        }
    }

    private fun onItemAdded(newList: List<*>) {
        adapter.notifyItemInserted(newList.lastIndex)
        if (newList.size > 1) adapter.notifyItemChanged(newList.lastIndex -1)
    }

    private fun onItemClicked(oldList: List<*>, newList: List<*>) {
        val indexOfFirstDifferent = oldList.getIndexOfFirstDifferent(newList)
        indexOfFirstDifferent?.let {
            adapter.notifyItemChanged(it)
        }
    }

    private fun onItemDeleted(oldList: List<*>, newList: List<*>) {
        val indexOfFirstDifferent = oldList.getIndexOfFirstDifferent(newList)
        indexOfFirstDifferent?.let {
            adapter.notifyItemRemoved(indexOfFirstDifferent)
            if (newList.size > 1) adapter.notifyItemChanged(indexOfFirstDifferent - 1)
        }
    }

    private fun onItemMoved(oldList: List<*>, newList: List<*>) {
        val indexOfFirstDifferent = oldList.getIndexOfFirstDifferent(newList)
        indexOfFirstDifferent?.let {
            adapter.notifyItemRangeChanged(it, 2)
        }
    }

    private fun onItemReset() {
        adapter.notifyDataSetChanged()
    }

    private fun onInitialize() {
        adapter.notifyDataSetChanged()
    }
}