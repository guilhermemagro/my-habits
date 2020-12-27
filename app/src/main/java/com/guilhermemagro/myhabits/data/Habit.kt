package com.guilhermemagro.myhabits.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String,
    @ColumnInfo(name = "is_done") var isDone: Boolean
) {
    constructor(habit: String) : this(0, habit, false)
}