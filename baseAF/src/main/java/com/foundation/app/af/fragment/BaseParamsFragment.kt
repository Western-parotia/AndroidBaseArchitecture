package com.foundation.app.af.fragment

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foundation.app.af.utils.param.ParamsUtils

/**
 *@Desc:
 *- 参数绑定配置
 *create by zhusw on 5/17/21 19:10
 */
abstract class BaseParamsFragment : Fragment() {

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