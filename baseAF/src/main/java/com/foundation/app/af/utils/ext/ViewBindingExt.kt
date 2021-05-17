package com.foundation.app.af.utils.ext

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
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

//<editor-fold desc="fragment vb">
inline fun <reified VB : ViewBinding> Fragment.autoBind() = FragmentViewBindingDelegate<VB> {
    requireView().bind()
}

inline fun <reified VB : ViewBinding> View.bind(): VB =
    VB::class.java.getMethod("bind", View::class.java)
        .invoke(null, this) as VB
//</editor-fold>