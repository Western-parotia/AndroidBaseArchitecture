package com.foundation.app.arc.utils.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 使用此委托fragment 必须在BaseFragment中传入 layoutId
 * 如果有base层，确保支持布局id，像下面这样来声明BaseFragment
 * open class MyBaseFragment(@LayoutRes id:Int=0):Fragment(id){}
 * class MyFragment : MyBaseFragment(R.layout.act_vb)
 * create by zhusw on 5/12/21 15:19
 */
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
                        "can not init binding,because of fragment will be destroy soon" +
                                " you can implement ViewBindingLifecycleListener on Fragment and override onViewBindingDestroy to work"
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