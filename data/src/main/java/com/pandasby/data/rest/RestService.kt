package com.pandasby.data.rest

import com.pandasby.data.entity.Cat
import io.reactivex.Observable
import javax.inject.Inject

class RestService @Inject constructor(private val restApi: RestApi) {

    fun getCatListObservable(limit: Int): Observable<List<Cat>> = restApi.getCatList(limit)
}