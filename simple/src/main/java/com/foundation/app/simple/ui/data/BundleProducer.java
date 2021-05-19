package com.foundation.app.simple.ui.data;
import android.os.Bundle;

/**
 * @Desc: -
 * - 测试java 基本参数类型
 * create by zhusw on 5/18/21 10:39
 */
public class BundleProducer {
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
