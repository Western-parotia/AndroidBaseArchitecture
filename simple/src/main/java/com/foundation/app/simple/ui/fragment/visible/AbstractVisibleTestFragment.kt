package com.foundation.app.simple.ui.fragment.visible

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.foundation.app.arc.utils.ext.lazyOnUI
import com.foundation.app.simple.architecture.BaseFragment2
import com.foundation.app.simple.jump
import com.foundation.app.simple.log
import com.foundation.app.simple.ui.EmptyActivity

/**
 * visible测试基类
 * create by zhusw on 5/19/21 14:33
 */

abstract class AbstractVisibleTestFragment(@LayoutRes id: Int) : BaseFragment2(id) {

    companion object {
        const val TYPE_VP_PAGER_HINT_ALL = "PagerHintAll"
        const val TYPE_VP_PAGER_HINT_ONLY = "PagerHintOnly"
        const val TYPE_VP_STATE_HINT_ALL = "StateHintAll"
        const val TYPE_VP_STATE_HINT_ONLY = "StateHintOnly"
        const val TYPE_VP2 = "VP2"
        const val TYPE_FRAGMENT_MANAGER = "FM"
        const val TYPE_EMPTY = "Empty"

        fun createNextFrags(
            nextDeep: Int,
            deepDesc: String,
        ): List<Fragment> {
            fun Fragment.putArg(fragType: String): Fragment {
                arguments = Bundle().apply {
                    putInt("deep", nextDeep)
                    putString("deepDesc", deepDesc)
                    putString("fragType", fragType)
                }
                return this
            }
            return when (nextDeep) {
                0, 1, 2 -> {
                    listOf(
                        ViewPagerVisibleTestFragment().putArg(TYPE_VP_PAGER_HINT_ONLY),
                        ViewPagerVisibleTestFragment().putArg(TYPE_VP_PAGER_HINT_ALL),
                        ViewPagerVisibleTestFragment().putArg(TYPE_VP_STATE_HINT_ALL),
                        ViewPagerVisibleTestFragment().putArg(TYPE_VP_STATE_HINT_ONLY),
                        ViewPager2VisibleTestFragment().putArg(TYPE_VP2),
                        FragmentManagerVisibleTestFragment().putArg(TYPE_FRAGMENT_MANAGER),
                    )
                }
                else -> {
                    listOf(
                        EmptyVisibleTestFragment().putArg(TYPE_EMPTY),
                    )
                }
            }
        }
    }

    private fun String.vLog(level: Int = 0) {
        when (level) {
            /*0, 8,*/ 9 -> {
            "${deepDesc}--$fragType($id)：：$this".log("frag visible")
        }
        }
    }

    init {
        Handler(Looper.getMainLooper()).post {
            "new".vLog(8)//1
        }
    }

    protected val deep by lazyOnUI {
        arguments?.getInt("deep")!!
    }

    protected val deepDesc by lazyOnUI {
        arguments?.getString("deepDesc")!!
    }

    protected val fragType by lazyOnUI {
        arguments?.getString("fragType")!!
    }

    private val id get() = Integer.toHexString(System.identityHashCode(this))

    override fun onVisible(isFirstVisible: Boolean) {
        super.onVisible(isFirstVisible)
        "onVisible isFirst=$isFirstVisible".vLog(9)//1
    }

    override fun onHidden() {
        super.onHidden()
        "onHidden".vLog(9)//3
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        "setUserVisibleHint=$isVisibleToUser".vLog(0)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        "onHiddenChanged=$hidden".vLog(0)
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        "setMenuVisibility=$menuVisible".vLog(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate ".vLog(8)//2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        "onDestroyView".vLog(8)//2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "onViewCreated".vLog(8)//2
    }

    override fun onResume() {
        super.onResume()
        "onResume".vLog(8)//2
    }

    override fun onPause() {
        super.onPause()
        "onPause".vLog(8)//4
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy".vLog(8)//4
    }

    protected val frags by lazyOnUI {
        createNextFrags(deep + 1, "$deepDesc>$fragType")
    }

    protected var currentIndex: Int = 0

    override fun bindData() {
        super.bindData()

        getBtnOpenNewPage()?.setOnClickListener {
            jump(EmptyActivity::class.java)
        }
        getTvFragmentTitle()?.text =
            "层级${deep}，${deepDesc}--$fragType($id)"
        getBtnClickSwitch()?.setOnClickListener {
            currentIndex = (currentIndex + 1) % frags.size
            onSwitchFragment(currentIndex)
        }
    }

    abstract fun onSwitchFragment(index: Int)

    abstract fun getBtnOpenNewPage(): View?
    abstract fun getTvFragmentTitle(): TextView?
    abstract fun getBtnClickSwitch(): View?
}
