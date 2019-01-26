package com.kotlin.app.lbl.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.kotlin.app.lbl.BaseApplication
import com.kotlin.app.lbl.utils.PreferencesUtil
import com.kotlin.app.lbl.utils.ToastUtils
import me.yokeyword.fragmentation.SupportActivity


abstract class BaseActivity : SupportActivity() {

    var rootView :View ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResources())
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        BaseApplication.getInstance().AddActivity(this)
        PreferencesUtil.get(this)
        initView()
        initData()
    }

    abstract fun getLayoutResources():Int

    abstract   fun initView()

    abstract  fun initData()

    fun showToast(msg : String){
        ToastUtils.shwoToast(this,msg)
    }

    fun finishAcitivty(){
        finish()
        BaseApplication.getInstance().finishActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        BaseApplication.getInstance().finishActivity(this)
    }
}