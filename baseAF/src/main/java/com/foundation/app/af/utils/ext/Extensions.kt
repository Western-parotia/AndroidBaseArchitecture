package com.foundation.app.af.utils.ext

import com.foundation.app.af.BuildConfig

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:24
 */
private const val TAG = "baseAF"
internal fun String.log(secTag: String = "") {
    if (BuildConfig.DEBUG) {
        println("$TAG $secTag $this")
    }
}