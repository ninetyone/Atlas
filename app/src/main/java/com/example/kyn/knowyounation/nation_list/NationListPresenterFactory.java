package com.example.kyn.knowyounation.nation_list;

import com.example.kyn.knowyounation.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationListPresenterFactory implements PresenterFactory<NationListContract.Presenter> {
    @Override
    public NationListPresenter create() {
        return new NationListPresenter();
    }
}
