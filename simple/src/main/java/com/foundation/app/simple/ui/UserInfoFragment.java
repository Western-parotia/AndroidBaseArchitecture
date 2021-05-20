package com.foundation.app.simple.ui;

import android.os.Bundle;

import com.foundation.app.arc.utils.param.BundleParams;
import com.foundation.app.simple.architecture.BaseFragment;
import com.foundation.app.simple.databinding.FragUserInfoBinding;

import org.jetbrains.annotations.Nullable;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/18/21 09:40
 */
public class UserInfoFragment extends BaseFragment<FragUserInfoBinding> {

    @BundleParams("userId")
    private int userId = -1;

    @BundleParams("userName")
    private String userName = "none";

    @Override
    public void initViewModel() {

    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void bindData() {
        jViewBinding.auiTvUserId.setText(String.valueOf(userId));
        jViewBinding.auiTvUserName.setText(userName);
    }
}
