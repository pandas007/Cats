package com.pandasby.data.rest

import com.pandasby.data.entity.Cat
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestApi {

    @GET("images/search")
    @Headers("x-api-key: 02dd55aa-3551-4634-8238-cde239f39999")
    fun getCatList(@Query("limit") limit: Int): Single<List<Cat>>
}