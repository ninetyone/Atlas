package com.ninetyone.projects.atlas.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ninetyone.projects.atlas.models.NationData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ninetyone on 24/11/17.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KynDatabase.db";
    private static final int DATABASE_VERSION = 2;

    /*********************************************/
    //TABLES
    /*********************************************/
    private static final String TABLE_NATION_DATA = "table_nation_data";
    private static final String TABLE_CALLING_CODE_DATA = "table_calling_code_data";
    private static final String TABLE_TOP_LEVEL_DOMAIN_DATA = "table_top_level_domain_data";
    private static final String TABLE_TIMEZONES_DATA = "table_timezones_data";
    private static final String TABLE_BORDERS_DATA = "table_border_data";
    private static final String TABLE_LATLONG_DATA = "table_latlong_data";
    private static final String TABLE_CURRENCY_DATA = "table_currency_data";
    private static final String TABLE_LANGUAGE_DATA = "table_language_data";
    private static final String TABLE_BLOC_DATA = "table_bloc_data";
    private static final String TABLE_TRANSLATIONS_DATA = "table_translations_data";

    /*********************************************/
    //COLUMNS
    /*********************************************/
    //TABLE_NATION_DATA
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ALPHA_3_CODE = "alpha_3_code";
    private static final String CAPITAL = "capital";
    private static final String REGION = "region";
    private static final String SUBREGION = "subregion";
    private static final String POPULATION = "population";
    private static final String DEMONYM = "demonym";
    private static final String AREA = "area";
    private static final String GINI = "gini";
    private static final String NATIVE_NAMES = "native_names";
    private static final String NUMERIC_CODE = "numeric_code";
    private static final String TRANSLATIONS = "translations";
    private static final String FLAG_URL = "flag";
    private static final String CIOC = "cioc";

    //TABLE_CALLING_CODE_DATA
    private static final String CODE = "code";

    //TABLE_TOP_LEVEL_DOMAIN_DATA
    private static final String TOP_LEVEL_DOMAIN = "top_level_domain";

    //TABLE_TIMEZONES_DATA
    private static final String TIMEZONES = "timezones";

    //TABLE_BORDERS_DATA
    private static final String BORDERS = "borders";

    //TABLE_LATLONG_DATA
    private static final String LATLONG = "latlong";

    //TABLE_CURRENCY_DATA
    private static final String CURRENCY_CODE = "currency_code";
    private static final String CURRENCY_NAME = "currency_name";
    private static final String CURRENCY_SYMBOL = "currency_symbol";

    //TABLE_LANGUAGE_DATA
    private static final String LANGUAGE_NAME = "language_name";
    private static final String LANGUAGE_NATIVE_NAME = "language_native_name";

    //TABLE_BLOC_DATA
    private static final String REGIONAL_BLOCS_ACRONYM = "regional_blocs_acronym";
    private static final String REGIONAL_BLOCS_NAME = "regional_blocs_name";

    //TABLE_TRANSLATIONS_DATA
    private static final String TRANSLATION_KEY = "meta_key";
    private static final String TRANSLATION_VALUE = "meta_value";


    /*********************************************/
    //CREATE TABLE QUERIES
    /*********************************************/
    private static final String CREATE_TABLE_NATION_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_NATION_DATA
            + "("
            + ID + " INTEGER PRIMARY KEY, "
            + NAME + " TEXT, "
            + ALPHA_3_CODE + " TEXT, "
            + CAPITAL + " TEXT, "
            + REGION + " TEXT, "
            + SUBREGION + " TEXT, "
            + POPULATION + " INTEGER, "
            + DEMONYM + " TEXT, "
            + AREA + " REAL, "
            + GINI + " REAL, "
            + NATIVE_NAMES + " TEXT, "
            + NUMERIC_CODE + " TEXT, "
            + TRANSLATIONS + " TEXT, "
            + FLAG_URL + " TEXT, "
            + CIOC + " TEXT "
            + ")";

    private static final String CREATE_TABLE_CALLING_CODE_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_CALLING_CODE_DATA
            + "("
            + ID + " INTEGER, "
            + CODE + " TEXT "
            + ")";

    private static final String CREATE_TABLE_TOP_LEVEL_DOMAIN_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_TOP_LEVEL_DOMAIN_DATA
            + "("
            + ID + " INTEGER, "
            + TOP_LEVEL_DOMAIN + " TEXT "
            + ")";

    private static final String CREATE_TABLE_TIMEZONES_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_TIMEZONES_DATA
            + "("
            + ID + " INTEGER, "
            + TIMEZONES + " TEXT "
            + ")";

    private static final String CREATE_TABLE_BORDERS_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_BORDERS_DATA
            + "("
            + ID + " INTEGER, "
            + BORDERS + " TEXT "
            + ")";

    private static final String CREATE_TABLE_LATLONG_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_LATLONG_DATA
            + "("
            + ID + " INTEGER, "
            + LATLONG + " REAL "
            + ")";

    private static final String CREATE_TABLE_CURRENCY_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_CURRENCY_DATA
            + "("
            + ID + " INTEGER, "
            + CURRENCY_CODE + " TEXT, "
            + CURRENCY_NAME + " TEXT, "
            + CURRENCY_SYMBOL + " TEXT "
            + ")";

    private static final String CREATE_TABLE_LANGUAGE_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_LANGUAGE_DATA
            + "("
            + ID + " INTEGER, "
            + LANGUAGE_NAME + " TEXT, "
            + LANGUAGE_NATIVE_NAME + " TEXT "
            + ")";

    private static final String CREATE_TABLE_BLOC_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_BLOC_DATA
            + "("
            + ID + " INTEGER, "
            + REGIONAL_BLOCS_ACRONYM + " TEXT, "
            + REGIONAL_BLOCS_NAME + " TEXT "
            + ")";


    private static final String CREATE_TABLE_TRANSLATIONS_DATA = "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_TRANSLATIONS_DATA
            + "("
            + ID + " INTEGER, "
            + TRANSLATION_KEY + " TEXT, "
            + TRANSLATION_VALUE + " TEXT "
            + ")";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NATION_DATA);
        db.execSQL(CREATE_TABLE_CALLING_CODE_DATA);
        db.execSQL(CREATE_TABLE_TOP_LEVEL_DOMAIN_DATA);
        db.execSQL(CREATE_TABLE_TIMEZONES_DATA);
        db.execSQL(CREATE_TABLE_BORDERS_DATA);
        db.execSQL(CREATE_TABLE_LATLONG_DATA);
        db.execSQL(CREATE_TABLE_CURRENCY_DATA);
        db.execSQL(CREATE_TABLE_LANGUAGE_DATA);
        db.execSQL(CREATE_TABLE_BLOC_DATA);
        db.execSQL(CREATE_TABLE_TRANSLATIONS_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                if (isTableExist(db, TABLE_NATION_DATA)) {
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NATION_DATA);
                }
                break;
        }
        onCreate(db);
    }

    private boolean isTableExist(SQLiteDatabase db, String tableName) {
        String query = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = '" + tableName + "'";
        Cursor cursor = db.rawQuery(query, null);
        boolean isTableExist = false;
        if (cursor != null && cursor.moveToFirst()) {
            isTableExist = true;
        }
        if (cursor != null) {
            cursor.close();
        }
        return isTableExist;
    }

    public long deleteData(String TableName, String whereClause) {
        long rowsModified;
        SQLiteDatabase db = DbSingleton.openDatabase();
        rowsModified = db.delete(TableName, whereClause, null);
        DbSingleton.closeDatabase();

        return rowsModified;
    }

    /*********************************************/
    //CRUD TABLE_NATION_DATA

    /*********************************************/

    public void createNationData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_NATION_DATA + "(" +
                ID + ", " +
                NAME + ", " +
                ALPHA_3_CODE + ", " +
                CAPITAL + ", " +
                REGION + ", " +
                SUBREGION + ", " +
                POPULATION + ", " +
                DEMONYM + ", " +
                AREA + ", " +
                GINI + ", " +
                NATIVE_NAMES + ", " +
                NUMERIC_CODE + ", " +
                TRANSLATIONS + ", " +
                FLAG_URL + ", " +
                CIOC +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            rawQuery += " (" +
                    i + ", " +
                    "\"" + nation.getName() + "\", " +
                    "\"" + nation.getAlpha3Code() + "\", " +
                    "\"" + nation.getCapital() + "\", " +
                    "\"" + nation.getRegion() + "\", " +
                    "\"" + nation.getSubregion() + "\", " +
                    nation.getPopulation() + ", " +
                    "\"" + nation.getDemonym() + "\", " +
                    nation.getArea() + ", " +
                    nation.getGini() + ", " +
                    "\"" + nation.getNativeName() + "\", " +
                    "\"" + nation.getNumericCode() + "\", " +
                    "\"" + new JSONObject(nation.getTranslations()).toString().replaceAll("\"", "\"\"") + "\", " +
                    "\"" + nation.getFlag() + "\", " +
                    "\"" + nation.getCioc() + "\"" +
                    "), ";
        }
        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public ArrayList<NationData> readAllNationData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_NATION_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<NationData> nationDataList = new ArrayList<>();

        while (cursor.moveToNext()) {
            NationData nationData = new NationData();
            nationData.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            nationData.setAlpha3Code(cursor.getString(cursor.getColumnIndex(ALPHA_3_CODE)));
            nationData.setCapital(cursor.getString(cursor.getColumnIndex(CAPITAL)));
            nationData.setRegion(cursor.getString(cursor.getColumnIndex(REGION)));
            nationData.setSubregion(cursor.getString(cursor.getColumnIndex(SUBREGION)));
            nationData.setPopulation(cursor.getInt(cursor.getColumnIndex(POPULATION)));
            nationData.setDemonym(cursor.getString(cursor.getColumnIndex(DEMONYM)));
            nationData.setArea(cursor.getDouble(cursor.getColumnIndex(AREA)));
            nationData.setGini(cursor.getDouble(cursor.getColumnIndex(GINI)));
            nationData.setNativeName(cursor.getString(cursor.getColumnIndex(NATIVE_NAMES)));
            nationData.setNumericCode(cursor.getString(cursor.getColumnIndex(NUMERIC_CODE)));

            String translationData = cursor.getString(cursor.getColumnIndex(TRANSLATIONS));
            Map<String, String> dataHashMap = new HashMap<>();
            try {
                JSONObject json = new JSONObject(translationData);
                Iterator<String> iterator = json.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    dataHashMap.put(key, json.getString(key));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            nationData.setTranslations(dataHashMap);

            nationData.setFlag(cursor.getString(cursor.getColumnIndex(FLAG_URL)));
            nationData.setCioc(cursor.getString(cursor.getColumnIndex(CIOC)));
            nationDataList.add(nationData);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return nationDataList;
    }

    public void createCallingCodeData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_CALLING_CODE_DATA + "(" +
                ID + ", " +
                CODE +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (String callingCode : nation.getCallingCodes()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + callingCode + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<String>> readCallingCodeData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_CALLING_CODE_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);

        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> callingCodeList;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                callingCodeList = hashMap.get(id);
            } else {
                callingCodeList = new ArrayList<>();
            }
            callingCodeList.add(cursor.getString(cursor.getColumnIndex(CODE)));
            hashMap.put(id, callingCodeList);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createTopLevelDomainData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_TOP_LEVEL_DOMAIN_DATA + "(" +
                ID + ", " +
                TOP_LEVEL_DOMAIN +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (String topLevelDomain : nation.getTopLevelDomain()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + topLevelDomain + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<String>> readTopLevelDomainData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_TOP_LEVEL_DOMAIN_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<String> topLevelDomainsList;
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                topLevelDomainsList = hashMap.get(id);
            } else {
                topLevelDomainsList = new ArrayList<>();
            }
            topLevelDomainsList.add(cursor.getString(cursor.getColumnIndex(TOP_LEVEL_DOMAIN)));
            hashMap.put(id, topLevelDomainsList);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createTimezoneData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();
        String rawQuery = "INSERT INTO " + TABLE_TIMEZONES_DATA + "(" +
                ID + ", " +
                TIMEZONES +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (String timeZone : nation.getTimezones()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + timeZone + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<String>> readTimezoneData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_TIMEZONES_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);

        ArrayList<String> timezoneList;
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                timezoneList = hashMap.get(id);
            } else {
                timezoneList = new ArrayList<>();
            }
            timezoneList.add(cursor.getString(cursor.getColumnIndex(TIMEZONES)));
            hashMap.put(id, timezoneList);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createBorderData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();
        String rawQuery = "INSERT INTO " + TABLE_BORDERS_DATA + "(" +
                ID + ", " +
                BORDERS +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (String border : nation.getBorders()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + border + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<String>> readBorderData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_BORDERS_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<String> borderList;
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                borderList = hashMap.get(id);
            } else {
                borderList = new ArrayList<>();
            }
            borderList.add(cursor.getString(cursor.getColumnIndex(BORDERS)));
            hashMap.put(id, borderList);
        }

        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createLatLongData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_LATLONG_DATA + "(" +
                ID + ", " +
                LATLONG +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (Double latLong : nation.getLatlng()) {
                rawQuery += " (" +
                        i + ", " +
                        latLong +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<Double>> readLatLongData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_LATLONG_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<Double> latLongList;
        HashMap<Integer, ArrayList<Double>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                latLongList = hashMap.get(id);
            } else {
                latLongList = new ArrayList<>();
            }
            latLongList.add(cursor.getDouble(cursor.getColumnIndex(LATLONG)));
            hashMap.put(id, latLongList);
        }

        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createCurrencyData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_CURRENCY_DATA + "(" +
                ID + ", " +
                CURRENCY_NAME + ", " +
                CURRENCY_CODE + ", " +
                CURRENCY_SYMBOL +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (NationData.CurrencyData currencyData : nation.getCurrencies()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + currencyData.getName() + "\", " +
                        "\"" + currencyData.getCode() + "\", " +
                        "\"" + currencyData.getSymbol() + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<NationData.CurrencyData>> readCurrencyData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_CURRENCY_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<NationData.CurrencyData> currencyDataList;
        HashMap<Integer, ArrayList<NationData.CurrencyData>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                currencyDataList = hashMap.get(id);
            } else {
                currencyDataList = new ArrayList<>();
            }
            NationData.CurrencyData currencyData = new NationData.CurrencyData();
            currencyData.setCode(cursor.getString(cursor.getColumnIndex(CURRENCY_CODE)));
            currencyData.setName(cursor.getString(cursor.getColumnIndex(CURRENCY_NAME)));
            currencyData.setSymbol(cursor.getString(cursor.getColumnIndex(CURRENCY_SYMBOL)));
            currencyDataList.add(currencyData);
            hashMap.put(id, currencyDataList);
        }

        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createLanguageData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_LANGUAGE_DATA + "(" +
                ID + ", " +
                LANGUAGE_NAME + ", " +
                LANGUAGE_NATIVE_NAME +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (NationData.LanguageData languageData : nation.getLanguages()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + languageData.getName() + "\", " +
                        "\"" + languageData.getNativeName() + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<NationData.LanguageData>> readLanguageData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_LANGUAGE_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<NationData.LanguageData> languageDataList;
        HashMap<Integer, ArrayList<NationData.LanguageData>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                languageDataList = hashMap.get(id);
            } else {
                languageDataList = new ArrayList<>();
            }
            NationData.LanguageData languageData = new NationData.LanguageData();
            languageData.setName(cursor.getString(cursor.getColumnIndex(LANGUAGE_NAME)));
            languageData.setNativeName(cursor.getString(cursor.getColumnIndex(LANGUAGE_NATIVE_NAME)));
            languageDataList.add(languageData);
            hashMap.put(id, languageDataList);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createBlocData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "INSERT INTO " + TABLE_BLOC_DATA + "(" +
                ID + ", " +
                REGIONAL_BLOCS_NAME + ", " +
                REGIONAL_BLOCS_ACRONYM +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (NationData.BlocsData blocsData : nation.getRegionalBlocs()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + blocsData.getName() + "\", " +
                        "\"" + blocsData.getAcronym() + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, ArrayList<NationData.BlocsData>> readBlocData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_BLOC_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        ArrayList<NationData.BlocsData> blockDataList;

        HashMap<Integer, ArrayList<NationData.BlocsData>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                blockDataList = hashMap.get(id);
            } else {
                blockDataList = new ArrayList<>();
            }
            NationData.BlocsData blocsData = new NationData.BlocsData();
            blocsData.setName(cursor.getString(cursor.getColumnIndex(REGIONAL_BLOCS_NAME)));
            blocsData.setAcronym(cursor.getString(cursor.getColumnIndex(REGIONAL_BLOCS_ACRONYM)));
            blockDataList.add(blocsData);
            hashMap.put(id, blockDataList);
        }
        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void createTranslationData(ArrayList<NationData> nationData) {
        SQLiteDatabase db = DbSingleton.openDatabase();
        String rawQuery = "INSERT INTO " + TABLE_TRANSLATIONS_DATA + "(" +
                ID + ", " +
                TRANSLATION_KEY + ", " +
                TRANSLATION_VALUE +
                ") VALUES ";

        for (int i = 0; i < nationData.size(); i++) {
            NationData nation = nationData.get(i);
            for (Map.Entry<String, String> entry :nation.getTranslations().entrySet()) {
                rawQuery += " (" +
                        i + ", " +
                        "\"" + entry.getKey() + "\", " +
                        "\"" + entry.getValue() + "\"" +
                        "), ";
            }
        }

        rawQuery = rawQuery.replaceAll(", $", "");
        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
        }
        cursor.close();
        DbSingleton.closeDatabase();
    }

    public HashMap<Integer, HashMap<String, String>> readTranslationData() {

        SQLiteDatabase db = DbSingleton.openDatabase();

        String rawQuery = "SELECT * FROM" + " " + TABLE_TRANSLATIONS_DATA;
        Cursor cursor = db.rawQuery(rawQuery, null);
        HashMap<String, String> translationList;
        HashMap<Integer, HashMap<String, String>> hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            if (hashMap.containsKey(id)) {
                translationList = hashMap.get(id);
            } else {
                translationList = new HashMap<>();
            }
            translationList.put(cursor.getString(cursor.getColumnIndex(TRANSLATION_KEY)),
                    cursor.getString(cursor.getColumnIndex(TRANSLATION_VALUE)));
            hashMap.put(id, translationList);
        }

        cursor.close();
        DbSingleton.closeDatabase();
        return hashMap;
    }

    public void deleteAllNationData() {
        deleteData(TABLE_NATION_DATA, null);
        deleteData(TABLE_CALLING_CODE_DATA, null);
        deleteData(TABLE_TOP_LEVEL_DOMAIN_DATA, null);
        deleteData(TABLE_TIMEZONES_DATA, null);
        deleteData(TABLE_BORDERS_DATA, null);
        deleteData(TABLE_LATLONG_DATA, null);
        deleteData(TABLE_CURRENCY_DATA, null);
        deleteData(TABLE_LANGUAGE_DATA, null);
        deleteData(TABLE_BLOC_DATA, null);
        deleteData(TABLE_TRANSLATIONS_DATA, null);
    }
}
