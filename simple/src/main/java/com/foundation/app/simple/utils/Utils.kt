package com.foundation.app.simple.utils

import android.app.Application

/**
 * create by zhusw on 5/24/21 17:20
 */
object Utils {

    private var _application: Application? = null
    val app: Application get() = _application!!
    fun init(app: Application) {
        _application = app
    }

}