package com.foundation.app.simple.demo.net

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.log
import com.foundation.service.net.NetException
import com.foundation.service.net.NetLoadingEvent
import com.foundation.service.net.NetStateListener

/**
 * create by zhusw on 5/26/21 14:06
 */
class WanAndroidNetStateHandler(
    private val control: Boolean = false,
    private val stateLiveData: MutableLiveData<NetLoadingEvent>
) :
    NetStateListener {
    override fun onStart() {
        if (control) {
            stateLiveData.value = NetLoadingEvent.START
        }
    }

    override fun onSuccess() {
        if (control) {
            stateLiveData.value = NetLoadingEvent.STOP
        }
    }

    override fun onFailure(e: Throwable) {
        handlerNetException(e)
    }

    private fun handlerNetException(e: Throwable) {
        when (e) {
            is NetException -> {
                stateLiveData.value = NetLoadingEvent.getErrorEvent(e.netCode, e.netMsg)
                "NetException: $e".log("net--")
            }
            is WanAndroidResException -> {
                stateLiveData.value = NetLoadingEvent.getErrorEvent(e.code, e.msg)
                "WanAndroidResException: $e".log("net--")
            }
            else -> {
                stateLiveData.value = NetLoadingEvent.getErrorEvent(-1, "网络层未知错误")
                "else: $e".log("net--")
            }
        }

    }
}