package com.foundation.app.af.utils.param

/**
 *@Desc:
 *-activity，fragment 自动初始化参数注解
 *create by zhusw on 5/17/21 16:26
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * java中的不兼容情况
 * BundleParams(val key: String = "")
 * {
 * @BundleParams("key1") -> Cannot find method 'value'
 * @BundleParams(key ="key1") -> 正确
 * }
 * 如果单个参数时使用value 命名
 * @BundleParams("key1") -> 正确
 */
annotation class BundleParams(val value: String = "")

/*在java中需要显式的赋值 key 字段
annotation class BundleParams(val key: String = "") {

}*/
