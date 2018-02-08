package com.example.kyn.knowyounation.main;

import com.example.kyn.knowyounation.mvp.BasePresenter;
import com.example.kyn.knowyounation.mvp.BaseView;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class MainContract {

    public interface View extends BaseView<Presenter> {
    }

    public interface Presenter extends BasePresenter<View> {
    }
}
