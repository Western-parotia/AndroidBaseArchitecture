package com.foundation.app.simple.ui.fragment.visible

import androidx.viewpager2.widget.ViewPager2
import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.FragViewPager2VisibleBinding
import com.foundation.app.simple.ui.adapter.ViewPager2FragmentAdapter

class ViewPager2VisibleTestFragment :
    AbstractVisibleTestFragment(R.layout.frag_view_pager2_visible) {

    private val vb by lazyVB<FragViewPager2VisibleBinding>()

    override fun bindData() {
        super.bindData()

        vb.vpPager2.offscreenPageLimit = 1
        vb.vpPager2.adapter = ViewPager2FragmentAdapter(this).apply { list = frags }
        vb.vpPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentIndex = position
            }
        })
    }

    override fun onSwitchFragment(index: Int) {
        vb.vpPager2.currentItem = index
    }

    override fun getBtnOpenNewPage() = vb.btnOpenNewPage
    override fun getTvFragmentTitle() = vb.tvFragmentTitle
    override fun getBtnClickSwitch() = vb.btnClickSwitch
}
