package com.foundation.app.arc.utils

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentSwitchHelper(private val fm: FragmentManager) {
    private var showFragment: Fragment? = null
    fun switchFragment(tag: String, @IdRes frameLayoutId: Int = 0): Fragment? {
        try {
            val fragment: Fragment? = fm.findFragmentByTag(tag)
            fragment?.let {
                switchFragment(it, frameLayoutId)
                return it
            }
        } catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 切换fragment，如果没有缓存则新建
     * 默认使用currentFragment.hasCode 作为tag
     */
    fun switchFragment(
        @NonNull currentFragment: Fragment,
        @IdRes frameLayoutId: Int = 0, tag: String? = null
    ) {
        val safeTag = tag ?: currentFragment.hashCode().toString()
        val ft: FragmentTransaction = fm.beginTransaction()
        //如果选择的fragment 不是第一个也不是正在显示的 则隐藏正在显示的
        showFragment?.let {
            if (it !== currentFragment) {
                ft.hide(it)
            }
        }
        if (currentFragment.isAdded) {
            ft.show(currentFragment)
        } else if (frameLayoutId != 0) {
            ft.add(frameLayoutId, currentFragment, safeTag)
        }
        ft.commitNow()
        showFragment = currentFragment
    }

    /**
     * 隐藏一个Fragment
     */
    fun hideFragment(@NonNull clazz: Class<out Fragment>) {
        val ft: FragmentTransaction = fm.beginTransaction()
        val currentFragment: Fragment? = fm.findFragmentByTag(clazz.name)
        currentFragment?.let {
            ft.hide(it)
            ft.commitNow()
        }
    }

    /**
     * 移除一个Fragment
     */
    fun removeFragment(@NonNull clazz: Class<out Fragment?>) {
        val ft: FragmentTransaction = fm.beginTransaction()
        val currentFragment: Fragment? = fm.findFragmentByTag(clazz.name)
        currentFragment?.let {
            ft.remove(it)
            ft.commitNow()
        }
    }
}