package com.pandasby.data.repository

import android.graphics.Bitmap
import android.os.Environment
import com.pandasby.data.entity.CatBitmapSource
import com.pandasby.domain.entity.Source
import com.pandasby.domain.repository.SourceRepository
import java.io.File
import java.io.FileOutputStream

class SourceRepositoryImpl : SourceRepository {

    override fun saveSource(source: Source) {
        val bitmapSource = source as CatBitmapSource

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(path,"${bitmapSource.name}.jpeg")
        if (!file.exists()) {
            FileOutputStream(file).use {
                bitmapSource.bitmap.compress(Bitmap.CompressFormat.JPEG, 80, it)
            }
        }
    }

}