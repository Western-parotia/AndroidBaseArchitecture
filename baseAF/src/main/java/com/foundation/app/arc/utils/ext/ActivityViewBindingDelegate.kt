package com.foundation.app.arc.utils.ext

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import com.foundation.widget.binding.ViewBindingHelper

class ActivityViewBindingDelegate<out T : ViewBinding>(
    private val act: ComponentActivity,
    private val clazz: Class<T>
) : Lazy<T> {

    private var _value: T? = null

    private val obs = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun setContentView() {
            val vb = ViewBindingHelper.getViewBindingInstanceByClass(
                clazz, act.layoutInflater, null
            )

            act.setContentView(vb.root)
            _value = vb
        }
    }

    init {
        act.lifecycle.addObserver(obs)
    }

    override val value: T
        get() = _value
            ?: throw IllegalStateException("不能在onCreate之前访问vb：${act.lifecycle.currentState}")

    override fun isInitialized(): Boolean = _value != null
}