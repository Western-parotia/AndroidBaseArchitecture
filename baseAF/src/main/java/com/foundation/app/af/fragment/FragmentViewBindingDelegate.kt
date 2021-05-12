package com.foundation.app.af.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import com.foundation.app.af.utils.log
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/12/21 15:19
 */

inline fun <reified VB : ViewBinding> Fragment.fastBind() = FragmentViewBindingDelegate<VB> {
    requireView().bind()
}

inline fun <reified VB : ViewBinding> View.bind(): VB =
    VB::class.java.getMethod("bind", View::class.java)
        .invoke(null, this) as VB

class FragmentViewBindingDelegate<VB : ViewBinding>(private val initBlock: () -> VB) :
    ReadOnlyProperty<Fragment, VB> {
    private var binding: VB? = null
    private var hasInit = false
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        return when {
            !hasInit && null == binding -> {
                val curSate = thisRef.viewLifecycleOwner.lifecycle.currentState
                if (curSate == Lifecycle.State.DESTROYED) {
                    throw IllegalAccessException(
                        "can not init binding,because of fragment will be destroyed soon" +
                                " you can implement ViewBindingLifecycleListener on Fragment and override onViewBindingDestroy"
                    )
                }
                "init binding instance".log("FragmentViewBindingDelegate")
                binding = initBlock()
                thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroyView() {
                        if (thisRef is ViewBindingLifecycleListener) {
                            thisRef.onViewBindingDestroy()
                        }
                        hasInit = false
                        binding = null
                    }
                })
                hasInit = true
                binding!!
            }
            else -> {
                binding!!
            }
        }
    }
}

interface ViewBindingLifecycleListener {
    /**
     * 在OnDestroyView执行清理前 处理例如状态保持，释放等逻辑
     * 通常并不需要实现
     */
    fun onViewBindingDestroy() {
    }
}