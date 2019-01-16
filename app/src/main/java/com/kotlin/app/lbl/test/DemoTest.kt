package com.kotlin.app.lbl.test

import com.kotlin.app.lbl.test.bean.DemoBean.DemoBena
import com.kotlin.app.lbl.test.bean.DemoBean.DemoInterface

 class DemoTest : DemoBena() ,DemoInterface{
     var name: String?=null
     override var names:String="123"

     override fun bar() {
       println("bar")
     }



    override fun textInit() {
        println("names:${names}")
    }

    inner class demoTow{
        fun tow() = name
        fun textTow(){
            println(tow())
        }
    }

//    @Test
    fun textDemo(){
        name = "1234"
        demoTow().textTow()
    }
}