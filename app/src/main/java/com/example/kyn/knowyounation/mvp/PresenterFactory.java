package com.example.kyn.knowyounation.mvp;

public interface PresenterFactory<T extends BasePresenter> {
    T create();
}
