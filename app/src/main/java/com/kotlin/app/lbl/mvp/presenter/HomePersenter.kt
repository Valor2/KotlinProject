package com.kotlin.app.lbl.mvp.presenter


import com.kotlin.app.lbl.mvp.contract.HomeContract

class HomePersenter (): HomeContract.Presenter {
    var mView:HomeContract.View?=null
//    var mHomeApiMeanage : HomeApiMeanage by lazy{
////        HomeApiMeanage()
//    }

    override fun start() {
        requsetData()
    }

    override fun requsetData() {
//        val observable:Observable<GetMovie>? =
    }



}

