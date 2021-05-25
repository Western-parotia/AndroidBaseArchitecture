package com.foundation.app.simple

import com.foundation.app.arc.app.BaseVMApplication
import com.foundation.app.simple.demo.net.RetrofitFactory
import com.foundation.app.simple.utils.Utils
import com.foundation.service.net.NetManager

/**

*-
 *-
 *create by zhusw on 5/18/21 11:26
 */
class CustomApplication : BaseVMApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        NetManager.init(RetrofitFactory.factory(), BuildConfig.DEBUG)

    }
}