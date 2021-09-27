package com.foundation.app.arc.activity

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.FragmentSwitchHelper
import com.foundation.app.arc.utils.ext.lazyOnUI

/**
 * 切换Fragment的Activity
 * create by zhusw on 5/28/21 11:06
 */
abstract class BaseFragmentManagerActivity : BaseVMVBActivity() {
    private val switchHelper by lazyOnUI { FragmentSwitchHelper(supportFragmentManager) }

    protected fun switchFragment(tag: String, @IdRes frameLayoutId: Int = 0): Fragment? {
        return switchHelper.switchFragment(tag, frameLayoutId)
    }

    /**
     * 切换fragment，如果没有缓存则新建
     * 默认使用currentFragment.hasCode 作为tag
     */
    protected fun switchFragment(
        @NonNull currentFragment: Fragment,
        @IdRes frameLayoutId: Int = 0, tag: String? = null
    ) {
        switchHelper.switchFragment(currentFragment, frameLayoutId, tag)
    }

    /**
     * 隐藏一个Fragment
     */
    protected fun hideFragment(@NonNull clazz: Class<out Fragment>) {
        switchHelper.hideFragment(clazz)
    }

    /**
     * 移除一个Fragment
     */
    protected fun removeFragment(@NonNull clazz: Class<out Fragment?>) {
        switchHelper.removeFragment(clazz)
    }
}