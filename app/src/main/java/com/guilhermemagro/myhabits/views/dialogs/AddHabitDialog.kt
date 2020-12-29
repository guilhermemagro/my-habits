package com.guilhermemagro.myhabits.views.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.guilhermemagro.myhabits.R
import com.guilhermemagro.myhabits.data.Habit
import com.guilhermemagro.myhabits.viewmodels.HabitViewModel
import java.lang.IllegalStateException

class AddHabitDialog (private val viewModel: HabitViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val builder = AlertDialog.Builder(it)
            builder.setView(inflater.inflate(R.layout.dialog_add_habit, null))
                .setTitle(R.string.add_habit_title)
                .setPositiveButton(R.string.add_habit_confirm_button) { dialog, id ->
                    val addHabitDialog = dialog as Dialog
                    val habitDescriptionEdt = addHabitDialog
                        .findViewById<AppCompatEditText>(R.id.add_habit_edt)
                    val habitDescription = habitDescriptionEdt.text.toString()
                    if (habitDescription == "") {
                        addHabitDialog.cancel()
                    } else {
                        viewModel.insertHabit(Habit(habitDescription))
                    }
                }
                .setNegativeButton(R.string.add_habit_cancel_button) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}