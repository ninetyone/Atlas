package com.ninetyone.projects.atlas.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.ninetyone.projects.atlas.R;
import com.ninetyone.projects.atlas.mvp.BasePresenterActivity;
import com.ninetyone.projects.atlas.mvp.BasePresenterFragment;
import com.ninetyone.projects.atlas.mvp.PresenterFactory;
import com.ninetyone.projects.atlas.nation_list.NationListFrag;

public class MainActivity extends BasePresenterActivity<MainContract.Presenter, MainContract.View>
        implements MainContract.View {

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        openFragment();
    }

    private void openFragment() {
        FrameLayout mainFragContainer = (FrameLayout) findViewById(R.id.main_frag_container);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment attachedFrag = supportFragmentManager.findFragmentById(mainFragContainer.getId());
        if (attachedFrag == null) {
            onSwitchFragment(null);
        }
    }

    public void onSwitchFragment(@Nullable BasePresenterFragment fragment) {
        if (fragment == null) {
            fragment = NationListFrag.newInstance();
        }
        if (!(isFinishing())) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_frag_container, fragment);
            transaction.show(fragment);
            Fragment currFrag = fragmentManager.findFragmentById(R.id.main_frag_container);
            if (currFrag != null) {
                transaction.remove(currFrag);
            }
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @NonNull
    @Override
    protected PresenterFactory<MainContract.Presenter> getPresenterFactory() {
        return new MainPresenterFactory();
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int loaderId() {
        return BasePresenterActivity.LOADER_ID;
    }
}
