package com.pandasby.cats.utils

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtilCallback<T> (private val oldList: List<T>,
                                 private val newList: List<T>,
                                 private val contentCheckFunc: (T, T) -> Boolean)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contentCheckFunc.invoke(oldList[oldItemPosition], newList[newItemPosition])
    }
}