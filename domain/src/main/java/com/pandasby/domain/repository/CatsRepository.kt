package com.pandasby.domain.repository

import com.pandasby.domain.entity.CatEntity
import io.reactivex.Observable

interface CatsRepository {

    fun getCatListObservable(limit: Int): Observable<List<CatEntity>>
}