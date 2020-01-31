package com.pandasby.cats.screens.main.list

import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pandasby.cats.utils.SimpleDiffUtilCallback
import com.pandasby.domain.entity.CatEntity

class CatsAdapter(private var catList: ArrayList<CatEntity> = arrayListOf(),
                  private val onClickListener: (CatEntity) -> Unit,
                  private val onLongClickListener: (Bitmap, String) -> Unit) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(parent, onClickListener, onLongClickListener)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(catList[position])
    }

    fun update(newCatList: ArrayList<CatEntity>) {
        DiffUtil.calculateDiff(
            SimpleDiffUtilCallback(
                catList,
                newCatList,
                this::catsSameCheckFunc)
        ).dispatchUpdatesTo(this)

        catList = newCatList
    }

    private fun catsSameCheckFunc(firstCat: CatEntity, secondCat: CatEntity) =
        firstCat.id == secondCat.id
}