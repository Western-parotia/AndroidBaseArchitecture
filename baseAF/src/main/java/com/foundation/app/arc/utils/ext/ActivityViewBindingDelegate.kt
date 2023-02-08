package com.foundation.app.arc.utils.ext

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding


class ActivityViewBindingDelegate<out T : ViewBinding>(
    private val act: ComponentActivity,
    private val initializer: () -> T
) : Lazy<T> {

    private var _value: Any? = UNINIT_VALUE

    private val obs = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun setContentView() {
            // 触发 value 的 get 方法
            value.root
        }
    }

    init {
        act.lifecycle.addObserver(obs)
    }

    override val value: T
        get() {
            if (_value == UNINIT_VALUE) {
                act.lifecycle.removeObserver(obs)
                val vb = initializer()
                act.setContentView(vb.root)
                _value = vb
            }
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UNINIT_VALUE


}