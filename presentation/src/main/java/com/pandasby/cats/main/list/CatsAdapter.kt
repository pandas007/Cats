package com.pandasby.cats.main.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pandasby.domain.entity.CatEntity

class CatsAdapter(private var catList: ArrayList<CatEntity>?) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return catList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        catList?.let {
            holder.bind(it[position])
        }
    }

    fun update(newCatList: ArrayList<CatEntity>?) {
        catList = newCatList
        notifyDataSetChanged()
    }
}