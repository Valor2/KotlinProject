package com.kotlin.app.lbl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.kotlin.app.lbl.ui.MainActivity
import com.kotlin.app.lbl.utils.newIntent
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        init()
    }

    fun  init(){
       button.setOnClickListener {
           newIntent<MainActivity>()
       }
    }

}
