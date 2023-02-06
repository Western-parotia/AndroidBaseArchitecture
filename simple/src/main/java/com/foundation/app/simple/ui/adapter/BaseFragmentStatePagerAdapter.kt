package com.foundation.app.simple.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * create by zhusw on 5/19/21 15:19
 */
class BaseFragmentStatePagerAdapter(
    fm: FragmentManager,
    fragList: List<Fragment>,
    behavior: Int = BEHAVIOR_SET_USER_VISIBLE_HINT
) :
    FragmentStatePagerAdapter(fm, behavior) {
    var fragList: List<Fragment> = fragList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }
}