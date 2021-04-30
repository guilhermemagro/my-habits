package com.guilhermemagro.myhabits.utilities

fun <T> List<T>.getIndexOfFirstDifferent(secondList: List<T>): Int? {
    this.forEachIndexed { index, item ->
        if (item != secondList[index]) {
            return index
        }
    }
    return null
}