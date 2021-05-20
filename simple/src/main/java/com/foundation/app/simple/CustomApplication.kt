package com.foundation.app.simple

import android.content.Context
import androidx.multidex.MultiDex
import com.foundation.app.arc.app.BaseVMApplication

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/18/21 11:26
 */
class CustomApplication : BaseVMApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}