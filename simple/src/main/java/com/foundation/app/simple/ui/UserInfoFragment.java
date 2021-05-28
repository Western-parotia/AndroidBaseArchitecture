package com.foundation.app.simple.ui;

import android.os.Bundle;

import com.foundation.app.arc.utils.param.BundleParams;
import com.foundation.app.simple.architecture.BaseFragment;
import com.foundation.app.simple.databinding.FragUserInfoBinding;

import org.jetbrains.annotations.Nullable;

/**
 * @Desc: -
 * 这是java 示例
 * create by zhusw on 5/18/21 09:40
 */
public class UserInfoFragment extends BaseFragment<FragUserInfoBinding> {

    @BundleParams("userId")
    private int userId = -1;

    @BundleParams("userName")
    private String userName = "没自动赋值";

    @Override
    public void initViewModel() {

    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void bindData() {
        jViewBinding.auiTvUserId.setText("用户ID:" + String.valueOf(userId));
        jViewBinding.auiTvUserName.setText("用户名：" + userName);
    }
}
