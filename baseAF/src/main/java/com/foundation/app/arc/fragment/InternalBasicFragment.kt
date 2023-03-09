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
     * null表示未初始化（下面几个成员变量同理），0表示ViewPager(2)，1表示FragmentManager
     */
    private var _isFragmentManager: Boolean? = null

    private var _isFmHidden: Boolean? = null
    protected val isFmHidden get() = isFragmentManager() && (_isFmHidden ?: false)

    private var _isVpVisible: Boolean? = null
    protected val isVpVisible get() = !isFragmentManager() && (_isVpVisible ?: false)

    private var _isFirstResumed = false
    protected val isFirstResumed get() = _isFirstResumed

    /**
     * 获取父类类型是FragmentManager还是ViewPager(2)
     */
    protected fun isFragmentManager(): Boolean {
        _isFragmentManager?.let {
            return it
        }
        return when (mContainer) {
            is ViewPager -> {
                _isFragmentManager = false
                false
            }
            is ViewGroup -> {
                //FragmentManager的父布局类似FrameLayout、LinearLayout
                _isFragmentManager = true
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
        _isFragmentManager = true
        _isFmHidden = hidden
    }

    /**
     * vp的可见回调，在onCreate前，[setUserVisibleHint]太乱不适合锚点
     */
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        _isFragmentManager = false
        _isVpVisible = menuVisible
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
        _isFmHidden = null
        _isVpVisible = null
        _isFirstResumed = false
    }
}