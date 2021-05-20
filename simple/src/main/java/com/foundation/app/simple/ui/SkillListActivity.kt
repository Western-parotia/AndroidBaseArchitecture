package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.ui.fragment.SkillListFragment

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:42
 */

class SkillListActivity : BaseActivity() {

    override fun getContentVB(): ViewBinding? = null

    override fun init(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, SkillListFragment(), "SkillListFragment")
            .commitNowAllowingStateLoss()
    }

    override fun bindData() {

    }
}

