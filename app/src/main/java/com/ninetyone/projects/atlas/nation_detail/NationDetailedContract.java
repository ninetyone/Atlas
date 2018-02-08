package com.ninetyone.projects.atlas.nation_detail;

import android.support.annotation.NonNull;

import com.ninetyone.projects.atlas.models.NationData;
import com.ninetyone.projects.atlas.mvp.BasePresenter;
import com.ninetyone.projects.atlas.mvp.BaseView;

import java.util.ArrayList;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NationDetailedContract {
    public interface View extends BaseView<NationDetailedContract.Presenter> {

        void showCountry(String name);

        void showCapital(String capital);

        void showAlpha3Code(String alpha3Code);

        void showRegion(String region);

        void showSubregion(String subregion);

        void showDemonym(String demonym);

        void showGini(double gini);

        void showNativeName(String nativeName);

        void showNumericCode(String numericCode);

        void showArea(double area);

        void showPopulation(long population);

        void showLatlng(ArrayList<Double> latlng);

        void showTimeZone(String timeZone);

        void showCallingCodes(String cc);

        void showBorders(String borderData);

        void showDomain(String domainData);

        void showCurrencies(String currencyData);

        void showLanguage(String languageData);

        void showTranslation(String translationData);
    }

    public interface Presenter extends BasePresenter<NationDetailedContract.View> {

        void checkValidityForCapital(@NonNull NationData nationData);

        void checkValidityForCountry(@NonNull NationData nationData);

        void checkValidityForCode(@NonNull NationData nationData);

        void checkValidityForArea(@NonNull NationData nationData);

        void checkValidityForDomain(@NonNull NationData nationData);

        void checkValidityForRegion(@NonNull NationData nationData);

        void checkValidityForSubregion(@NonNull NationData nationData);

        void checkValidityForPopulation(@NonNull NationData nationData);

        void checkValidityForCallingCodes(@NonNull NationData nationData);

        void checkValidityForBorders(@NonNull NationData nationData);

        void checkValidityForLatlng(@NonNull NationData nationData);

        void checkValidityForDemonym(@NonNull NationData nationData);

        void checkValidityForGini(@NonNull NationData nationData);

        void checkValidityForTimeZone(@NonNull NationData nationData);

        void checkValidityForNativeName(@NonNull NationData nationData);

        void checkValidityForNumericCode(@NonNull NationData nationData);

        void checkValidityForCurrencies(@NonNull NationData nationData);

        void checkValidityForLanguage(@NonNull NationData nationData);

        void checkValidityForTranslation(@NonNull NationData nationData);
    }
}
