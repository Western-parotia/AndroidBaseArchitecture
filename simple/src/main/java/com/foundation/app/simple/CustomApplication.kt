package com.foundation.app.simple

import com.foundation.app.arc.app.BaseVMApplication
import com.foundation.app.simple.utils.Utils

/**

*-
 *-
 *create by zhusw on 5/18/21 11:26
 */
class CustomApplication : BaseVMApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}