package com.foundation.app.simple.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActUserInfoBinding
import com.foundation.app.simple.ui.data.UserDesc


/**

*-
 *-
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 *
 */
class ParcelableActivity : BaseActivity() {

    companion object {
        fun enter(context: Context, userDesc: UserDesc) {
            val intent = Intent(context, ParcelableActivity::class.java)
            intent.putExtra("userDesc", userDesc)
            context.startActivity(intent)
        }
    }

    @BundleParams("userDesc")
    private val userDesc: UserDesc = UserDesc()


    private val vbBinding by lazyVB<ActUserInfoBinding>()

    override fun getContentVB(): ViewBinding = vbBinding

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun bindData() {

        vbBinding.auiTvUserId.text = "introduce: ${userDesc.introduce}"
        vbBinding.auiTvUserName.text = "height: ${userDesc.height}"
    }

}
