package com.kotlin.app.lbl.ui

import android.os.Bundle
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.R.layout.activity_main
import com.kotlin.app.lbl.base.BaseActivity
import com.kotlin.app.lbl.ui.fragment.MainFragment
import me.yokeyword.fragmentation.SupportActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResources(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initMainPage()
    }

    override fun initData() {
    }

    fun initMainPage() {
        var container = fl_container
        if(findFragment(MainFragment::class.java ) == null){
                loadRootFragment(fl_container.id,MainFragment.newInstance())
        }
    }
}
