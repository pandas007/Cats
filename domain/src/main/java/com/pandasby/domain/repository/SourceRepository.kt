package com.pandasby.domain.repository

import com.pandasby.domain.entity.Source

interface SourceRepository {
    fun saveSource(source: Source)
}