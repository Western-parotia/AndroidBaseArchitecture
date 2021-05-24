package com.foundation.app.simple.utils

import android.app.Application

/**
 * create by zhusw on 5/24/21 17:20
 */
object Utils {

    private lateinit var _application: Application
    val app = _application
    fun init(app: Application) {
        _application = app
    }

}