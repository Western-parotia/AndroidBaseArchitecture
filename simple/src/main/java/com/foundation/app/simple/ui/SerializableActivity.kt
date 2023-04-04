package com.foundation.app.simple.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.arc.utils.param.BundleParamsUseSerializable
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActUserInfoBinding
import com.foundation.app.simple.ui.data.UserDescSerializable


/**
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 */
class SerializableActivity : BaseActivity() {

    companion object {
        fun enter(context: Context, userDesc: UserDescSerializable) {
            val intent = Intent(context, SerializableActivity::class.java)
            intent.putExtra("userDesc", userDesc)
            context.startActivity(intent)
        }
    }

    @BundleParamsUseSerializable
    @BundleParams("userDesc")
    private val userDesc: UserDescSerializable = UserDescSerializable()


    private val vbBinding by lazyAndSetRoot<ActUserInfoBinding>()

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun bindData() {

        vbBinding.auiTvUserId.text = "introduce: ${userDesc.introduce}"
        vbBinding.auiTvUserName.text = "height: ${userDesc.height}"
    }

}
