package com.foundation.app.simple.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * create by zhusw on 7/12/21 18:05
 */
class ViewPager2FragmentAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    constructor(frag: Fragment) : this(frag.childFragmentManager, frag.lifecycle)
    constructor(act: AppCompatActivity) : this(act.supportFragmentManager, act.lifecycle)

    var list = listOf<Fragment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}