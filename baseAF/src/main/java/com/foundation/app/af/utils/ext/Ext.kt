package com.foundation.app.af.utils.ext

import androidx.annotation.MainThread
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

/**
 * 多线程场景下保证返回第一次创建的实例
 * 内部使用原子性检查，保证创建实例时具备原子性
 */
fun <T> lazyAtomic(initializer: () -> T) = lazy(LazyThreadSafetyMode.PUBLICATION, initializer)

@MainThread
fun <T> lazyOnUI(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)



