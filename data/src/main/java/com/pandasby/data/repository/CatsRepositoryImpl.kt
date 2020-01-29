package com.pandasby.data.repository

import com.pandasby.data.rest.RestService
import com.pandasby.domain.entity.CatEntity
import com.pandasby.domain.repository.CatsRepository
import io.reactivex.Observable
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(private val restService: RestService)
    : CatsRepository {

    override fun getCatListObservable(limit: Int): Observable<List<CatEntity>> {
        return restService.getCatListObservable(limit)
            .map {it.map {cat -> CatEntity(cat.id, cat.url)}}
    }

}