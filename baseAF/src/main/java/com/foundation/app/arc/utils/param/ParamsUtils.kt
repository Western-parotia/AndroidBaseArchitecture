package com.foundation.app.arc.utils.param

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.ext.log
import java.io.Serializable
import java.lang.reflect.Field

/**

 * 支持全部基本类型
 *
 *
 *create by zhusw on 5/17/21 16:32
 */
object ParamsUtils {
    private val TAG = ParamsUtils::class.java.simpleName

    fun initWithActivity(activity: Activity, intent: Intent?) {
        intent?.extras?.let {
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
        val key: String = p.value.ifEmpty { field.name }
        "bind key=$key".log(TAG)
        if (bundle.containsKey(key)) {
            "bind type=${field.type.name}".log(TAG)
            val value: Any? = when (field.type) {
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
                else -> when {
                    Parcelable::class.java.isAssignableFrom(field.type) -> {
                        bundle.getParcelable(key) as? Parcelable//不 as 的话智能推断无法识别，会被强转成 Serializable 导致崩溃
                    }
                    Serializable::class.java.isAssignableFrom(field.type) -> {
                        if (!field.isAnnotationPresent(BundleParamsUseSerializable::class.java)) {
                            throw IllegalArgumentException("not recommend use Serializable key:$key,you should use Parcelable or add @BundleParamsUseSerializable annotation")
                        }
                        bundle.getSerializable(key)
                    }
                    else -> {
                        //理论上不可能走到这里
                        throw IllegalArgumentException("unknown class type key:$key")
                    }
                }
            }
            value?.let {
                "set value=$it".log(TAG)
                val accessible = field.isAccessible
                if (!accessible) field.isAccessible = true
                field.set(obj, it)
                if (!accessible) field.isAccessible = false
            }
        } else {
            Log.e(TAG, "bundle not contains this key=$key")
        }
    }

}