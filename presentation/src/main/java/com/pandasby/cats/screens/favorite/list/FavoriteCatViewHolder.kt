package com.pandasby.cats.screens.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pandasby.cats.R
import com.pandasby.domain.entity.FavoriteCatEntity

class FavoriteCatViewHolder(parent: ViewGroup)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)) {

    @BindView(R.id.iv_cat)
    lateinit var catImage: ImageView

    private lateinit var cat: FavoriteCatEntity

    init {
        ButterKnife.bind(this, itemView)
    }

    internal fun bind(favoriteCatEntity: FavoriteCatEntity) {
        cat = favoriteCatEntity
        Glide.with(itemView)
            .load(cat.url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(catImage)
    }
}