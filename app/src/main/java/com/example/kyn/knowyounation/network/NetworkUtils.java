package com.example.kyn.knowyounation.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.kyn.knowyounation.NationApp;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NetworkUtils {

    public static boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) NationApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }
}
