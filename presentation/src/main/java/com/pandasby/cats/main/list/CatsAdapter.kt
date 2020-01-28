package com.pandasby.cats.main.list

import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pandasby.domain.entity.CatEntity

class CatsAdapter(private var catList: ArrayList<CatEntity>?,
                  private val onClickListener: (CatEntity) -> Unit,
                  private val onLongClickListener: (Bitmap, String) -> Unit) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(parent, onClickListener, onLongClickListener)
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