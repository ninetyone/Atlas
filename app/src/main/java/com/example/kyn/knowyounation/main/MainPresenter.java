package com.example.kyn.knowyounation.main;

import android.support.annotation.Nullable;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class MainPresenter implements MainContract.Presenter {
    @Nullable
    private MainContract.View view;


    @Override
    public void onViewAttached(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {

    }
}

