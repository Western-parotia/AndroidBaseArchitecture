package com.foundation.service.net

import com.foundation.service.net.utils.NetStateType
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * 网络请求 响应了错误（tsl，tcp 层 校验没问题）
 * create by zhusw on 5/20/21 14:30
 */
class NetException(type: NetStateType, res: Response<*>? = null, e: Throwable? = null) :
    Throwable(e) {
    constructor(state: NetStateType) : this(state, null, null)

    var netStateType: NetStateType = NetStateType.CODE_NORMAL
    var netMsg: String = ""
    var netCode: Int = NetStateType.CODE_NORMAL.value
    var errorBody: ResponseBody? = null
        private set

    /**
     * 注意这是okhttp 3的 request,包含了此次请求报文的信息 url,method等等
     */
    var request: Request? = null
        private set

    init {
        netStateType = type
    }

    init {
        res?.let {
            netMsg = res.message()
            netCode = res.code()
            errorBody = res.errorBody()
            request = res.raw().request()
        }
    }

    override fun toString(): String {
        return "netCode:$netCode netMsg:$netMsg request:$request e:${cause?.toString()}"
    }
}