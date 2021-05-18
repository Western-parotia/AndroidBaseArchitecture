package com.foundation.app.simple.ui;

import android.os.Bundle;

import com.foundation.app.af.utils.param.BundleParams;
import com.foundation.app.simple.architecture.BaseFragment;
import com.foundation.app.simple.databinding.FragUserInfoBinding;
import com.foundation.app.simple.vm.AndroidVM;

import org.jetbrains.annotations.Nullable;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/18/21 09:40
 */
public class UserInfoFragment extends BaseFragment<FragUserInfoBinding> {
    AndroidVM vm;

    @BundleParams()
    private int userId = -1;

    @BundleParams()
    private String userName = "none";

    private UserAddress userAddress;

    private UserDesc userDesc;

    @Override
    public void initViewModel() {
        vm = getAppVM(AndroidVM.class);
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void observeData() {
        jViewBinding.auiTvUserId.setText(String.valueOf(userId));
        jViewBinding.auiTvUserName.setText(userName);
//        jViewBinding.auiTvAddress.setText(userAddress.toString());
//        jViewBinding.auiTvDesc.setText(userDesc.toString());
        vm.getImgLiveData().observe(getViewLifecycleOwner(), s -> {
            jViewBinding.auiTvVm.setText(s);
        });
    }
}
