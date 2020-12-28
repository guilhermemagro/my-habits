package com.guilhermemagro.myhabits.utilities

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:onLongClick")
    fun setOnLongClickListener(
        view: View,
        func: () -> Unit
    ) {
        view.setOnLongClickListener {
            func()
            return@setOnLongClickListener true
        }
    }
}