package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.ui.fragment.SkillListFragment

/**

*-
 *-
 *create by zhusw on 5/11/21 14:42
 */

class SkillListActivity : BaseActivity() {

    override fun getContentVB(): ViewBinding? = null

    override fun init(savedInstanceState: Bundle?) {
        switchFragment(SkillListFragment(), android.R.id.content)
    }

    override fun bindData() {

    }
}

