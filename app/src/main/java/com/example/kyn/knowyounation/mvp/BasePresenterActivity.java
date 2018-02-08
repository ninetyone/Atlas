package com.example.kyn.knowyounation.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kyn.knowyounation.NationApp;

public abstract class BasePresenterActivity<P extends BasePresenter<V>, V> extends AppCompatActivity {
    private static final String TAG = "base-activity";
    public static final int LOADER_ID = 201;
    public static final int NATION_DETAIL_ACTIVTY = 202;
    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Loader<P> loader = getSupportLoaderManager().getLoader(loaderId());
        if (loader == null) {
            initLoader();
        } else {
            this.presenter = ((PresenterLoader<P>) loader).getPresenter();
            onPresenterCreatedOrRestored(presenter);
        }
    }

    private void initLoader() {
        // LoaderCallbacks as an object, so no hint regarding Loader will be leak to the subclasses.
        getSupportLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                Log.i(TAG, "onCreateLoader");
                return new PresenterLoader<>(BasePresenterActivity.this, getPresenterFactory());
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                Log.i(TAG, "onLoadFinished");
                BasePresenterActivity.this.presenter = presenter;
                onPresenterCreatedOrRestored(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                Log.i(TAG, "onLoaderReset");
                BasePresenterActivity.this.presenter = null;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        NationApp.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Activity currActivity = NationApp.getInstance().getCurrentActivity();
        if (this.equals(currActivity)) {
            NationApp.getInstance().setCurrentActivity(null);
        }
    }

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses that deliver the {@link BasePresenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterCreatedOrRestored(@NonNull P presenter);

    /**
     * Override in case of fragment not implementing Presenter<View> interface
     */
    @NonNull
    protected V getPresenterView() {
        return (V) this;
    }

    /**
     * Use this method in case you want to specify a spefic ID for the {@link PresenterLoader}.
     * By default its value would be {@link #LOADER_ID}.
     */
    /**
     * Use this method in case you want to specify a spefic ID for the {@link PresenterLoader}.
     */
    protected abstract int loaderId();
}
