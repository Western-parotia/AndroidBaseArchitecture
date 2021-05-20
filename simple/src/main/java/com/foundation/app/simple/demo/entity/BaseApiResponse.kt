package com.foundation.app.simple.demo.entity

import com.foundation.app.simple.demo.net.BaseApiException

/**
 * create by zhusw on 5/20/21 14:20
 */
class BaseApiResponse<T> {
    object Code {
        const val CODE_NORMAL = -123456
        const val CODE_SUCCESS = 0
    }

    /*    {
            "data": ...,
            "errorCode": 0,
            "errorMsg": ""
        }*/
    private val data: T? = null
    val errorCode: Int = Code.CODE_NORMAL
    val errorMsg: String = ""
    fun formatData(): T {
        when (errorCode) {
            Code.CODE_SUCCESS -> {
                return data!!
            }
            else -> {
                throw BaseApiException(errorMsg, errorCode)
            }
        }
    }

}