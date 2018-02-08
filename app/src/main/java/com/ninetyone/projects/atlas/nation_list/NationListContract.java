package com.ninetyone.projects.atlas.nation_list;

import com.ninetyone.projects.atlas.models.NationData;
import com.ninetyone.projects.atlas.mvp.BasePresenter;
import com.ninetyone.projects.atlas.mvp.BaseView;

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
