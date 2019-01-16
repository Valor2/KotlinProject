package com.kotlin.app.lbl.test

class Person constructor(name:String){ // constructor 构造函数
    var name: String? = null
    var url: String? = null



    init {
        println("初始化${name}")
    }
    constructor(name:String ,alexa:String): this(name){ // 次构造函数
        println("构造方法${alexa}")
    }

    fun printTest(){
        println("类的函数")
    }
    fun Person(name: String, url: String) {
        this.name = name
        this.url = url
    }


}