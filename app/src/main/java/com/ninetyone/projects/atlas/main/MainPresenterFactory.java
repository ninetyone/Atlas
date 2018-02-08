package com.ninetyone.projects.atlas.main;

import com.ninetyone.projects.atlas.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class MainPresenterFactory implements PresenterFactory<MainContract.Presenter> {
    @Override
    public MainPresenter create() {
        return new MainPresenter();
    }
}
