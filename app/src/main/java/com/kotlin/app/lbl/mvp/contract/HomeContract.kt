package com.kotlin.app.lbl.mvp.contract

import com.kotlin.app.lbl.entity.GetMovie
import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.base.BaseView

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: GetMovie)
    }

    interface Presenter : BasePresenter {
        fun requsetData()
    }
}