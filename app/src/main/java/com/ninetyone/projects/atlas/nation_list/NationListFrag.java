package com.ninetyone.projects.atlas.nation_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetyone.projects.atlas.R;
import com.ninetyone.projects.atlas.main.MainActivity;
import com.ninetyone.projects.atlas.models.NationData;
import com.ninetyone.projects.atlas.mvp.BasePresenterFragment;
import com.ninetyone.projects.atlas.mvp.PresenterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationListFrag extends BasePresenterFragment<NationListContract.Presenter, NationListContract.View>
        implements NationListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private NationListContract.Presenter presenter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    NationListAdapter nationListAdapter;
    private ArrayList<NationData> nationData = new ArrayList<>();
    private ProgressBar mProgressBar;
    private TextView mErrorView;
    private static final String NATION_DATA = "nation_data";

    public static NationListFrag newInstance() {

        Bundle args = new Bundle();

        NationListFrag fragment = new NationListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nation_list, container, false);
        Toolbar mActionBarToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mActionBarToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_nation_list);
        nationListAdapter = new NationListAdapter(getContext(), nationData);
        LinearLayoutManager recyclerViewManager = new LinearLayoutManager(getActivity());
        recyclerViewManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(recyclerViewManager);
        mRecyclerView.setAdapter(nationListAdapter);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mErrorView = (TextView) rootView.findViewById(R.id.error_view);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (nationData == null || nationData.size() == 0) {
            presenter.fetchNationData(true);
        } else {
            setUpRecyclerView(nationData);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @NonNull
    @Override
    protected PresenterFactory<NationListContract.Presenter> getPresenterFactory() {
        return new NationListPresenterFactory();
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull NationListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int loaderId() {
        return BasePresenterFragment.NATION_LIST_LOADER_ID;
    }

    @Override
    public void onRefresh() {
        presenter.fetchNationData(false);
    }

    @Override
    public void setUpRecyclerView(ArrayList<NationData> nationData) {
        this.nationData = nationData;
        nationListAdapter.addNationDataInAdapter(nationData);
    }

    @Override
    public void handleInternetNotAvailable() {
        stopRefreshing();
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setText("Please check your internet connectivity");
    }

    @Override
    public void showLoadingIndicator(boolean loadingVisibility) {
        stopRefreshing();
        if (loadingVisibility) {
            mProgressBar.setVisibility(View.VISIBLE);
            mErrorView.setVisibility(View.VISIBLE);
            mErrorView.setText("Loading...");
        } else {
            mProgressBar.setVisibility(View.GONE);
            mErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void serverError() {
        stopRefreshing();
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setText("Something went wrong! Please try again later.");
    }

    private void stopRefreshing() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopRefreshing();
        presenter.cancelCall();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<NationData> filteredModelList = filter(nationData, newText);
                nationListAdapter.setFilter(filteredModelList);
                return true;
            }
        });
    }

    private List<NationData> filter(List<NationData> models, String query) {
        query = query.toLowerCase();

        final List<NationData> filteredModelList = new ArrayList<>();
        for (NationData model : models) {
            final String text = getMatchStringForNation(model);
            if (text.trim().toLowerCase().contains(query.toLowerCase())) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private String getMatchStringForNation(NationData model) {
        String str = model.getName() + " " +
                model.getCapital() + " " +
                model.getAlpha3Code() + " " +
                model.getRegion() + " " +
                model.getSubregion() + " " +
                model.getPopulation() + " " +
                model.getDemonym() + " " +
                model.getNumericCode() + " " +
                model.getNativeName() + " ";
        if (model.getCallingCodes() != null) {
            for (String codes : model.getCallingCodes()) {
                str += codes + " ";
            }
        }
        if (model.getTimezones() != null) {
            for (String timezones : model.getTimezones()) {
                str += timezones + " ";
            }
        }
        if (model.getCurrencies() != null) {
            for (NationData.CurrencyData currency : model.getCurrencies()) {
                str += currency.getName() + " " + currency.getCode() + " ";
            }
        }
        if (model.getLanguages() != null) {
            for (NationData.LanguageData language : model.getLanguages()) {
                str += language.getName() + " " + language.getNativeName() + " ";
            }
        }
        if (model.getRegionalBlocs() != null) {
            for (NationData.BlocsData bloc : model.getRegionalBlocs()) {
                str += bloc.getName() + " " + bloc.getAcronym() + " ";
            }
        }

        return str.trim();
    }

    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        if (nationData != null && nationData.size() > 0) {
            state.putSerializable(NATION_DATA, nationData);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            nationData = (ArrayList<NationData>) savedInstanceState.getSerializable(NATION_DATA);
        }
    }
}
