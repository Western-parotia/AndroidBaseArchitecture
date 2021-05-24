package com.foundation.app.simple.demo.net

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * create by zhusw on 5/20/21 14:30
 */
open class BaseApiException(res: Response<*>?) : Throwable() {
    var netMsg: String = ""
        private set
    var netCode: Int = -1
        private set
    var errorBody: ResponseBody? = null
        private set

    /**
     * 注意这是okhttp 3的 request
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

}