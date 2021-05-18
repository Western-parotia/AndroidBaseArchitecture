package com.foundation.app.af.app

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.af.utils.ext.lazyAtomic

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 14:19
 */
open class BaseVMApplication : Application(), ViewModelStoreOwner {
    private val vmStore: ViewModelStore by lazyAtomic {
        ViewModelStore()
    }
    lateinit var _instance: BaseVMApplication

    override fun onCreate() {
        super.onCreate()
        _instance = this

    }

    override fun getViewModelStore(): ViewModelStore = vmStore
}