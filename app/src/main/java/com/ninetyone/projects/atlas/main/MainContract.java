package com.ninetyone.projects.atlas.main;

import com.ninetyone.projects.atlas.mvp.BasePresenter;
import com.ninetyone.projects.atlas.mvp.BaseView;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class MainContract {

    public interface View extends BaseView<Presenter> {
    }

    public interface Presenter extends BasePresenter<View> {
    }
}
