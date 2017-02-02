package com.solodroid.androidnewsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.solodroid.androidnewsapp.DatabaseHandler.DatabaseManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;

import java.util.ArrayList;
import java.util.List;

public class News_Favorite extends ActionBarActivity {

	ListView list_fav;
	DatabaseHandler db;
	private DatabaseManager dbManager;
	Adapter_Favorite adapter;
	TextView txt_no;
	JsonUtils util;
	List<Pojo> allData;
	ArrayList<String> allListnews, allListnewsCatName;
	ArrayList<String> allListNewsCId, allListNewsCatId, allListNewsCatImage,
			allListNewsCatName, allListNewsHeading, allListNewsImage,
			allListNewsDes, allListNewsDate;
	String[] allArraynews, allArraynewsCatName;
	String[] allArrayNewsCId, allArrayNewsCatId, allArrayNewsCatImage,
			allArrayNewsCatName, allArrayNewsHeading, allArrayNewsImage,
			allArrayNewsDes, allArrayNewsDate;
	int textlength = 0;
	Pojo pojo;
    Toolbar toolbar;

    private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id));

		setContentView(R.layout.news_favorite);

        StartAppAd.showSlider(this);
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorite");

		list_fav = (ListView) findViewById(R.id.lsv_favorite);
		txt_no = (TextView) findViewById(R.id.textView1);
		db = new DatabaseHandler(getApplicationContext());
		dbManager = DatabaseManager.INSTANCE;
		dbManager.init(getApplicationContext());
		util = new JsonUtils(getApplicationContext());

		allData = db.getAllData();
		adapter = new Adapter_Favorite(News_Favorite.this,
				R.layout.lsv_item_favorite, allData);
		list_fav.setAdapter(adapter);
		if (allData.size() == 0) {
			txt_no.setVisibility(View.VISIBLE);
		} else {
			txt_no.setVisibility(View.INVISIBLE);
		}

		list_fav.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				pojo = allData.get(position);
				int pos = Integer.parseInt(pojo.getCatId());

				Intent intplay = new Intent(News_Favorite.this,
						News_Detail.class);
				intplay.putExtra("POSITION", pos);
				intplay.putExtra("CATEGORY_ITEM_CID", allArrayNewsCId);
				intplay.putExtra("CATEGORY_ITEM_NAME", allArrayNewsCatName);
				// intplay.putExtra("CATEGORY_ITEM_IMAGE",
				// allArrayNewsCatImage);
				intplay.putExtra("CATEGORY_ITEM_CAT_ID", allArrayNewsCatId);
				intplay.putExtra("CATEGORY_ITEM_NEWSIMAGE", allArrayNewsImage);
				intplay.putExtra("CATEGORY_ITEM_NEWSHEADING",
						allArrayNewsHeading);
				intplay.putExtra("CATEGORY_ITEM_NEWSDESCRI", allArrayNewsDes);
				intplay.putExtra("CATEGORY_ITEM_NEWSDATE", allArrayNewsDate);

				startActivity(intplay);
			}
		});

	}

	public void onDestroy() {
		// Log.e("OnDestroy", "called");
		if (!dbManager.isDatabaseClosed())
			dbManager.closeDatabase();
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
		// Log.e("OnPaused", "called");
		if (!dbManager.isDatabaseClosed())
			dbManager.closeDatabase();
	}

	@Override
	public void onResume() {
		super.onResume();
		// Log.e("OnResume", "called");
		// when back key pressed or go one tab to another we update the favorite
		// item so put in resume
		allData = db.getAllData();
		adapter = new Adapter_Favorite(News_Favorite.this,
				R.layout.lsv_item_favorite, allData);
		list_fav.setAdapter(adapter);
		if (allData.size() == 0) {
			txt_no.setVisibility(View.VISIBLE);
		} else {
			txt_no.setVisibility(View.INVISIBLE);
		}

		allListnews = new ArrayList<String>();
		allListnewsCatName = new ArrayList<String>();
		allListNewsCId = new ArrayList<String>();
		allListNewsCatId = new ArrayList<String>();
		// allListNewsCatImage=new ArrayList<String>();
		allListNewsCatName = new ArrayList<String>();
		allListNewsHeading = new ArrayList<String>();
		allListNewsImage = new ArrayList<String>();
		allListNewsDes = new ArrayList<String>();
		allListNewsDate = new ArrayList<String>();

		allArraynews = new String[allListnews.size()];
		allArraynewsCatName = new String[allListnewsCatName.size()];
		allArrayNewsCId = new String[allListNewsCId.size()];
		allArrayNewsCatId = new String[allListNewsCatId.size()];
		// allArrayNewsCatImage=new String[allListNewsCatImage.size()];
		allArrayNewsCatName = new String[allListNewsCatName.size()];
		allArrayNewsHeading = new String[allListNewsHeading.size()];
		allArrayNewsImage = new String[allListNewsImage.size()];
		allArrayNewsDes = new String[allListNewsDes.size()];
		allArrayNewsDate = new String[allListNewsDate.size()];

		for (int j = 0; j < allData.size(); j++) {
			Pojo objAllBean = allData.get(j);

			allListNewsCatId.add(objAllBean.getCatId());
			allArrayNewsCatId = allListNewsCatId.toArray(allArrayNewsCatId);

			allListNewsCId.add(String.valueOf(objAllBean.getCId()));
			allArrayNewsCId = allListNewsCId.toArray(allArrayNewsCId);

			allListNewsCatName.add(objAllBean.getCategoryName());
			allArrayNewsCatName = allListNewsCatName
					.toArray(allArrayNewsCatName);

			allListNewsHeading.add(String.valueOf(objAllBean.getNewsHeading()));
			allArrayNewsHeading = allListNewsHeading
					.toArray(allArrayNewsHeading);

			allListNewsImage.add(String.valueOf(objAllBean.getNewsImage()));
			allArrayNewsImage = allListNewsImage.toArray(allArrayNewsImage);

			allListNewsDes.add(String.valueOf(objAllBean.getNewsDesc()));
			allArrayNewsDes = allListNewsDes.toArray(allArrayNewsDes);

			allListNewsDate.add(String.valueOf(objAllBean.getNewsDate()));
			allArrayNewsDate = allListNewsDate.toArray(allArrayNewsDate);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)
                MenuItemCompat.getActionView(menu.findItem(R.id.search));

        final MenuItem searchMenuItem = menu.findItem(R.id.search);

		searchView
				.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						if (!hasFocus) {
							searchMenuItem.collapseActionView();
							searchView.setQuery("", false);
						}
					}
				});

		searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {

				textlength = newText.length();
				allData.clear();

				for (int i = 0; i < allArrayNewsHeading.length; i++) {
					if (textlength <= allArrayNewsHeading[i].length()) {
						if (newText.toString().equalsIgnoreCase(
								(String) allArrayNewsHeading[i].subSequence(0,
										textlength))) {

							Pojo objItem = new Pojo();

							objItem.setCatId(allArrayNewsCatId[i]);
							objItem.setCId(allArrayNewsCId[i]);
							objItem.setCategoryName(allArrayNewsCatName[i]);
							// objItem.setCategoryImage(allArrayNewsCatImage[i]);
							objItem.setNewsHeading(allArrayNewsHeading[i]);
							objItem.setNewsImage(allArrayNewsImage[i]);
							objItem.setNewsDesc(allArrayNewsDes[i]);
							objItem.setNewsDate(allArrayNewsDate[i]);

							allData.add(objItem);

						}
					}
				}

				adapter = new Adapter_Favorite(News_Favorite.this,
						R.layout.lsv_item_favorite, allData);
				list_fav.setAdapter(adapter);

				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// Do something
				return true;
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.refresh:
                finish();
                startActivity(getIntent());
                overridePendingTransition(R.anim.open_next, R.anim.close_next);

                return true;

            case R.id.menu_favorite:

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
                final String appName = getPackageName();
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
}
