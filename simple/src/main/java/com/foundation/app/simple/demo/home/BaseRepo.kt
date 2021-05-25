package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.service.net.NetRepository
import kotlinx.coroutines.CoroutineScope

/**
 * create by zhusw on 5/25/21 17:26
 */
open abstract class BaseRepo<T>(uiCoroutineScope: CoroutineScope) :
    NetRepository(uiCoroutineScope) {

    protected abstract fun handlerNetException(
        e: Throwable,
        errorLiveData: MutableLiveData<T>? = null
    )

}