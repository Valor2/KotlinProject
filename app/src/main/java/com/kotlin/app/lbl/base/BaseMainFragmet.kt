package com.kotlin.app.lbl.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.utils.ToastUtils
import me.yokeyword.fragmentation.SupportFragment


/**
 * 懒加载
 */
abstract class BaseMainFragmet : SupportFragment() {

    val WAIT_TIME:Long = 2000L
    var TOUCH_TIME: Long = 0
    val context = _mActivity
    var rootView :View ? = null
    var isFragmentVisiable :Boolean = false
    var isFirst : Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       if(rootView == null){
           rootView = inflater?.inflate(getLayoutResources(),container,false)
       }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            isFragmentVisiable = true;
        }
        if (rootView == null) {
            return;
        }
        //可见，并且没有加载过
        if (!isFirst&&isFragmentVisiable) {
            onFragmentVisiableChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisiable) {
            onFragmentVisiableChange(false);
            isFragmentVisiable = false;
        }
    }

    override fun onBackPressedSupport(): Boolean {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish()
        } else {
            TOUCH_TIME = System.currentTimeMillis()
            ToastUtils.shwoToast(_mActivity,getString(R.string.press_again_exit))
        }
        return true
    }

    open protected fun onFragmentVisiableChange(b: Boolean) {
    }

    abstract fun getLayoutResources():Int

    abstract  fun initView()

    abstract fun initData()

}