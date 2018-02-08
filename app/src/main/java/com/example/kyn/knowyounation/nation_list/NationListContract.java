package com.example.kyn.knowyounation.nation_list;

import com.example.kyn.knowyounation.models.NationData;
import com.example.kyn.knowyounation.mvp.BasePresenter;
import com.example.kyn.knowyounation.mvp.BaseView;

import java.util.ArrayList;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationListContract {

    public interface View extends BaseView<Presenter> {
        void setUpRecyclerView(ArrayList<NationData> body);

        void handleInternetNotAvailable();

        void showLoadingIndicator(boolean loadingVisibility);

        void serverError();
    }

    public interface Presenter extends BasePresenter<View> {
        void fetchNationData(boolean showLoadingView);

        void cancelCall();
    }
}
