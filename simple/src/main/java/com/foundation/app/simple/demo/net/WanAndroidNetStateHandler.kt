package com.foundation.app.simple.demo.net

import androidx.lifecycle.MutableLiveData
import com.foundation.app.arc.utils.LoadingEvent
import com.foundation.app.simple.log
import com.foundation.service.net.NetException
import com.foundation.service.net.NetStateListener

/**
 * create by zhusw on 5/26/21 14:06
 */
class WanAndroidNetStateHandler(private val stateLiveData: MutableLiveData<LoadingEvent>) :
    NetStateListener {
    override fun onStart() {
        stateLiveData.value = LoadingEvent.START
    }

    override fun onSuccess() {
        stateLiveData.value = LoadingEvent.STOP
    }

    override fun onFailure(e: Throwable) {
        handlerNetException(e)

    }

    private fun handlerNetException(e: Throwable) {
        when (e) {
            is NetException -> {
                stateLiveData.value = LoadingEvent.getErrorEvent(e.netCode, e.netMsg)
                "NetException: $e".log("net--")
            }
            is WanAndroidResException -> {
                stateLiveData.value = LoadingEvent.getErrorEvent(e.code, e.msg)
                "WanAndroidResException: $e".log("net--")
            }
            else -> {
                stateLiveData.value = LoadingEvent.getErrorEvent(-1, "网络层未知错误")
                "else: $e".log("net--")
            }
        }

    }
}