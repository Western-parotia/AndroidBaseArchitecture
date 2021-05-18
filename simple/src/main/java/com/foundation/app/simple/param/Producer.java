package com.foundation.app.simple.param;

import android.os.Bundle;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/18/21 10:39
 */
public class Producer {
    public static Bundle create() {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", 1);
        bundle.putString("userName", "YonGo");
        UserAddress address = new UserAddress("inJava", 1);
        UserDesc desc = new UserDesc("inJava", 1);
        bundle.putParcelable("address", address);
        bundle.putParcelable("desc", desc);
        return bundle;
    }
}