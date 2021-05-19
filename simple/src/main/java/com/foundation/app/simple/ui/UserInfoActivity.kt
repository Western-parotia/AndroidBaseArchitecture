package com.foundation.app.simple.ui

import android.os.Bundle
import com.foundation.app.arc.utils.ext.autoBind
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActUserInfoBinding
import com.foundation.app.simple.vm.AndroidVM


/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 *
 */
class UserInfoActivity : BaseActivity() {

    @BundleParams
    private val userId: Int = 0

    @BundleParams
    private val userName: String = "none"

    @BundleParams("address")
    private val userAddress: UserAddress = UserAddress()

    @BundleParams("desc")
    private val userDesc: UserDesc = UserDesc()

    private val vbBinding by autoBind<ActUserInfoBinding>()
    private val vm by lazyAppVM<AndroidVM>()

    override fun init(savedInstanceState: Bundle?) {

        vbBinding.auiVm.setOnClickListener {
            vm.imgLiveData.value = "activity update img${System.currentTimeMillis()}"
        }
        vbBinding.auiBtn.setOnClickListener {
            val frag = UserInfoFragment()
            val bundle = Producer.create()
            frag.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.aui_fl, frag, "UserInfoFragment")
                .commitNowAllowingStateLoss()
        }
    }

    override fun observeData() {
        vbBinding.auiTvUserId.text = userId.toString()
        vbBinding.auiTvUserName.text = userName
        vbBinding.auiTvAddress.text = userAddress.toString()
        vbBinding.auiTvDesc.text = userDesc.toString()
        vm.imgLiveData.observe(this, {
            vbBinding.auiVm.text = it
        })

    }


}