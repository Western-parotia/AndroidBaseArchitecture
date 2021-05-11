package com.foundation.app.af.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *@Desc:
 *-
 *- 检查一级范型声明获取ViewBinding类型，创建对应ViewBinding 实际例
 *create by zhusw on 4/22/21 10:48
 */
object ViewBindingHelper {
    /**
     * @param obj 当前类实例
     */
    fun <B : ViewBinding> getViewBindingInstance(
        obj: Any, layoutInflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): B? {
        runCatching {
            val pt = obj::class.java.genericSuperclass as? ParameterizedType
            pt?.let { p ->
                p.actualTypeArguments.forEach { aT ->
                    val clz = aT as Class<*>
                    if (clz.simpleName.endsWith("Binding")) {
                        val clzSuperClass = clz.genericInterfaces
                        clzSuperClass.forEach { clT ->
                            val supClz = clT as Class<*>
                            if (supClz == ViewBinding::class.java) {
                                @Suppress("UNCHECKED_CAST")
                                val bindingClass = aT as Class<B>
                                return getViewBindingInstanceByClass(
                                    bindingClass,
                                    layoutInflater,
                                    container,
                                    attachToParent
                                )
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * 反射调用 viewbinding 实现类的 inflate 方法获取viewBinding实例
     * @param clz viewbinding 实现类型
     */
    fun <B : ViewBinding> getViewBindingInstanceByClass(
        clz: Class<out ViewBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): B? {
        runCatching {
            val method = clz.getDeclaredMethod(
                "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
            )
            @Suppress("UNCHECKED_CAST")
            return method.invoke(null, layoutInflater, container, attachToParent) as B
        }
        return null
    }

}