package com.example.kyn.knowyounation.nation_list;

import com.example.kyn.knowyounation.NationApp;
import com.example.kyn.knowyounation.database.DbSingleton;
import com.example.kyn.knowyounation.models.NationData;
import com.example.kyn.knowyounation.network.NationNetworkApi;
import com.example.kyn.knowyounation.network.NetworkUtils;
import com.example.kyn.knowyounation.network.NetworkWrapper;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ninetyone on 19/11/17.
 */

class NationListPresenter implements NationListContract.Presenter {

    private NationListContract.View view;
    private NationNetworkApi nationNetworkApi;
    private Call<ArrayList<NationData>> nationDataCall;

    @Override
    public void onViewAttached(NationListContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void fetchNationData(boolean showLoadingView) {
        ArrayList<NationData> nationDataList = DbSingleton.getInstance().readAllNationData();
        if (nationDataList.size() == 0) {
            if (!NetworkUtils.isConnectedToInternet()) {
                if (view != null) {
                    view.handleInternetNotAvailable();
                }
                return;
            } else {
                if (showLoadingView) {
                    if (view != null) {
                        view.showLoadingIndicator(true);
                    }
                }
            }
        } else {
            if (view != null && showLoadingView) {
                view.showLoadingIndicator(true);
                HashMap<Integer, ArrayList<String>> callingCodes = DbSingleton.getInstance().readCallingCodeData();
                HashMap<Integer, ArrayList<String>> topLevelDomains = DbSingleton.getInstance().readTopLevelDomainData();
                HashMap<Integer, ArrayList<String>> timezones = DbSingleton.getInstance().readTimezoneData();
                HashMap<Integer, ArrayList<String>> borders = DbSingleton.getInstance().readBorderData();
                HashMap<Integer, ArrayList<Double>> latLong = DbSingleton.getInstance().readLatLongData();
                HashMap<Integer, ArrayList<NationData.CurrencyData>> currencies = DbSingleton.getInstance().readCurrencyData();
                HashMap<Integer, ArrayList<NationData.LanguageData>> languages = DbSingleton.getInstance().readLanguageData();
                HashMap<Integer, ArrayList<NationData.BlocsData>> blocs = DbSingleton.getInstance().readBlocData();
                HashMap<Integer, HashMap<String, String>> translations = DbSingleton.getInstance().readTranslationData();

               for (int i = 0; i < nationDataList.size(); i++) {
                   nationDataList.get(i).setCallingCodes(callingCodes.get(i));
                    nationDataList.get(i).setTopLevelDomain(topLevelDomains.get(i));
                    nationDataList.get(i).setTimezones(timezones.get(i));
                    nationDataList.get(i).setBorders(borders.get(i));
                    nationDataList.get(i).setLatlng(latLong.get(i));
                    nationDataList.get(i).setCurrencies(currencies.get(i));
                    nationDataList.get(i).setLanguages(languages.get(i));
                    nationDataList.get(i).setRegionalBlocs(blocs.get(i));
                    nationDataList.get(i).setTranslations(translations.get(i));
                }
                view.showLoadingIndicator(false);
                view.setUpRecyclerView(nationDataList);
                return;
            }
        }

        nationDataCall = getNationNetworkApi().fetchNationData();
        NationApp.getInstance().getNetworkWrapper().enqueue(nationDataCall, view, new NetworkWrapper.NetworkCallback<ArrayList<NationData>>() {
            @Override
            public void onResponse(Call<ArrayList<NationData>> call, Response<ArrayList<NationData>> response) {
                if (response != null) {
                    if (view != null) {
                        view.showLoadingIndicator(false);
                    }
                    if (response.isSuccessful()) {
                        if (view != null) {
                            if (response.body() != null && response.body().size() > 0) {
                                view.setUpRecyclerView(response.body());
                                DbSingleton.getInstance().deleteAllNationData();
                                DbSingleton.getInstance().createNationData(response.body());
                                DbSingleton.getInstance().createCallingCodeData(response.body());
                                DbSingleton.getInstance().createTopLevelDomainData(response.body());
                                DbSingleton.getInstance().createTimezoneData(response.body());
                                DbSingleton.getInstance().createBorderData(response.body());
                                DbSingleton.getInstance().createLatLongData(response.body());
                                DbSingleton.getInstance().createCurrencyData(response.body());
                                DbSingleton.getInstance().createLanguageData(response.body());
                                DbSingleton.getInstance().createBlocData(response.body());
                                DbSingleton.getInstance().createTranslationData(response.body());
                            }
                        }
                    } else {
                        onFailure(null, new Throwable("Api not working"));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NationData>> call, Throwable t) {
                if (view != null) {
                    view.serverError();
                }
            }
        });
    }

    @Override
    public void cancelCall() {
        if (nationDataCall != null) {
            nationDataCall.cancel();
        }
    }

    private NationNetworkApi getNationNetworkApi() {
        if (nationNetworkApi == null) {
            nationNetworkApi = NationApp.getInstance().getNetworkWrapper().getClient();
        }
        return nationNetworkApi;
    }
}
