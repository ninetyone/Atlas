package com.ninetyone.projects.atlas.nation_list;

import com.ninetyone.projects.atlas.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationListPresenterFactory implements PresenterFactory<NationListContract.Presenter> {
    @Override
    public NationListPresenter create() {
        return new NationListPresenter();
    }
}
