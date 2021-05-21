package com.foundation.app.simple.vm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * create by zhusw on 5/21/21 11:42
 */
class StickyMutableLiveData<T> : MutableLiveData<T>() {

    fun observe(owner: LifecycleOwner, stickLess: Boolean, observer: Observer<in T>) {

    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }


}


