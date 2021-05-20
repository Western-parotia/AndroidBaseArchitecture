package com.foundation.app.simple.kcp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * create by zhusw on 5/20/21 13:49
 */
inline fun FragmentActivity.easyRequestPermission(
    vararg perNames: String, crossinline onReject: (rejects: List<Permission>) -> Unit,
    crossinline onSuccess: () -> Unit
) {
    KCPermission.prepare()
        .with(this)
        .target(*perNames)
        .build()?.request(
            leastOneReject = { g, r ->
                onReject.invoke(r)
            },
            allGranted = {
                onSuccess.invoke()
            }
        )
}

inline fun FragmentActivity.easyRequestPermission(
    vararg perNames: String,
    crossinline onSuccess: () -> Unit
) {
    easyRequestPermission(*perNames, onReject = { }, onSuccess = onSuccess)
}


inline fun Fragment.easyRequestPermission(
    vararg perNames: String,
    crossinline onSuccess: () -> Unit
) {
    easyRequestPermission(*perNames, onReject = { }, onSuccess = onSuccess)
}

inline fun Fragment.easyRequestPermission(
    vararg perNames: String, crossinline onReject: (rejects: List<Permission>) -> Unit,
    crossinline onSuccess: () -> Unit
) {
    KCPermission.prepare()
        .with(this)
        ?.target(*perNames)
        ?.build()?.request(
            leastOneReject = { g, r ->
                onReject.invoke(r)
            },
            allGranted = {
                onSuccess.invoke()
            }
        )
}