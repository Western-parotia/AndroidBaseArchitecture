package com.foundation.app.af.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.foundation.app.af.utils.param.ParamsUtils

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 19:10
 */
open class BaseParamsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ParamsUtils.initWithFragment(this)
    }

}