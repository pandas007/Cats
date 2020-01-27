package com.pandasby.cats.favorite.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pandasby.domain.entity.FavoriteCatEntity

class FavoriteCatsAdapter(private var catList: ArrayList<FavoriteCatEntity>?)
    : RecyclerView.Adapter<FavoriteCatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCatViewHolder {
        return FavoriteCatViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return catList?.size ?: 0
    }

    override fun onBindViewHolder(holder: FavoriteCatViewHolder, position: Int) {
        catList?.let {
            holder.bind(it[position])
        }
    }

    fun update(newCatList: ArrayList<FavoriteCatEntity>?) {
        catList = newCatList
        notifyDataSetChanged()
    }
}