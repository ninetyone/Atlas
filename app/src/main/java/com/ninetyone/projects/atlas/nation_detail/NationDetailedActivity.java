package com.ninetyone.projects.atlas.nation_detail;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.ninetyone.projects.atlas.R;
import com.ninetyone.projects.atlas.image_helper.SvgSoftwareLayerSetter;
import com.ninetyone.projects.atlas.models.NationData;
import com.ninetyone.projects.atlas.mvp.BasePresenterActivity;
import com.ninetyone.projects.atlas.mvp.PresenterFactory;

import java.util.ArrayList;

/**
 * Created by Ninetyone on 20/11/17.
 */

public class NationDetailedActivity extends BasePresenterActivity<NationDetailedContract.Presenter, NationDetailedContract.View> implements NationDetailedContract.View {

    public static final String NATION_DATA = "NATION_DATA";
    private NationData nationData;
    private NationDetailedContract.Presenter presenter;

    private TextView capitalTv;
    private TextView countryTv;
    private TextView codeTv;
    private TextView areaTv;
    private TextView domainTv;
    private TextView regionTv;
    private TextView subregionTv;
    private TextView populationTv;
    private TextView callingCodesTv;
    private TextView bordersTv;
    private TextView latlngTv;
    private TextView demonymTv;
    private TextView giniTv;
    private TextView timeZoneTv;
    private TextView nativeNameTv;
    private TextView numericCodeTv;
    private TextView currenciesTv;
    private TextView languageTv;
    private TextView translationTv;
    private AppCompatImageView directionIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation_detail);
        if (getIntent() != null) {
            nationData = (NationData) getIntent().getSerializableExtra(NATION_DATA);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final ImageView countryFlag = (ImageView) findViewById(R.id.flag_photo);
        RequestBuilder<PictureDrawable> requestBuilder = Glide.with(this)
                .as(PictureDrawable.class)
                .listener(new SvgSoftwareLayerSetter());

        requestBuilder.load(Uri.parse(nationData.getFlag())).into(countryFlag);
        collapsingToolbarLayout.setTitle(nationData.getName());
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedAppbar);
        capitalTv = (TextView) findViewById(R.id.capital);
        countryTv = (TextView) findViewById(R.id.country);
        codeTv = (TextView) findViewById(R.id.code);
        areaTv = (TextView) findViewById(R.id.area);
        domainTv = (TextView) findViewById(R.id.domain);
        regionTv = (TextView) findViewById(R.id.region);
        subregionTv = (TextView) findViewById(R.id.subregion);
        populationTv = (TextView) findViewById(R.id.population);
        callingCodesTv = (TextView) findViewById(R.id.calling_codes);
        bordersTv = (TextView) findViewById(R.id.borders);
        latlngTv = (TextView) findViewById(R.id.latLong);
        directionIcon = (AppCompatImageView) findViewById(R.id.direction_icon);
        demonymTv = (TextView) findViewById(R.id.demonym);
        giniTv = (TextView) findViewById(R.id.gini);
        timeZoneTv = (TextView) findViewById(R.id.timeZone);
        nativeNameTv = (TextView) findViewById(R.id.native_name);
        numericCodeTv = (TextView) findViewById(R.id.numeric_code);
        currenciesTv = (TextView) findViewById(R.id.currencies);
        languageTv = (TextView) findViewById(R.id.language);
        translationTv = (TextView) findViewById(R.id.translations);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.checkValidityForCapital(nationData);
        presenter.checkValidityForCountry(nationData);
        presenter.checkValidityForCode(nationData);
        presenter.checkValidityForArea(nationData);
        presenter.checkValidityForDomain(nationData);
        presenter.checkValidityForRegion(nationData);
        presenter.checkValidityForSubregion(nationData);
        presenter.checkValidityForPopulation(nationData);
        presenter.checkValidityForCallingCodes(nationData);
        presenter.checkValidityForBorders(nationData);
        presenter.checkValidityForLatlng(nationData);
        presenter.checkValidityForDemonym(nationData);
        presenter.checkValidityForGini(nationData);
        presenter.checkValidityForTimeZone(nationData);
        presenter.checkValidityForNativeName(nationData);
        presenter.checkValidityForNumericCode(nationData);
        presenter.checkValidityForCurrencies(nationData);
        presenter.checkValidityForLanguage(nationData);
        presenter.checkValidityForTranslation(nationData);
    }

    @NonNull
    @Override
    protected PresenterFactory<NationDetailedContract.Presenter> getPresenterFactory() {
        return new NationDetailedPresenterFactory();
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull NationDetailedContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int loaderId() {
        return BasePresenterActivity.NATION_DETAIL_ACTIVTY;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCountry(String name) {
        countryTv.setText(nationData.getName());
    }

    @Override
    public void showCapital(String capital) {
        capitalTv.setText(nationData.getCapital());
    }

    @Override
    public void showAlpha3Code(String alpha3Code) {
        codeTv.setText(nationData.getAlpha3Code());
    }

    @Override
    public void showRegion(String region) {
        regionTv.setText(nationData.getRegion());
    }

    @Override
    public void showSubregion(String subregion) {
        subregionTv.setText(nationData.getSubregion());
    }

    @Override
    public void showDemonym(String demonym) {
        demonymTv.setText(nationData.getDemonym());
    }

    @Override
    public void showGini(double gini) {
        giniTv.setText(String.valueOf(nationData.getGini()));
    }

    @Override
    public void showNativeName(String nativeName) {
        nativeNameTv.setText(nationData.getNativeName());
    }

    @Override
    public void showNumericCode(String numericCode) {
        numericCodeTv.setText(nationData.getNumericCode());
    }

    @Override
    public void showArea(double area) {
        String areaVal = String.valueOf(nationData.getArea()) + Html.fromHtml("km<sup>2</sup>");
        areaTv.setText(areaVal);
    }

    @Override
    public void showPopulation(long population) {
        populationTv.setText(String.valueOf(nationData.getPopulation()));
    }

    @Override
    public void showLatlng(ArrayList<Double> latlng) {
        latlngTv.setText(nationData.getLatlng().get(0) + ", " + nationData.getLatlng().get(1));
        directionIcon.setVisibility(View.VISIBLE);
        directionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + nationData.getLatlng().get(0) +
                                "," + nationData.getLatlng().get(1) +
                                "?q=" + nationData.getLatlng().get(0) +
                                "," + nationData.getLatlng().get(1) +
                                "(" + nationData.getName() + ")"));
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        } else {
                            Toast.makeText(NationDetailedActivity.this, "Install Google Maps to start navigation", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void showTimeZone(String timeZone) {
        timeZoneTv.setText(timeZone);
    }

    @Override
    public void showCallingCodes(String cc) {
        callingCodesTv.setText(cc);
    }

    @Override
    public void showBorders(String borderData) {
        bordersTv.setText(borderData);
    }

    @Override
    public void showDomain(String domainData) {
        domainTv.setText(domainData);
    }

    @Override
    public void showCurrencies(String currencyData) {
        currenciesTv.setText(currencyData);
    }

    @Override
    public void showLanguage(String languageData) {
        languageTv.setText(languageData);
    }

    @Override
    public void showTranslation(String translationData) {
        translationTv.setText(translationData);
    }
}
