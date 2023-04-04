package com.foundation.app.arc.app

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.arc.utils.ext.lazyAtomic

/**
 *- 你并不一定要继承[BaseVMApplication] ,仅需要实现[ViewModelStoreOwner]
 *create by zhusw on 5/17/21 14:19
 */
open class BaseVMApplication : Application(), ViewModelStoreOwner {
    private val vmStore: ViewModelStore by lazyAtomic {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = vmStore
}