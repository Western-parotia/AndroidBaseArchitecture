package com.foundation.widget.buildsrc

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/7/21 14:10
 */
private const val TAG = "buildSrc"
internal fun String.log(secondTag: String) {
    println("$TAG $secondTag $this")
}