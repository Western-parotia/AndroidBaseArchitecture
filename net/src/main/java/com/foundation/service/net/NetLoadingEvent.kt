package com.foundation.service.net

/**
 * create by zhusw on 5/25/21 18:00
 */
class NetLoadingEvent
private constructor(val type: Int, val code: Int = 0, val msg: String = "") {

    companion object {
        const val TYPE_START = 1
        const val TYPE_STOP = 2
        const val TYPE_ERROR = 3
        val START = NetLoadingEvent(TYPE_START)
        val STOP = NetLoadingEvent(TYPE_STOP)
        fun getErrorEvent(code: Int, msg: String): NetLoadingEvent {
            return NetLoadingEvent(TYPE_ERROR, code, msg)
        }
    }

}