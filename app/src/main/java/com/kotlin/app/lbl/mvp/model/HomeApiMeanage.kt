package com.kotlin.app.lbl.mvp.model

import android.content.Context
import com.kotlin.app.lbl.common.Constants
import com.kotlin.app.lbl.entity.GetMovie
import com.kotlin.app.lbl.network.HttpInterface
import com.kotlin.app.lbl.network.RetrofitClient
import io.reactivex.Observable
import java.util.*

class HomApiMeanage {

    companion object {
        fun login(context: Context, start: Int, count: Int): Observable<GetMovie>? {
            val retrofitClient = RetrofitClient.getInstance(context, Constants.BASE_URL)
            val apiService = retrofitClient.create(HttpInterface::class.java)
            return apiService?.getTopMovie(start, count)
        }


    }
}