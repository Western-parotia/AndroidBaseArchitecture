package com.foundation.service.net.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat

/**
 * create by zhusw on 5/26/21 12:02
 */
internal object NetState {
    //默认异常状态，通常是发生在网络层的代码异常，此时无法准确区分网络异常
    const val CODE_NORMAL = -900_0

    //网络不可用
    const val CODE_NETWORK_OFF = -900_1

    @SuppressLint("MissingPermission")
    fun networkIsAvailable(context: Context): Boolean {
        val cm = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        )
        cm?.let {
            return it.activeNetworkInfo?.isAvailable ?: false
        }
        return false
    }
}