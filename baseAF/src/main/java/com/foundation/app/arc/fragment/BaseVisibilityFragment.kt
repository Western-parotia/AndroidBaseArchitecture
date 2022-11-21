package com.foundation.app.arc.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

/**
 * fragment 状态管理，首次显示，显示，隐藏,视图重用
 *create by zhusw on 5/19/21 09:44
 */

abstract class BaseVisibilityFragment : Fragment() {
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
     * @param isFirst 是首次可见
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
     * 当前fragment 被hide 时调用true，被show时false
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

    //isVisible=$isVisible userVis=$userVisibleHint isAdded=$isAdded isHidden=$isHidden view v=${view?.visibility==View.VISIBLE}
    //在嵌套fragment 中，作为 child fragment 在被重建时 以上全部为true
    //所以真实的状态需要参考父 fragment 是否可见
    @CallSuper
    protected open fun onFragmentVisibleChange(isVisible: Boolean, tag: String = "") {
        //支持子fragment 完全跟随 父fragment 可见状态
        val realVisible = isVisible && checkParentFragmentIsVisible()
        //区分重复状态 与 用户可见性
        if (realVisible != currentVisibleState) {
            currentVisibleState = realVisible
            when (realVisible) {
                true -> {
                    onVisible(neverVisibleBefore)
                    neverVisibleBefore = false
                }
                false -> onHidden()
            }
        }
        //每次的parentFragment的变化都需要通知 childFragment
        childFragmentManager.fragments.forEach {
            when (it) {
                is BaseVisibilityFragment -> {
                    it.onParentFragmentVisibleChanged(realVisible)
                }
            }
        }
    }

    /**
     * parentFragment 也必须是[BaseVisibilityFragment] 才完全有效
     * 如果不是则只判断 userVisibleHint，isAdded,!isHidden
     * @return parentFragment是否可见
     *
     */
    protected fun checkParentFragmentIsVisible(): Boolean {
        return when (val pf: Fragment? = parentFragment) {
            null -> true
            is BaseVisibilityFragment -> {
                pf.currentVisibleState
            }
            else -> {
                pf.userVisibleHint || (pf.isAdded && !pf.isHidden)
            }
        }
    }

    private fun onParentFragmentVisibleChanged(isVisible: Boolean) {
        //当父fragment 的状态与子不同时，重新检查子的 状态
        if (isVisible != currentVisibleState) {
            val realVisible = userVisibleHint || (isAdded && !isHidden)
            onFragmentVisibleChange(realVisible, "onParentFragmentVisibleChanged")
        }
    }

    override fun onResume() {
        super.onResume()
        if (!currentVisibleState) {
            onFragmentVisibleChange(userVisibleHint, "onResume")
        }

    }

    override fun onPause() {
        super.onPause()
        if (currentVisibleState) {
            onFragmentVisibleChange(false, "onPause")
        }
    }
}