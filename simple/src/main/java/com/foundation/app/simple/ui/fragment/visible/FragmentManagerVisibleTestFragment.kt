package com.foundation.app.simple.ui.fragment.visible

import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.FragFragmentManagerVisibleBinding

class FragmentManagerVisibleTestFragment :
    AbstractVisibleTestFragment(R.layout.frag_fragment_manager_visible) {

    private val vb by lazyVB<FragFragmentManagerVisibleBinding>()

    override fun bindData() {
        super.bindData()

        onSwitchFragment(0)
    }

    override fun onSwitchFragment(index: Int) {
        switchFragment(frags[index], vb.flFrag.id)
    }

    override fun getBtnOpenNewPage() = vb.btnOpenNewPage
    override fun getTvFragmentTitle() = vb.tvFragmentTitle
    override fun getBtnClickSwitch() = vb.btnClickSwitch
}
