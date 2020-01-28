package com.pandasby.data.entity

import android.graphics.Bitmap
import com.pandasby.domain.entity.Source

data class CatBitmapSource(val bitmap: Bitmap,
                           val name: String) : Source