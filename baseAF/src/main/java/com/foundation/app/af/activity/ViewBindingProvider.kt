package com.foundation.app.af.activity

import androidx.viewbinding.ViewBinding

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 17:11
 */
interface ViewBindingProvider<T : ViewBinding> {
    val viewBinding: T
}
