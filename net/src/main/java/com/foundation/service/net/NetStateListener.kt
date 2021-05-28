package com.foundation.service.net

/**
 * [onSuccess] 与 [onFailure] 是互斥的
 * create by zhusw on 5/25/21 16:11
 */
interface NetStateListener {
    fun onStart()
    fun onSuccess()
    fun onFailure(e: Throwable)

}