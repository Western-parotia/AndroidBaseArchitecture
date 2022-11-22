package com.foundation.app.arc.utils.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 * 跟着frag的生命周期走，当destroy时会销毁，当再次create时会创建新的
 */
class FragmentViewDelegate<out T>(
    private val frag: Fragment,
    private val initializer: () -> T
) : Lazy<T> {

    private var _value: Any? = UNINIT_VALUE

    override val value: T
        get() {
            if (!isInitialized()) {
                val curSate = frag.viewLifecycleOwner.lifecycle.currentState
                if (curSate == Lifecycle.State.DESTROYED) {
                    throw IllegalAccessException(
                        "can not init,because of fragment will be destroy soon，" +
                                " you can implement ViewBindingLifecycleListener on Fragment and override onViewBindingDestroy to work"
                    )
                }
                _value = initializer()
                frag.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroyView() {
                        frag.viewLifecycleOwner.lifecycle.removeObserver(this)
                        _value = UNINIT_VALUE
                    }
                })
            }
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UNINIT_VALUE


}