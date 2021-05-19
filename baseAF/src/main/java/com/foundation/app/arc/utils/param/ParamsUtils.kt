package com.foundation.app.arc.utils.param

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.ext.log
import java.lang.reflect.Field

/**
 *@Desc:
 *-支持全部基本类型
 *
 *-
 *create by zhusw on 5/17/21 16:32
 */
object ParamsUtils {
    private val TAG = ParamsUtils::class.java.simpleName

    fun initWithActivity(activity: Activity) {
        activity.intent?.extras?.let {
            initParams(activity, it)
        }
    }

    fun initWithFragment(fragment: Fragment) {
        fragment.arguments?.let {
            initParams(fragment, it)
        }
    }

    private fun initParams(obj: Any, bundle: Bundle) {
        val time = System.currentTimeMillis()
        val fields = obj.javaClass.declaredFields
        for (f in fields) {
            f.isAccessible
            if (f.isAnnotationPresent(BundleParams::class.java)) {
                f.getAnnotation(BundleParams::class.java)?.let {
                    bind(it, f, obj, bundle)
                }
            }
        }
        "track time=${System.currentTimeMillis() - time}".log(TAG)
    }

    private fun bind(p: BundleParams, field: Field, obj: Any, bundle: Bundle) {
        val key: String = if (p.value.isEmpty()) field.name else p.value
        "bind key=$key".log(TAG)
        if (bundle.containsKey(key)) {
            "bind type=${field.type.name}".log(TAG)
            when (field.type) {
                String::class.java -> {
                    bundle.getString(key, "")
                }
                Boolean::class.javaPrimitiveType -> {
                    bundle.getBoolean(key, false)
                }
                Char::class.javaPrimitiveType -> {
                    bundle.getChar(key, Char.MIN_VALUE)
                }
                Byte::class.javaPrimitiveType -> {
                    bundle.getByte(key, 0)
                }
                Short::class.javaPrimitiveType -> {
                    bundle.getShort(key, 0)
                }
                Int::class.javaPrimitiveType -> {
                    bundle.getInt(key, 0)
                }
                Float::class.javaPrimitiveType -> {
                    bundle.getFloat(key, 0F)
                }
                Long::class.javaPrimitiveType -> {
                    bundle.getLong(key, 0L)
                }
                Double::class.javaPrimitiveType -> {
                    bundle.getDouble(key, 0.0)
                }
                else -> {
//                    bundle.getParcelable(key) //不允许传递实体
                    null
                }
            }?.apply {
                "set value=$this".log(TAG)
                val accessible = field.isAccessible
                if (!accessible) field.isAccessible = true
                field.set(obj, this)
                if (!accessible) field.isAccessible = false
            }
        } else {
            Log.e(TAG, "bundle not contains this key=$key")
        }
    }

}