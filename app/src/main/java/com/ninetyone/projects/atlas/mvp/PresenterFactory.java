package com.ninetyone.projects.atlas.mvp;

public interface PresenterFactory<T extends BasePresenter> {
    T create();
}
