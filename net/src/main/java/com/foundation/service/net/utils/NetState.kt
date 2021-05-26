package com.foundation.service.net.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat

/**
 * create by zhusw on 5/26/21 12:02
 */
object NetState {
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