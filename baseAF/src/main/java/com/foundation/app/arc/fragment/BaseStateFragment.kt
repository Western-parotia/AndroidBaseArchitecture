package com.foundation.app.arc.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.ext.log

/**
 *@Desc:
 *-
 *- fragment 状态管理，首次显示，显示，隐藏,视图重用
 *create by zhusw on 5/19/21 09:44
 */
abstract class BaseStateFragment : Fragment() {

    private var neverVisibleBefore = true
    private var currentVisibleState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetState()
    }

    private fun resetState() {
        neverVisibleBefore = true
        currentVisibleState = false
    }


    /**
     * @param isFirst 是是首次可见
     */
    abstract fun onVisible(isFirst: Boolean)
    abstract fun onHidden()

    /**
     * 根View是否已创建
     */
    protected fun viewCreated(): Boolean {
        return null != view
    }

    /**
     * 当前frggment 被hide 时调用true，被show时false
     * 注意使用pageadapter 创建的fragment 不会调用此方法，而是 走 setUserVisibleHint
     * 原因很简单  pageadapter，控制显示
     * 但是onPause时，不会调用，此方法只是 fm 的 hide 跟 show 的回调
     * @param hidden
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        onFragmentVisibleChange(!hidden, "onHiddenChanged")
    }

    /**
     * FragmentPagerAdapter装载时 显示状态 回调 setUserVisibleHint ，且早于 onCreate，
     * 所以需要过滤view未初始化,否则子类依赖onFragmentVisibleChange 操作view 会空指针
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (viewCreated()) {
            onFragmentVisibleChange(isVisibleToUser, "setUserVisibleHint")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentVisibleChange(true, "onViewCreated")
    }

    private fun onFragmentVisibleChange(isVisible: Boolean, tag: String = "") {
        "onFragmentVisibleChange $tag".log()
        if (currentVisibleState != isVisible) {
            currentVisibleState = isVisible
            when (isVisible) {
                true -> {
                    onVisible(neverVisibleBefore)
                    neverVisibleBefore = false
                }
                false -> {
                    onHidden()
                }
            }
        }

    }

}