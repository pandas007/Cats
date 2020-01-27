package com.pandasby.cats.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.pandasby.cats.R
import com.pandasby.domain.entity.CatEntity

class CatViewHolder(parent: ViewGroup)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)) {

    @BindView(R.id.tv_url)
    lateinit var url: TextView
    @BindView(R.id.tv_id)
    lateinit var id: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    internal fun bind(catEntity: CatEntity) {
        url.text = catEntity.url
        id.text = catEntity.id
    }
}