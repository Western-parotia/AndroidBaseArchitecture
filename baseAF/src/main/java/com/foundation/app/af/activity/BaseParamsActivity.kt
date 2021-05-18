package com.foundation.app.af.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.af.utils.param.ParamsUtils

/**
 *@Desc:VM 快捷初始化
 *create by zhusw on 5/17/21 15:12
 */
abstract class BaseParamsActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //此intent中可能存在逻辑分叉点，暂不提供参数自动覆盖，否则容易导致强耦合的隐士逻辑
        //如有需要自行重写调用：ParamsUtils.initWithActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ParamsUtils.initWithActivity(this)
    }


}