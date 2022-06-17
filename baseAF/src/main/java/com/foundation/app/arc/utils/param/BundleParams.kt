package com.foundation.app.arc.utils.param

/**
 * activity，fragment 自动初始化参数注解
 *
 * 支持类型：
 * String
 * 全部基础数据类型（兼容kotlin与java）
 * Parcelable
 * 不支持类型：Serializable
 * create by zhusw on 5/17/21 16:26
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BundleParams(val value: String)

/**
 * 强制使用Serializable
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BundleParamsUseSerializable()

/*在java中需要显式的赋值 key 字段
annotation class BundleParams(val key: String = "") {

}*/
