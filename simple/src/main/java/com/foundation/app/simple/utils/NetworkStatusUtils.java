package com.foundation.app.simple.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * create by zhusw on 5/24/21 16:07
 */
public class NetworkStatusUtils {
    public static void init(Application app) {

    }

    public static boolean networkIsConnected(Context context) {
        if (null != context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null != cm) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Network[] networks = cm.getAllNetworks();
                    boolean networkIsConnected = false;
                    if (null != networks && networks.length > 0) {
                        NetworkInfo networkInfo;
                        for (int i = networks.length - 1; i >= 0; i--) {
                            networkInfo = cm.getNetworkInfo(networks[i]);
                            if (networkInfo != null && networkInfo.isConnected()) {
                                networkIsConnected = true;
                                break;
                            }
                        }
                    }
                    return networkIsConnected;
                } else {
                    NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    boolean isWifiConnected = networkInfo.isConnected();
                    networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    boolean isMobileConnected = networkInfo.isConnected();
                    return isWifiConnected || isMobileConnected;
                }
            } else {
                throw new NullPointerException("cann not obtain  ConnectivityManager," +
                        "check internet permission  or Mobile Phone Network Setting");
            }
        }
        return false;
    }
}
