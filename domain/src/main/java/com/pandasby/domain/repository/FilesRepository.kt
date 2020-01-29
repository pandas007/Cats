package com.pandasby.domain.repository

import com.pandasby.domain.entity.Source

interface FilesRepository {
    fun saveFile(source: Source)
}