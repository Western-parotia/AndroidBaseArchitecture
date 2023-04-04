package com.foundation.app.simple

import android.os.Bundle
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActVbTestBinding

/**
 * create by zhusw on 6/10/21 17:47
 */
class VBIncludeTestActivity : BaseActivity() {
    val vb by lazyAndSetRoot<ActVbTestBinding>()
    override fun init(savedInstanceState: Bundle?) {
        vb.include1.tvChild.setOnClickListener {
            "click:vb.include1.tvChild".toast()
        }
        vb.tvParent.setOnClickListener {
            "click:vb.tvParent".toast()
        }
    }

    override fun bindData() {

    }
}