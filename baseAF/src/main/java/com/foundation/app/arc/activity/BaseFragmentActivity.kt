package com.foundation.app.arc.activity

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * 切换Fragment的Activity
 * create by zhusw on 5/28/21 11:06
 */
abstract class BaseFragmentActivity : BaseParamsActivity() {
    private var showFragment: Fragment? = null
    fun switchFragment(tag: String, @IdRes frameLayoutId: Int = 0): Fragment? {
        try {
            val fm: FragmentManager = supportFragmentManager
            val fragment: Fragment? = fm.findFragmentByTag(tag)
            fragment?.let {
                switchFragment(it, frameLayoutId)
                return it
            }
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 切换fragment，如果没有缓存则新建
     * 使用hasCode 作为tag
     */
    fun switchFragment(
        @NonNull currentFragment: Fragment,
        @IdRes frameLayoutId: Int = 0
    ) {
        val tag = currentFragment.hashCode().toString()
        val fm: FragmentManager = supportFragmentManager
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
            ft.add(frameLayoutId, currentFragment, tag)
        }
        ft.commitAllowingStateLoss()
        showFragment = currentFragment
    }

    /**
     * 隐藏一个Fragment
     * @param clazz
     */
    fun hideFragment(@NonNull clazz: Class<out Fragment>) {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        val currentFragment: Fragment? = fm.findFragmentByTag(clazz.name)
        currentFragment?.let {
            ft.hide(it)
            ft.commit()
        }
    }

    /**
     * 移除一个Fragment
     *
     * @param clazz
     */
    fun removeFragment(@NonNull clazz: Class<out Fragment?>) {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        val currentFragment: Fragment? = fm.findFragmentByTag(clazz.name)
        currentFragment?.let {
            ft.remove(it)
            ft.commit()
        }
    }

}
