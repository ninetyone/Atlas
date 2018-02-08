package com.ninetyone.projects.atlas.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ninetyone on 24/11/17.
 */

public class DbSingleton {

    private static SqliteHelper sqliteHelper;
    private static AtomicInteger mAtomicInteger = new AtomicInteger();
    private static SQLiteDatabase mDatabase;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private DbSingleton() {
    }

    public static synchronized void initializeInstance(Context context) {
        if (sqliteHelper == null) {
            sqliteHelper = new SqliteHelper(context);
            mContext = context;
        }
    }

    public static synchronized SqliteHelper getInstance() {
        if (sqliteHelper == null) {
            throw new IllegalStateException(DbSingleton.class.getSimpleName() +
                    " is not initialized, call initializeInstance() method first.");
        }
        return sqliteHelper;
    }

    public static synchronized SQLiteDatabase openDatabase() {
        if (mAtomicInteger.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = sqliteHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    static synchronized void closeDatabase() {
        if (mAtomicInteger.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();
        }
    }
}
