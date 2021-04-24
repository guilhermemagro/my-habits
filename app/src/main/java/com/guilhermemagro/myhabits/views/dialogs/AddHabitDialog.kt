package com.guilhermemagro.myhabits.views.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.guilhermemagro.myhabits.R
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.viewmodels.HabitViewModel
import java.lang.IllegalStateException

class AddHabitDialog (private val viewModel: HabitViewModel) : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = it.layoutInflater
            val builder = AlertDialog.Builder(it)
            builder.setView(inflater.inflate(R.layout.dialog_add_habit, null))
                .setTitle(R.string.add_habit_title)
                .setPositiveButton(R.string.add_habit_confirm_button) { dialog, _ ->
                    val addHabitDialog = dialog as Dialog
                    val habitDescriptionEdt = addHabitDialog
                        .findViewById<AppCompatEditText>(R.id.add_habit_edt)
                    val habitDescription = habitDescriptionEdt.text.toString()
                    if (habitDescription == "") {
                        addHabitDialog.cancel()
                    } else {
                        val lastPosition = viewModel.habitsLiveData.value?.size ?: 0
                        viewModel.insertHabit(Habit(habitDescription, lastPosition))
                    }
                }
                .setNegativeButton(R.string.add_habit_cancel_button) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create().apply {
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}