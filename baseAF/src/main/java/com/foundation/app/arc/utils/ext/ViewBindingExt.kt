package com.foundation.app.arc.utils.ext

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

/**
 *@Desc:
 *-
 *-与viewBinding 相关的拓展
 *create by zhusw on 5/12/21 14:58
 */
//<editor-fold desc="activity viewBinding 拓展">
inline fun <reified VB : ViewBinding> Activity.autoBind() = lazy(LazyThreadSafetyMode.NONE) {
    initVB<VB>().apply {
        setContentView(root)
    }
}

inline fun <reified VB : ViewBinding> Activity.initVB() =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB
//</editor-fold>

//<editor-fold desc="dialog viewBinding 拓展">
inline fun <reified VB : ViewBinding> Dialog.autoBind() = lazy(LazyThreadSafetyMode.NONE) {
    initVB<VB>().apply {
        setContentView(root)
    }
}

inline fun <reified VB : ViewBinding> Dialog.initVB() =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB
//</editor-fold>
