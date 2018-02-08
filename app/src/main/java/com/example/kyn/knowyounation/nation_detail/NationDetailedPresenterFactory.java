package com.example.kyn.knowyounation.nation_detail;

import com.example.kyn.knowyounation.mvp.PresenterFactory;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NationDetailedPresenterFactory implements PresenterFactory<NationDetailedContract.Presenter> {
    @Override
    public NationDetailedPresenter create() {
        return new NationDetailedPresenter();
    }
}
