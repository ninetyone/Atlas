package com.ninetyone.projects.atlas.nation_detail;

import com.ninetyone.projects.atlas.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NationDetailedPresenterFactory implements PresenterFactory<NationDetailedContract.Presenter> {
    @Override
    public NationDetailedPresenter create() {
        return new NationDetailedPresenter();
    }
}
