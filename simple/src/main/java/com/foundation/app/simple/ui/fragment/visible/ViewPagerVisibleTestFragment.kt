package com.foundation.app.simple.ui.fragment.visible

import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.FragViewPagerVisibleBinding
import com.foundation.app.simple.ui.adapter.BaseFragmentPagerAdapter
import com.foundation.app.simple.ui.adapter.BaseFragmentStatePagerAdapter

class ViewPagerVisibleTestFragment :
    AbstractVisibleTestFragment(R.layout.frag_view_pager_visible) {

    private val vb by lazyVB<FragViewPagerVisibleBinding>()

    override fun bindData() {
        super.bindData()

        vb.vpPager.offscreenPageLimit = 1
        vb.vpPager.adapter = when (fragType) {
            TYPE_VP_PAGER_HINT_ALL -> BaseFragmentPagerAdapter(
                childFragmentManager,
                frags,
                FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
            )
            TYPE_VP_PAGER_HINT_ONLY -> BaseFragmentPagerAdapter(
                childFragmentManager,
                frags,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
            TYPE_VP_STATE_HINT_ALL -> BaseFragmentStatePagerAdapter(
                childFragmentManager,
                frags,
                FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
            )
            TYPE_VP_STATE_HINT_ONLY -> BaseFragmentStatePagerAdapter(
                childFragmentManager,
                frags,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
            else -> throw RuntimeException("未知异常：$fragType")
        }
        vb.vpPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                currentIndex = position
            }
        })
    }

    override fun onSwitchFragment(index: Int) {
        vb.vpPager.currentItem = index
    }

    override fun getBtnOpenNewPage() = vb.btnOpenNewPage
    override fun getTvFragmentTitle() = vb.tvFragmentTitle
    override fun getBtnClickSwitch() = vb.btnClickSwitch
}
