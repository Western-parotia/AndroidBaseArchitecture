@file:JvmName("LiveDataExt")
@file:kotlin.jvm.JvmMultifileClass
package com.foundation.app.arc.utils.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field

/**
 * 同一observer对象多次订阅[observerStickyLess]和[LiveData.observe]只会有一次生效
 * 订阅无粘性消息，使用到了反射 liveData的 mVersion
 * 反射谣言：在Android 大可不必考虑反射的性能开销。在个位数指令执行测试中，反射1w次
 * 裸调 多消耗 16 ms， 使用缓存 多消耗 2ms
 * 订阅前尝试了移除，避免同一个Observer 分别订阅在了[LiveData.observe] 与 [observerStickyLess] 中
 * 造成重复收到结果
 * create by zhusw on 5/21/21 14:34
 */
fun <T> LiveData<T>.observerStickyLess(owner: LifecycleOwner, observer: Observer<in T>) {
    removeObserver(observer)
    observe(owner, StickLessWrapperObserver(this, observer))
}

private class StickLessWrapperObserver<T>(
    private val liveData: LiveData<T>,
    private val realObs: Observer<in T>
) :
    Observer<T> {
    private val initVersion: Int
    private val versionField: Field = LiveData::class.java.getDeclaredField("mVersion")

    init {
        initVersion = getVersion()
    }

    //<editor-fold desc="Lifecycle 内部存储去重复需要以 realObs为准">
    override fun hashCode(): Int {
        return realObs.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is StickLessWrapperObserver<*>) {
            return realObs == other.realObs
        } else if (other is Observer<*>) {
            return realObs == other
        }
        return super.equals(other)
    }
//</editor-fold>

    override fun onChanged(t: T) {
        val curVersion = getVersion()
        if (curVersion > initVersion) {
            realObs.onChanged(t)
        }
    }

    private fun getVersion(): Int {
        if (!versionField.isAccessible) {
            versionField.isAccessible = true
        }
        return versionField.getInt(liveData)
    }

}