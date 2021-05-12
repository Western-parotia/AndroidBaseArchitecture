package com.foundation.app.simple;

import org.jetbrains.annotations.NotNull;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/12/21 17:47
 */
public class JavaMainFoo implements KJAPI {

    public static void bind(String view) {
        System.out.println("非拓展 view=" + view);
    }

    @NotNull
    @Override
    public String name() {
        return "JavaMainFoo KJAPI";
    }
}
