package com.foundation.app.simple.demo.net

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.BuildConfig
import com.foundation.app.simple.demo.home.LoadingState
import com.foundation.app.simple.log
import com.foundation.service.net.NetException
import com.foundation.service.net.NetStateListener
import com.foundation.service.net.utils.NetStateType

/**
 * create by zhusw on 5/26/21 14:06
 */
class WanAndroidNetStateHandler(private val stateLiveData: MutableLiveData<LoadingState>) :
    NetStateListener {
    override fun onStart() {
        stateLiveData.value = LoadingState.LOADING_START
    }

    override fun onSuccess() {
        stateLiveData.value = LoadingState.LOADING_STOP
    }

    override fun onFailure(e: Throwable) {
        stateLiveData.value = LoadingState.LOADING_STOP
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
        handlerNetException(e)

    }

    private fun handlerNetException(e: Throwable) {
        when (e) {
            is NetException -> {
                when (e.netStateType) {
                    NetStateType.CODE_NETWORK_OFF -> {

                    }
                    NetStateType.CODE_CONNECT_ERROR -> {

                    }
                    NetStateType.CODE_RESPONSE_ERROR -> {

                    }
                    NetStateType.CODE_NORMAL -> {

                    }
                }
                "NetException: $e".log("net--")
            }
            is WanAndroidResException -> {
                "WanAndroidResException: $e".log("net--")
            }
            else -> {
                "else: $e".log("net--")
            }
        }

    }
}