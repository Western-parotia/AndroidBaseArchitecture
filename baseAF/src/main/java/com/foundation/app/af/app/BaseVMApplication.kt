package com.foundation.app.af.app

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 14:19
 */
class BaseVMApplication : Application(), ViewModelStoreOwner {
    private lateinit var vmStore: ViewModelStore
    override fun onCreate() {
        super.onCreate()
        vmStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = vmStore
}