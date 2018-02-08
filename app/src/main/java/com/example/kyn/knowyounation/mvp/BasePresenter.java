package com.example.kyn.knowyounation.mvp;

public interface BasePresenter<V> {

    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
