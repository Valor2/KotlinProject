package com.shenyun.volumecostprofit.guide

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.app.lbl.BaseApplication
import com.kotlin.app.lbl.common.Constants
import com.kotlin.app.lbl.ui.MainActivity
import com.kotlin.app.lbl.utils.PreferencesUtil

open class PageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var variable by PreferencesUtil(Constants.IS_FIRST_TIME, false)
        val args = arguments
        val index = args!!.getInt("index")
        val layoutId = args!!.getInt("layoutId")
        val count = args!!.getInt("count")
        var  rootView = inflater.inflate(layoutId, null)
        // 滑动到最后一页有点击事件
        if (index == count - 1) {
                rootView.setOnClickListener {

                    if(variable){
                        startActivity(Intent(BaseApplication.getInstance().getContext(), MainActivity::class.java))
                    }else{
                        variable =true
                        startActivity(Intent(BaseApplication.getInstance().getContext(), MainActivity::class.java))

//                        startActivity(Intent(AppUtils.context, LoginActivity::class.java))
                    }
                }
        }
        return rootView
    }
}