package com.foundation.app.arc.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.FragmentSwitchHelper

/**
 * create by zhusw on 5/28/21 13:40
 */
abstract class BaseFragmentManagerFragment : BaseVMFragment() {
    private val switchHelper by lazyWithFragment { FragmentSwitchHelper(childFragmentManager) }

    protected fun switchFragment(tag: String, @IdRes frameLayoutId: Int = 0): Fragment? {
        return switchHelper.switchFragment(tag, frameLayoutId)
    }

    /**
     * 切换fragment，如果没有缓存则新建
     * 默认使用currentFragment.hasCode 作为tag
     */
    protected fun switchFragment(
        currentFragment: Fragment,
        @IdRes frameLayoutId: Int = 0, tag: String? = null
    ) {
        switchHelper.switchFragment(currentFragment, frameLayoutId, tag)
    }

    /**
     * 隐藏一个Fragment
     */
    protected fun hideFragment(clazz: Class<out Fragment>) {
        switchHelper.hideFragment(clazz)
    }

    /**
     * 移除一个Fragment
     */
    protected fun removeFragment(clazz: Class<out Fragment?>) {
        switchHelper.removeFragment(clazz)
    }
}