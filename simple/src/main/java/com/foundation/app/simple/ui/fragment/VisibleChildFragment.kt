package com.foundation.app.simple.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
open class VisibleChildFragment : BaseFragment2(R.layout.frag_visible) {
    private fun String.vLog() {
        this.log("${VisibleChildFragment::class.java.simpleName} : $text: ")
    }


    private val vbBinding by initVB<FragVisibleBinding>()

    @BundleParams("color")
    val color: Int = Color.parseColor("#3e3e3e")

    @BundleParams("text")
    val text: String = "-"

    companion object {
        fun newInstance(color: Int, text: String): VisibleChildFragment {
            val f = VisibleChildFragment()
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

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        vbBinding.btnOpenNewPage.setOnClickListener {
            jump(EmptyActivity::class.java)
        }
    }

    override fun bindData() {
        super.bindData()
        vbBinding.btn.text = text
        vbBinding.root.setBackgroundColor(Color.WHITE)
    }


    override fun onFragmentVisibleChange(isVisible: Boolean, tag: String) {
        "onFragmentVisibleChange $tag : isVisible=$isVisible parentVisible=${checkParentFragmentIsVisible()}".vLog()//1
        super.onFragmentVisibleChange(isVisible, tag)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate ".vLog()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy ".vLog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        "onCreateView ".vLog()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        "onDestroyView ".vLog()
    }
}
