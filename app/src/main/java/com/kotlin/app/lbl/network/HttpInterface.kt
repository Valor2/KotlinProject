package com.kotlin.app.lbl.network

import com.kotlin.app.lbl.entity.GetMovie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpInterface {

    @GET("v3/ranklist")
    fun getTopMovie(@Query("start") start:Int,
                    @Query("count") count:Int) : Observable<GetMovie>
}