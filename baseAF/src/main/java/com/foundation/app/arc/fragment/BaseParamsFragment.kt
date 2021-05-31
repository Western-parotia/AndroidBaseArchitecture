package com.foundation.app.arc.fragment

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.arc.utils.param.ParamsUtils

/**
 * 参数自动绑定
 *create by zhusw on 5/17/21 19:10
 */
abstract class BaseParamsFragment : BaseVisibilityFragment() {

    protected lateinit var hostActivity: AppCompatActivity
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (openAutoBindParams()) {
            ParamsUtils.initWithFragment(this)
        }
    }

    /**
     * 是否开启参数自动绑定
     */
    protected open fun openAutoBindParams(): Boolean = true
}