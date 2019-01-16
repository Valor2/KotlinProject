//package com.kotlin.app.lbl
//
//import com.kotlin.app.lbl.test.DemoTest
//import com.kotlin.app.lbl.test.Person
//import com.kotlin.app.lbl.test.calculator
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//
//class CalculatorTest {
//
//    @BeforeEach //测试时初始化工作
//    fun initup(){
//
//    }
//    @AfterEach // 测试结束后，结束工作
//    fun cleanUp(){
//
//    }
//
//    @Test
//    fun testDivide(){
//        var calendar = calculator()
//        var result = calendar.divide(1,2)
////        Assertions.assertEquals(0.5,result,0.0001) // 判读
//        Assertions.assertNotNull(calendar) // 判断calendar值是否为空
//    }
//
//    @Test
//    fun testPerson(){
//
//        var person = Person("1","233")
//        println(person.name+" "+person.url)
//        person.Person("1","2")
//        println(person.name+" "+person.url)
//        person.name ="wangxiansheng"
//        person.url ="http:url"
//        println(person.name+" "+person.url)
//        println("name:${person.name}  url:${person.url}")
//        person.printTest()
//    }
//
//    @Test
//    fun textDemo(){
//        var demo = DemoTest()
//        demo.bar()
//        demo.foo()
//        demo.textInit()
//        println(demo.names)
//    }
//}