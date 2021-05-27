package com.foundation.service.net

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * 网络请求 响应了错误（tsl，tcp 层 校验没问题）
 * create by zhusw on 5/20/21 14:30
 */
class NetException private constructor(
    type: NetLinkErrorType,
    res: Response<*>? = null,
    e: Throwable? = null
) :
    Throwable(e) {
    companion object {
        fun createNormalType(msg: String, e: Throwable? = null): NetException {
            return NetException(NetLinkErrorType.CODE_NORMAL, e = e).apply {
                netMsg = msg
            }
        }

        fun createNetWorkType(msg: String): NetException {
            return NetException(NetLinkErrorType.CODE_NETWORK_OFF).apply {
                netMsg = msg
            }
        }

        fun createConnectType(msg: String, e: Throwable): NetException {
            return NetException(NetLinkErrorType.CODE_CONNECT_ERROR, e = e).apply {
                netMsg = msg
            }
        }

        fun createResponseType(res: Response<*>): NetException {
            return NetException(NetLinkErrorType.CODE_RESPONSE_ERROR, res = res)
        }
    }

    var netStateType: NetLinkErrorType
        private set
    var netMsg: String = ""

    /**
     * 默认为type.value:-900_x 为本框架定义的网络错误码
     *
     * 当存在Response时，将得到20x,30x,40x等为http 网络状态码
     */
    var netCode: Int
        private set
    var errorBody: ResponseBody? = null
        private set

    /**
     * 注意这是okhttp 3的 request,包含了此次请求报文的信息 url,method等等
     */
    var request: Request? = null
        private set

    init {
        netStateType = type
        netCode = netStateType.value
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