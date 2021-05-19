package com.foundation.app.simple.ui.fragment

import android.graphics.Color
import android.os.Bundle
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseFragment2
import com.foundation.app.simple.databinding.FragVisibleBinding

/**
 * 单个fragment 测试
 * create by zhusw on 5/19/21 14:33
 */
open class VisibleMultiFragment : BaseFragment2(R.layout.frag_visible) {
    private val vbBinding by initVB<FragVisibleBinding>()

    @BundleParams
    val color: Int = Color.parseColor("#3e3e3e")

    @BundleParams
    val text: String = "-"

    companion object {
        fun newInstance(color: Int, text: String): VisibleOneFragment {
            val f = VisibleOneFragment()
            f.arguments = buildBundle(color, text)
            return f
        }

        fun buildBundle(color: Int, text: String): Bundle {
            val bundle = Bundle()
            bundle.putInt("color", color)
            bundle.putString("text", text)
            return bundle
        }
    }

    override fun bindData() {
        super.bindData()
        vbBinding.iv.setBackgroundColor(color)
        vbBinding.btn.text = text
    }

}
