package com.foundation.app.simple.ui.fragment

import android.graphics.Color
import android.os.Bundle
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseFragment2
import com.foundation.app.simple.databinding.FragVisibleBinding
import com.foundation.app.simple.jump
import com.foundation.app.simple.log
import com.foundation.app.simple.ui.EmptyActivity

/**
 * 单个fragment 测试
 * create by zhusw on 5/19/21 14:33
 */

open class VisibleOneFragment : BaseFragment2(R.layout.frag_visible) {
    private fun String.vLog() {
        this.log("VisibleOneFragment :")
    }

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

        private fun buildBundle(color: Int, text: String): Bundle {
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
        vbBinding.btnOpenNewPage.setOnClickListener {
            jump(EmptyActivity::class.java)
        }
    }

    override fun onFragmentVisibleChange(isVisible: Boolean, tag: String) {
        super.onFragmentVisibleChange(isVisible, tag)
        "onFragmentVisibleChange $tag : isVisible=$isVisible userVis=$userVisibleHint".vLog()//1
    }

    override fun onVisible(isFirst: Boolean) {
        super.onVisible(isFirst)
        "onVisible isFirst=$isFirst".vLog()//1
    }

    override fun onHidden() {
        super.onHidden()
        "onHidden ".vLog()//3
    }

    override fun onResume() {
        super.onResume()
        "onResume ".vLog()//2
    }

    override fun onPause() {
        super.onPause()
        "onPause ".vLog()//4
    }

}
