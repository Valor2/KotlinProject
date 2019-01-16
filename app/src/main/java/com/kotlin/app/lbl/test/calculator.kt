package com.kotlin.app.lbl.test

import java.lang.IllegalArgumentException

class calculator {
    fun divide(a :Int,b:Int) :Double{
        if(b ==0) throw IllegalArgumentException("divide by zero")
        return (a.toDouble() / b)
    }
}