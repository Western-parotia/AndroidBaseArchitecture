package com.foundation.service.net

/**
 * create by zhusw on 5/25/21 16:11
 */
interface NetStateListener {

    fun onStart()
    fun onSuccess()
    fun onFailure(e: Throwable)

}