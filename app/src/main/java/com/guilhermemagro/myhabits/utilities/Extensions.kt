package com.guilhermemagro.myhabits.utilities

fun <T> List<T>.getIndexOfFirstDifferent(secondList: List<T>): Int? {
    this.forEachIndexed { index, item ->
        if ((index < secondList.size) && (item != secondList[index]) || (index == secondList.size)) {
            return index
        }
    }
    return null
}