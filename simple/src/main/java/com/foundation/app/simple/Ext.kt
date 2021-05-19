package com.foundation.app.simple

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.foundation.app.arc.BuildConfig

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/19/21 14:04
 */
private const val TAG = "simple-baseAF"
internal fun String.log(secTag: String = "") {
    if (BuildConfig.DEBUG) {
        println("$TAG $secTag $this")
    }
}

fun Activity.jump(clz: Class<out Activity>) {
    val intent = Intent(this, clz)
    startActivity(intent)
}

fun Fragment.jump(clz: Class<out Activity>) {
    requireActivity().jump(clz)
}