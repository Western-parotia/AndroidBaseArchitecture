package com.foundation.app.simple.demo.home.data

/**
 * create by zhusw on 5/27/21 15:15
 */
data class PageInfo<T>(
    val datas: List<T>,
    val curPage: Int = 0,
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0,
    //test 字段
    val testString: String,
    val testInt: Int,
    val testBoolean: Boolean,
    val testLong: Long
)