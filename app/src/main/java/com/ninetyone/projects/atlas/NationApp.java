package com.ninetyone.projects.atlas;

import android.app.Application;
import android.support.annotation.Nullable;

import com.ninetyone.projects.atlas.database.DbSingleton;
import com.ninetyone.projects.atlas.mvp.BasePresenterActivity;
import com.ninetyone.projects.atlas.network.NetworkWrapper;

import okhttp3.OkHttpClient;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationApp extends Application {

    private static NationApp instance;
    private NetworkWrapper networkWrapper;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    public static final String BASE_URL = "https://restcountries.eu/";

    @Nullable
    private BasePresenterActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DbSingleton.initializeInstance(this);
        networkWrapper = NetworkWrapper.getInstance();
    }

    public NetworkWrapper getNetworkWrapper() {
        return networkWrapper;
    }

    public OkHttpClient getOkhttpClient() {
        return okHttpClient;
    }

    /**
     * Use this to get Application Context
     *
     * @return instance of Application class
     */
    public static NationApp getInstance() {
        return instance;
    }

    @Nullable
    public BasePresenterActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(@Nullable BasePresenterActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
