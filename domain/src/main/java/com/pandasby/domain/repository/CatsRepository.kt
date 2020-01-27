package com.pandasby.domain.repository

import com.pandasby.domain.entity.CatEntity
import io.reactivex.Single

interface CatsRepository {

    fun getCatListSingle(limit: Int): Single<List<CatEntity>>
}