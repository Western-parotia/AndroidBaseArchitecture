package com.foundation.app.arc.utils

/**
 * create by zhusw on 5/25/21 18:00
 */
class LoadingEvent
private constructor(val type: Int, val code: Int = 0, val msg: String = "") {

    companion object {
        const val TYPE_START = 1
        const val TYPE_STOP = 2
        const val TYPE_ERROR = 3
        val START = LoadingEvent(TYPE_START)
        val STOP = LoadingEvent(TYPE_STOP)
        fun getErrorEvent(code: Int, msg: String): LoadingEvent {
            return LoadingEvent(TYPE_ERROR, code, msg)
        }
    }

}