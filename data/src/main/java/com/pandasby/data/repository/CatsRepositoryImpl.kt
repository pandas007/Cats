package com.pandasby.data.repository

import com.pandasby.data.rest.RestService
import com.pandasby.domain.entity.CatEntity
import com.pandasby.domain.repository.CatsRepository
import io.reactivex.Single
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(private val restService: RestService)
    : CatsRepository {

    override fun getCatListSingle(limit: Int): Single<List<CatEntity>> {
        return restService.getCatListSingle(limit)
            .map {it.map {cat -> CatEntity(cat.id, cat.url)}}
    }

}