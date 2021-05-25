package com.foundation.app.simple.demo.net

import com.foundation.service.net.NetManager

/**
 * create by zhusw on 5/25/21 15:13
 */
object ApiManager {

    fun <T : Any> getApi(clz: Class<T>): T {
        return NetManager.getApiService(clz)
    }

}

inline fun <reified T : Any> ApiManager.getApi(): T {
    return getApi(T::class.java)
}