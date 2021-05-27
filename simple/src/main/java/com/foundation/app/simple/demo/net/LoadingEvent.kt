package com.foundation.app.simple.demo.net

/**
 * create by zhusw on 5/25/21 18:00
 */
class LoadingEvent private constructor(val type: Int, val code: Int, val msg: String) {

    companion object {
        val TYPE_START = 1
        val TYPE_STOP = 2
        val TYPE_ERROR = 3
        val START = LoadingEvent(TYPE_START, 0, "")
        val STOP = LoadingEvent(TYPE_STOP, 0, "")
        fun getErrorEvent(code: Int, msg: String): LoadingEvent {
            return LoadingEvent(TYPE_ERROR, code, msg)
        }
    }

}