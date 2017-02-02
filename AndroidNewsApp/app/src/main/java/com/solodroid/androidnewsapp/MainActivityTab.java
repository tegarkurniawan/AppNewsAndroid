package com.solodroid.androidnewsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.startapp.android.publish.StartAppAd;

public class MainActivityTab extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Recent News","Category"};
    int Numboftabs =2;

    private AdView mAdView;
    private StartAppAd startAppAd = new StartAppAd(this);
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id));
        setContentView(R.layout.activity_main_tab);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //Parse push notification
        Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_key));
        ParseAnalytics.trackAppOpened(getIntent());
        PushService.setDefaultPushCallback(this, MainActivityTab.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        StartAppAd.showSlider(this);
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivityTab.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        interstitial.loadAd(adRequest);
        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainActivityTab.this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.refresh:
                finish();
                startActivity(getIntent());
                overridePendingTransition(R.anim.open_next, R.anim.close_next);
                return true;

            case R.id.menu_favorite:
                startActivity(new Intent(getApplicationContext(), News_Favorite.class));
                return true;

            case R.id.menu_about:
                Intent about = new Intent(getApplicationContext(), About_Us.class);
                startActivity(about);
                return true;


            case R.id.menu_moreapp:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.play_more_apps))));
                return true;

            case R.id.menu_rateapp:
                final String appName = getApplicationContext().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="
                                    + appName)));
                }
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
        startAppAd.onResume();
    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


}