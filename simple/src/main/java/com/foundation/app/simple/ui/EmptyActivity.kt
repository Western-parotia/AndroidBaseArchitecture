package com.foundation.app.simple.ui

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.architecture.BaseActivity

/**
 * create by zhusw on 5/19/21 15:59
 */
class EmptyActivity : BaseActivity() {
    override fun afterSuperOnCreate(savedInstanceState: Bundle?) {
        super.afterSuperOnCreate(savedInstanceState)
        setContentView(TextView(this).apply {
            textSize = 90F
            text = "空白页面"
            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(-1, -1)
        })
    }

    override fun getContentVB(): ViewBinding? = null

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun bindData() {

    }
}