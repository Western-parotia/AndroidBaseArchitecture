package com.foundation.app.arc.utils.ext

import androidx.annotation.MainThread
import com.foundation.app.arc.BuildConfig

/**
 *create by zhusw on 5/11/21 14:24
 */
private const val TAG = "baseAF"
internal fun String.log(secTag: String = "") {
    if (BuildConfig.DEBUG) {
        println("$TAG $secTag $this")
    }
}

internal object UNINIT_VALUE

/**
 * 无锁开销的单例加载
 * 多线程场景下保证返回首次创建的实例
 */
fun <T> lazyAtomic(initializer: () -> T) = lazy(LazyThreadSafetyMode.PUBLICATION, initializer)

@MainThread
fun <T> lazyOnUI(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)



