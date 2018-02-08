package com.example.kyn.knowyounation.main;

import com.example.kyn.knowyounation.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class MainPresenterFactory implements PresenterFactory<MainContract.Presenter> {
    @Override
    public MainPresenter create() {
        return new MainPresenter();
    }
}
