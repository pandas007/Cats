package com.pandasby.data.rest

import com.pandasby.data.entity.Cat
import io.reactivex.Single
import javax.inject.Inject

class RestService @Inject constructor(private val restApi: RestApi) {

    fun getCatListSingle(limit: Int): Single<List<Cat>> = restApi.getCatList(limit)
}