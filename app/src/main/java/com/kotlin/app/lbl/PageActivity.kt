package com.kotlin.app.lbl

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.View
import com.kotlin.app.lbl.base.BaseActivity
import com.kotlin.app.lbl.common.Constants
import com.kotlin.app.lbl.ui.MainActivity
import com.kotlin.app.lbl.utils.PreferencesUtil
import com.kotlin.app.lbl.utils.newIntent
import kotlinx.android.synthetic.main.activity_page.*
import java.util.*

class PageActivity : BaseActivity() {



    var timer: Timer?=null
    var preferences by PreferencesUtil(Constants.IS_FIRST_TIME,false)
    var islogin by PreferencesUtil(Constants.IS_LOGIN,false)
    internal var i = 4
    var isClick:Boolean = true

    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){     //此处的object 要加，否则无法重写 handlerMessage
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 0){
                if (msg.what == 0) {
                    i--
                    time.text = "倒计时($i)"
                    if (i == 0) {
                        if (isClick) goNextActivity()
                    }
                }
            }

        }
    }


    override fun getLayoutResources(): Int {
        return R.layout.activity_page
    }

    override fun initView() {
        if(preferences){
            timer = Timer()
            timer?.schedule(object:TimerTask(){
                override fun run() {
                    handler.sendEmptyMessage(0)
                }
            },0,1000)
        }else{
            frameLayout.visibility = View.VISIBLE
            setPage()
        }

        time.setOnClickListener {
            isClick = false
            goNextActivity()
        }

    }

    override fun initData() {

    }


    fun setPage(){
        var array= intArrayOf(R.layout.page_tab1,R.layout.page_tab2,R.layout.page_tab4)
        contentFrameLayout.setUpViews(array,
            R.mipmap.banner_on,
            R.mipmap.banner_off)
    }

    fun goNextActivity(){
        if(islogin){
            startActivity(Intent(BaseApplication.getInstance().getContext(), MainActivity::class.java))
        }else{
            startActivity(Intent(BaseApplication.getInstance().getContext(), MainActivity::class.java))
//            startActivity(Intent(AppUtils.context, LoginActivity::class.java))
        }
        finish()

    }

}
