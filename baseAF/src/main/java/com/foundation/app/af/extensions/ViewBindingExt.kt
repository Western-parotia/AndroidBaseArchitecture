package com.foundation.app.af.extensions

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
inline fun <reified VB : ViewBinding> Activity.fastBind() = lazy(LazyThreadSafetyMode.NONE) {
    bindingVB<VB>(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <reified VB : ViewBinding> Activity.bindingVB(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB
//</editor-fold>

//<editor-fold desc="dialog viewBinding 拓展">
inline fun <reified VB : ViewBinding> Dialog.fastBind() = lazy(LazyThreadSafetyMode.NONE) {
    bindingVB<VB>(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <reified VB : ViewBinding> Dialog.bindingVB(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB
//</editor-fold>

