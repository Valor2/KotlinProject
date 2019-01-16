package com.kotlin.app.lbl.test.bean.DemoBean

interface DemoInterface {
    var names:String  // 接口中的属性必须是抽象化的，不允许有初始值

    fun  bar()
    fun foo(){
        println("foot")
    }
}