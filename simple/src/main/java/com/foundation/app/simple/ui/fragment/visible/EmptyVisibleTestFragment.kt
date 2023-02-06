package com.foundation.app.simple.ui.fragment.visible

import android.view.View
import android.widget.TextView

class EmptyVisibleTestFragment :
    AbstractVisibleTestFragment(0) {
    override fun onSwitchFragment(index: Int) {
    }

    override fun getBtnOpenNewPage(): View? = null
    override fun getTvFragmentTitle(): TextView? = null
    override fun getBtnClickSwitch(): View? = null
}
