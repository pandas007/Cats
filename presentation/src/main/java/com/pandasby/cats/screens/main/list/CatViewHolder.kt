package com.pandasby.cats.screens.main.list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pandasby.cats.R
import com.pandasby.domain.entity.CatEntity

class CatViewHolder(parent: ViewGroup,
                    onClickListener: (CatEntity) -> Unit,
                    onLongClickListener: (Bitmap, String) -> Unit)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)) {

    @BindView(R.id.iv_cat)
    lateinit var catImage: ImageView

    private lateinit var cat: CatEntity

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener { onClickListener.invoke(cat) }
        itemView.setOnLongClickListener {
            onLongClickListener.invoke((catImage.drawable as BitmapDrawable).bitmap, cat.id)
            return@setOnLongClickListener true
        }
    }

    internal fun bind(catEntity: CatEntity) {
        cat = catEntity
        Glide.with(itemView)
            .load(catEntity.url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(catImage)
    }
}