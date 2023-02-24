@file:Suppress("PackageDirectoryMismatch")//包名警告
package androidx.fragment.app

import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.foundation.app.arc.fragment.BaseVisibilityFragment

/**
 * 这是一个服务于[BaseVisibilityFragment]的上层Fragment，用于调用私有方法[mContainer]来更好的确定父布局类型
 *
 * 当前各版本现状：
 * FragmentManager：调用[onHiddenChanged]，但第一次不会调用
 * ViewPager(2)：统一会调用[setMenuVisibility]，[setUserVisibleHint]太乱不适合锚点
 */
abstract class InternalBasicFragment internal constructor() : Fragment() {

    /**
     * -1表示未初始化（下面几个成员变量同理），0表示ViewPager(2)，1表示FragmentManager
     */
    private var _isFragmentManager = -1

    private var _isFmHidden: Int = -1
    protected val isFmHidden get() = if (isFragmentManager()) _isFmHidden == 1 else false

    private var _isVpVisible = -1
    protected val isVpVisible get() = if (isFragmentManager()) false else _isVpVisible == 1

    private var _isFirstResumed = false
    protected val isFirstResumed get() = _isFirstResumed

    /**
     * 获取父类类型是FragmentManager还是ViewPager(2)
     */
    protected fun isFragmentManager(): Boolean {
        if (_isFragmentManager >= 0) {
            return _isFragmentManager == 1
        }
        return when (mContainer) {
            is ViewPager -> {
                _isFragmentManager = 0
                false
            }
            is ViewGroup -> {
                //FragmentManager的父布局类似FrameLayout、LinearLayout
                _isFragmentManager = 1
                true
            }
            else -> false//mContainer null的情况，是ViewPager2（除非自定义）
        }
    }

    /**
     * fm 的 hide 跟 show 的回调，第一次不会调用
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        _isFragmentManager = 1
        _isFmHidden = if (hidden) 1 else 0
    }

    /**
     * vp的可见回调，在onCreate前，[setUserVisibleHint]太乱不适合锚点
     */
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        _isFragmentManager = 0
        _isVpVisible = if (menuVisible) 1 else 0
    }

    override fun onResume() {
        super.onResume()
        _isFirstResumed = true
    }

    override fun onDestroy() {
        onClearVisibleState()
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onClearVisibleState()
    }

    /**
     * 当[onDestroy]或[onDestroyView]时应当重置保存状态
     */
    protected open fun onClearVisibleState() {
        _isFmHidden = -1
        _isVpVisible = -1
        _isFirstResumed = false
    }
}