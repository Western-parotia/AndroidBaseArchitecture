package com.foundation.app.simple.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * create by zhusw on 5/19/21 15:19
 */
class FragmentAdapter(fm: FragmentManager, fragList: List<Fragment>) :
    FragmentPagerAdapter(fm) {
    var fragList: List<Fragment>
    fun setFragmentList(l: List<Fragment>) {
        fragList = l
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }

    init {
        this.fragList = fragList
    }
}