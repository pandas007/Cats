package com.pandasby.cats.screens.favorite.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pandasby.cats.utils.SimpleDiffUtilCallback
import com.pandasby.domain.entity.FavoriteCatEntity

class FavoriteCatsAdapter(private var catList: ArrayList<FavoriteCatEntity> = arrayListOf())
    : RecyclerView.Adapter<FavoriteCatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCatViewHolder {
        return FavoriteCatViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: FavoriteCatViewHolder, position: Int) {
        holder.bind(catList[position])
    }

    fun update(newCatList: ArrayList<FavoriteCatEntity>) {
        DiffUtil.calculateDiff(
            SimpleDiffUtilCallback(
                catList,
                newCatList,
                this::catsSameCheckFunc)
        ).dispatchUpdatesTo(this)

        catList = newCatList
    }

    private fun catsSameCheckFunc(firstCat: FavoriteCatEntity, secondCat: FavoriteCatEntity) =
        firstCat.id == secondCat.id
}