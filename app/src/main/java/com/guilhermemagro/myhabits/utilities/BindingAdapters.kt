package com.guilhermemagro.myhabits.utilities

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
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

    @JvmStatic
    @BindingAdapter("android:isVisible")
    fun setVisibility(
        view: View,
        isVisible: Boolean
    ) { view.visibility = if (isVisible) VISIBLE else INVISIBLE }
}