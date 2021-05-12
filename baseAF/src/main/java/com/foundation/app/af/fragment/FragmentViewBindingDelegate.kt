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

inline fun <reified VB : ViewBinding> Fragment.fastBind(view: View) =
    FragmentViewBindingDelegate<VB> {
        view.bind()
    }

inline fun <reified VB : ViewBinding> Fragment.fastBind(crossinline view: () -> View) =
    FragmentViewBindingDelegate<VB> {
        view.invoke().bind()
    }

inline fun <reified VB : ViewBinding> View.bind(): VB =
    VB::class.java.getMethod("bind", View::class.java)
        .invoke(null, this) as VB

class FragmentViewBindingDelegate<VB : ViewBinding>(private val initBlock: () -> VB) :
    ReadOnlyProperty<Fragment, VB> {
    private var binding: VB? = null
    private var hasInit = false
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        //不允许在onDestroyView 中再访问 viewBinding 示例
        val curState = thisRef.viewLifecycleOwner.lifecycle.currentState
        "curState=$curState".log()
        if (!curState.isAtLeast(Lifecycle.State.DESTROYED)) {
            throw IllegalAccessException(
                "can not access value, $curState isAtLeast(Lifecycle.State.DESTROYED) = true " +
                        "implement BeforeOnDestroyView can do something before destroyView"
            )
        }
        return when {
            !hasInit && null == binding -> {
                "init binding instance".log("FragmentViewBindingDelegate")
                binding = initBlock()
                thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroyView() {
                        if (thisRef is ViewBindingLifecycleListener) {
                            thisRef.onViewBindingDestroy()
                        }
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
     */
    fun onViewBindingDestroy() {

    }
}