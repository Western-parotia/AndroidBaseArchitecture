package com.foundation.service.net

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * create by zhusw on 5/20/21 14:30
 */
open class NetException(res: Response<*>? = null) : Throwable() {

    var netMsg: String = ""
    var netCode: Int = -1
    var errorBody: ResponseBody? = null
        private set

    /**
     * 注意这是okhttp 3的 request,包含了此次请求的全部信息
     */
    var request: Request? = null
        private set

    init {
        res?.let {
            netMsg = res.message()
            netCode = res.code()
            errorBody = res.errorBody()
            request = res.raw().request()
        }
    }

    override fun toString(): String {
        return "netCode:$netCode netMsg:$netMsg"
    }
}