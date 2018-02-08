package com.ninetyone.projects.atlas.nation_detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ninetyone.projects.atlas.models.NationData;

import java.util.Map;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NationDetailedPresenter implements NationDetailedContract.Presenter {
    @Nullable
    private NationDetailedContract.View view;

    @Override
    public void onViewAttached(NationDetailedContract.View view) {
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
    public void checkValidityForCapital(@NonNull NationData nationData) {
        if (nationData.getCapital() != null && view != null) {
            view.showCapital(nationData.getCapital());
        }
    }

    @Override
    public void checkValidityForCountry(@NonNull NationData nationData) {
        if (nationData.getName() != null && view != null) {
            view.showCountry(nationData.getName());
        }
    }

    @Override
    public void checkValidityForCode(@NonNull NationData nationData) {
        if (nationData.getAlpha3Code() != null && view != null) {
            view.showAlpha3Code(nationData.getAlpha3Code());
        }
    }

    @Override
    public void checkValidityForArea(@NonNull NationData nationData) {
        if (view != null) {
            view.showArea(nationData.getArea());
        }
    }

    @Override
    public void checkValidityForDomain(@NonNull NationData nationData) {
        String domainData = "";
        if (nationData.getTopLevelDomain() != null) {
            for (String domain : nationData.getTopLevelDomain()) {
                if (domain != null) {
                    domainData += domain + "\n";
                }
            }
        }
        if (view != null) {
            view.showDomain(domainData);
        }
    }

    @Override
    public void checkValidityForRegion(@NonNull NationData nationData) {
        if (nationData.getRegion() != null && view != null) {
            view.showRegion(nationData.getRegion());
        }
    }

    @Override
    public void checkValidityForSubregion(@NonNull NationData nationData) {
        if (nationData.getSubregion() != null && view != null) {
            view.showSubregion(nationData.getSubregion());
        }
    }

    @Override
    public void checkValidityForPopulation(@NonNull NationData nationData) {
        if (view != null) {
            view.showPopulation(nationData.getPopulation());
        }
    }

    @Override
    public void checkValidityForCallingCodes(@NonNull NationData nationData) {
        String cc = "";
        if (nationData.getCallingCodes() != null) {
            for (String codes : nationData.getCallingCodes()) {
                if (codes != null) {
                    cc += codes + "\n";
                }
            }
        }
        if (view != null) {
            view.showCallingCodes(cc);
        }
    }

    @Override
    public void checkValidityForBorders(@NonNull NationData nationData) {
        String borderData = "";
        if (nationData.getBorders() != null) {
            for (String border : nationData.getBorders()) {
                if (border != null) {
                    borderData += border + "\n";
                }
            }
        }
        if (view != null) {
            view.showBorders(borderData);
        }
    }

    @Override
    public void checkValidityForLatlng(@NonNull NationData nationData) {
        if (view != null && nationData.getLatlng() != null
                && nationData.getLatlng().size() > 0
                && nationData.getLatlng().get(0) != null
                && nationData.getLatlng().get(1) != null) {
            view.showLatlng(nationData.getLatlng());
        }
    }

    @Override
    public void checkValidityForDemonym(@NonNull NationData nationData) {
        if (nationData.getDemonym() != null && view != null) {
            view.showDemonym(nationData.getDemonym());
        }
    }

    @Override
    public void checkValidityForGini(@NonNull NationData nationData) {
        if (view != null) {
            view.showGini(nationData.getGini());
        }
    }

    @Override
    public void checkValidityForTimeZone(@NonNull NationData nationData) {
        String timeZone = "";
        if (nationData.getTimezones() != null) {
            for (String time : nationData.getTimezones()) {
                if (time != null) {
                    timeZone += time + "\n";
                }
            }
        }
        if (view != null) {
            view.showTimeZone(timeZone);
        }
    }

    @Override
    public void checkValidityForNativeName(@NonNull NationData nationData) {
        if (nationData.getNativeName() != null && view != null) {
            view.showNativeName(nationData.getNativeName());
        }
    }

    @Override
    public void checkValidityForNumericCode(@NonNull NationData nationData) {
        if (nationData.getNumericCode() != null && view != null) {
            view.showNumericCode(nationData.getNumericCode());
        }
    }

    @Override
    public void checkValidityForCurrencies(@NonNull NationData nationData) {
        String currencyData = "";
        if (nationData.getCurrencies() != null) {
            for (NationData.CurrencyData currency : nationData.getCurrencies()) {
                if (currency != null) {
                    if (currency.getName() != null)
                        currencyData += currency.getName();
                    if (currency.getSymbol() != null)
                        currencyData += " (" + currency.getSymbol() + ")";
                    if (currency.getCode() != null)
                        currencyData += " (" + currency.getCode() + ")";
                    currencyData += "\n";
                }
            }
        }
        if (view != null) {
            view.showCurrencies(currencyData);
        }
    }

    @Override
    public void checkValidityForLanguage(@NonNull NationData nationData) {
        String languageData = "";
        if (nationData.getLanguages() != null) {
            for (NationData.LanguageData language : nationData.getLanguages()) {
                if (language != null) {
                    if (language.getName() != null)
                        languageData += language.getName();
                    if (language.getNativeName() != null)
                        languageData += " (" + language.getNativeName() + ")";
                    languageData += "\n";
                }
            }
        }
        if (view != null) {
            view.showLanguage(languageData);
        }
    }

    @Override
    public void checkValidityForTranslation(@NonNull NationData nationData) {
        String translationData = "";
        for (Map.Entry<String, String> entry : nationData.getTranslations().entrySet()) {
            if (entry != null && entry.getKey() != null && entry.getValue() != null)
                translationData += entry.getKey() + " : " + entry.getValue() + "\n";
        }
        if (translationData != null && view != null) {
            view.showTranslation(translationData);
        }
    }
}
