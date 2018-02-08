package com.ninetyone.projects.atlas.mvp;

public interface BasePresenter<V> {

    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
