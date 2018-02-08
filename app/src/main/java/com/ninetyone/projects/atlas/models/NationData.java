package com.ninetyone.projects.atlas.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationData implements Serializable {

    private static final long serialVersionUID = -4593042293528433422L;
    @SerializedName("name")
    private String name;
    @SerializedName("topLevelDomain")
    private ArrayList<String> topLevelDomain;
    @SerializedName("alpha2Code")
    private String alpha2Code;
    @SerializedName("alpha3Code")
    private String alpha3Code;
    @SerializedName("callingCodes")
    private ArrayList<String> callingCodes;
    @SerializedName("capital")
    private String capital;
    @SerializedName("altSpellings")
    private ArrayList<String> altSpellings;
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subregion;
    @SerializedName("population")
    private long population;
    @SerializedName("latlng")
    private ArrayList<Double> latlng;
    @SerializedName("demonym")
    private String demonym;
    @SerializedName("area")
    private double area;
    @SerializedName("gini")
    private double gini;
    @SerializedName("timezones")
    private ArrayList<String> timezones;
    @SerializedName("borders")
    private ArrayList<String> borders;
    @SerializedName("nativeName")
    private String nativeName;
    @SerializedName("numericCode")
    private String numericCode;
    @SerializedName("currencies")
    private ArrayList<CurrencyData> currencies;
    @SerializedName("languages")
    private ArrayList<LanguageData> languages;
    @SerializedName("translations")
    private Map<String, String> translations;
    @SerializedName("flag")
    private String flag;
    @SerializedName("regionalBlocs")
    private ArrayList<BlocsData> regionalBlocs;
    @SerializedName("cioc")
    private String cioc;

    @Override
    public boolean equals(Object object) {
        if (object instanceof NationData) {
            NationData objectToCompare = (NationData) object;

            if (this.name.equals(objectToCompare.getName())) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopLevelDomain(ArrayList<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public ArrayList<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(ArrayList<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public ArrayList<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(ArrayList<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public ArrayList<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(ArrayList<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(ArrayList<String> timezones) {
        this.timezones = timezones;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    public ArrayList<CurrencyData> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<CurrencyData> currencies) {
        this.currencies = currencies;
    }

    public ArrayList<LanguageData> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<LanguageData> languages) {
        this.languages = languages;
    }

    public ArrayList<BlocsData> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(ArrayList<BlocsData> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public double getGini() {
        return gini;
    }

    public void setGini(double gini) {
        this.gini = gini;
    }

    public static class CurrencyData implements Serializable {
        private static final long serialVersionUID = 1436301979559085280L;
        private String code;
        private String name;
        private String symbol;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class LanguageData implements Serializable {
        private static final long serialVersionUID = -9018446942590778942L;
        private String name;
        private String nativeName;


        public String getNativeName() {
            return nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class BlocsData implements Serializable {
        private static final long serialVersionUID = -2233772020120452705L;
        private String acronym;
        private String name;

        public String getAcronym() {
            return acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
