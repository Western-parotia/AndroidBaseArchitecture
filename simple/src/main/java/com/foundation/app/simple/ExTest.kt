package com.foundation.app.simple


/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:42
 */

object MainFoo {

    @JvmStatic
    fun main(args: Array<String>) {
        JavaMainFoo.bind("Class 调用 静态")
        JavaMainFoo().bind("对象 调用 拓展")
        JavaMainFoo::class.java.newInstance().bind("JavaMainFoo::class 对象 调用 拓展")
        d<JavaMainFoo>("d<JavaMainFoo> 对象 调用 拓展")
/*
非拓展 view=Class 调用 静态
baseAF  拓展 view=对象 调用 拓展
baseAF  拓展 view=JavaMainFoo::class 对象 调用 拓展
baseAF  拓展 view=d<JavaMainFoo> 对象 调用 拓展
*/
    }
}

inline fun <reified VB : KJAPI> d(tag: String) {
    VB::class.java.newInstance().bind(tag)
}

fun KJAPI.bind(view: String) {
    "拓展 view=$view".log()
}

interface KJAPI {
    fun name(): String
}
