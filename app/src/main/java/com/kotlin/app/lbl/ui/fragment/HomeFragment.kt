package com.kotlin.app.lbl.ui.fragment

import android.os.Bundle
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.entity.GetMovie
import com.kotlin.app.lbl.mvp.contract.HomeContract
import com.kotlin.app.lbl.mvp.model.HomApiMeanage
import com.kotlin.app.lbl.mvp.presenter.HomePersenter
import com.kotlin.app.lbl.utils.applySchedulers
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseMainFragmet(),HomeContract.View {

    var bean:GetMovie?=null
    lateinit var homePersenter:HomePersenter
    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {
        home_text1.text="我是首页"
        setDatas()
    }

    override fun initData() {
        homePersenter = HomePersenter()
        homePersenter.start()
    }

    override fun setData(bean: GetMovie) {

    }

    fun setDatas(){
        val observable: Observable<GetMovie>? = _mActivity?.let {
            HomApiMeanage.login(_mActivity,1,2)
        }
        observable?.applySchedulers()?.subscribe{
            bean:GetMovie ->bean
        }
    }

    override fun onDetach() {
        super.onDetach()
    }



}
