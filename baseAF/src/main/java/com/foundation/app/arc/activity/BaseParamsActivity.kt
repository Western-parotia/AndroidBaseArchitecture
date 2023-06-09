package com.foundation.app.arc.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.arc.utils.param.ParamsUtils

/**
 * create by zhusw on 5/17/21 15:12
 */
abstract class BaseParamsActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //此intent中可能存在逻辑分叉点，暂不提供参数自动覆盖，否则容易导致隐式逻辑
        //如有需要自行重写调用：ParamsUtils.initWithActivity(this,intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (openAutoBindParams()) {
            ParamsUtils.initWithActivity(this, intent)
        }
    }

    /**
     * 是否开启参数自动绑定
     */
    protected open fun openAutoBindParams(): Boolean = true
}