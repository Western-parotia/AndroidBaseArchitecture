package com.foundation.app.arc.fragment

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.InternalBasicFragment

/**
 * 对 fragment 的状态管理进行优化，简化对fragment可见性的管理，尤其是在Fragment嵌套时的可见状态判断。
 * 简化为：首次显示，显示，隐藏。
 * create by zhusw on 5/19/21 09:44
 */
abstract class BaseVisibilityFragment : InternalBasicFragment(), FragmentVisibilityChild {
    private var neverVisibleBefore = true

    private var _currentVisibleState = false
    override val currentVisibleState get() = _currentVisibleState

    /**
     * 根View是否已创建
     */
    protected fun viewCreated(): Boolean {
        return null != view
    }

    /**
     * fm 的 hide 跟 show 的回调，第一次不会调用
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        checkVisibleChangeState(!hidden)
    }

    /**
     * viewPager的可见回调，在onCreate前，[setUserVisibleHint]太乱不适合锚点
     */
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (isFirstResumed) {
            checkVisibleChangeState(menuVisible)
        }
    }

    /** isVisible=$isVisible userVis=$userVisibleHint isAdded=$isAdded
     * isHidden=$isHidden
     * 在嵌套fragment 中，作为 child fragment 在被重建时 以上全部为true
     * 所以真实的状态需要参考父 fragment 是否可见
     **/
    @CallSuper
    protected open fun checkVisibleChangeState(changedVisibleState: Boolean) {
        //支持子fragment 完全跟随 父fragment 可见状态
        val realVisible = changedVisibleState && isAdded && parentIsVisible()
        //区分重复状态 与 用户可见性
        if (realVisible != _currentVisibleState) {
            _currentVisibleState = realVisible
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
                is FragmentVisibilityChild -> {
                    it.onParentVisibleChanged(realVisible)
                }
            }
        }
    }

    override fun onParentVisibleChanged(parentIsVisible: Boolean) {
        //内部判断过了暂时不需要isParentVisible参数
        checkVisibleChangeState(
            if (isFragmentManager()) !isFmHidden else isVpVisible
        )
    }

    /**
     * parentFragment 也必须是[FragmentVisibilityChild] 才完全有效
     * 如果不是则只判断 isAdded
     * @return parentFragment是否可见
     *
     */
    protected fun parentIsVisible(): Boolean {
        return when (val pf: Fragment? = parentFragment) {
            null -> true
            is FragmentVisibilityChild -> {
                pf.currentVisibleState
            }
            else -> {
                pf.isAdded
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkVisibleChangeState(if (isFragmentManager()) !isFmHidden else isVpVisible)
    }

    override fun onPause() {
        super.onPause()
        if (currentVisibleState) {
            checkVisibleChangeState(false)
        }
    }

    override fun onClearVisibleState() {
        super.onClearVisibleState()
        _currentVisibleState = false
        neverVisibleBefore = true
    }
}

/**
 * 可见性状态变化接口，方便其他项目的fragment兼容实现
 */
interface FragmentVisibilityChild {
    val currentVisibleState: Boolean

    fun onParentVisibleChanged(parentIsVisible: Boolean)

    /**
     * @param isFirstVisible 是是首次可见
     * 注意：这个布尔值是根据view算的（destroyView后再create就是first）
     */
    fun onVisible(isFirstVisible: Boolean) {
    }

    fun onHidden() {
    }
}