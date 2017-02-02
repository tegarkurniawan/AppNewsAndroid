package com.solodroid.androidnewsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class News_Recent extends Fragment {

    ListView lsv_latest;
    List<ItemLatest> arrayOfLatestnews;
    Adapter_Recent objAdapter;
    AlertDialogManager alert = new AlertDialogManager();
    ArrayList<String> allListnews, allListnewsCatName;
    ArrayList<String> allListNewsCId, allListNewsCatId, allListNewsCatImage,
            allListNewsCatName, allListNewsHeading, allListNewsImage,
            allListNewsDes, allListNewsDate;
    String[] allArraynews, allArraynewsCatName;
    String[] allArrayNewsCId, allArrayNewsCatId, allArrayNewsCatImage,
            allArrayNewsCatName, allArrayNewsHeading, allArrayNewsImage,
            allArrayNewsDes, allArrayNewsDate;
    private ItemLatest objAllBean;
    private int columnWidth;
    JsonUtils util;
    int textlength = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.news_recent, container,false);

        setHasOptionsMenu(true);

        lsv_latest = (ListView) v.findViewById(R.id.lsv_latest);

        arrayOfLatestnews = new ArrayList<ItemLatest>();
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

        util = new JsonUtils(getActivity());

        if (JsonUtils.isNetworkAvailable(getActivity())) {
            new MyTask().execute(Constant.LATEST_URL);
        } else {
            showToast("No Network Connection!!!");
            alert.showAlertDialog(getActivity(), "Internet Connection Error",
                    "Please connect to working Internet connection", false);
        }

        lsv_latest.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                objAllBean = arrayOfLatestnews.get(position);
                int pos = Integer.parseInt(objAllBean.getCatId());
                Intent intplay = new Intent(getActivity(),
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

        return v;
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == result || result.length() == 0) {
                showToast("Server Connection Error");
                // alert.showAlertDialog(getActivity(),
                // "Server Connection Error",
                // "May Server Under Maintaines Or Low Network", false);

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson
                            .getJSONArray(Constant.CATEGORY_ARRAY_NAME);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemLatest objItem = new ItemLatest();

                        objItem.setCId(objJson
                                .getString(Constant.CATEGORY_ITEM_CID));
                        objItem.setCategoryName(objJson
                                .getString(Constant.CATEGORY_ITEM_NAME));
                        // objItem.setCategoryImage(objJson.getString(Constant.CATEGORY_ITEM_IMAGE));
                        objItem.setCatId(objJson
                                .getString(Constant.CATEGORY_ITEM_CAT_ID));
                        objItem.setNewsImage(objJson
                                .getString(Constant.CATEGORY_ITEM_NEWSIMAGE));
                        objItem.setNewsHeading(objJson
                                .getString(Constant.CATEGORY_ITEM_NEWSHEADING));
                        objItem.setNewsDescription(objJson
                                .getString(Constant.CATEGORY_ITEM_NEWSDESCRI));
                        objItem.setNewsDate(objJson
                                .getString(Constant.CATEGORY_ITEM_NEWSDATE));

                        arrayOfLatestnews.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < arrayOfLatestnews.size(); j++) {

                    objAllBean = arrayOfLatestnews.get(j);

                    allListNewsCatId.add(objAllBean.getCatId());
                    allArrayNewsCatId = allListNewsCatId
                            .toArray(allArrayNewsCatId);

                    allListNewsCatName.add(objAllBean.getCategoryName());
                    allArrayNewsCatName = allListNewsCatName
                            .toArray(allArrayNewsCatName);

                    allListNewsCId.add(String.valueOf(objAllBean.getCId()));
                    allArrayNewsCId = allListNewsCId.toArray(allArrayNewsCId);

                    allListNewsImage.add(String.valueOf(objAllBean
                            .getNewsImage()));
                    allArrayNewsImage = allListNewsImage
                            .toArray(allArrayNewsImage);

                    allListNewsHeading.add(String.valueOf(objAllBean
                            .getNewsHeading()));
                    allArrayNewsHeading = allListNewsHeading
                            .toArray(allArrayNewsHeading);

                    allListNewsDes.add(String.valueOf(objAllBean
                            .getNewsDescription()));
                    allArrayNewsDes = allListNewsDes.toArray(allArrayNewsDes);

                    allListNewsDate
                            .add(String.valueOf(objAllBean.getNewsDate()));
                    allArrayNewsDate = allListNewsDate
                            .toArray(allArrayNewsDate);

                }

                setAdapterToListview();
            }

        }
    }

    public void setAdapterToListview() {
        objAdapter = new Adapter_Recent(getActivity(), R.layout.lsv_item_recent,
                arrayOfLatestnews, columnWidth);
        lsv_latest.setAdapter(objAdapter);
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

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
                arrayOfLatestnews.clear();

                for (int i = 0; i < allArrayNewsHeading.length; i++) {
                    if (textlength <= allArrayNewsHeading[i].length()) {
                        if (newText.toString().equalsIgnoreCase(
                                (String) allArrayNewsHeading[i].subSequence(0,
                                        textlength))) {

                            ItemLatest objItem = new ItemLatest();

                            // objItem.setCategoryImage((allArrayNewsCatImage[i]));
                            objItem.setCategoryName((allArrayNewsCatName[i]));
                            objItem.setCatId(allArrayNewsCatId[i]);
                            objItem.setCId(allArrayNewsCId[i]);
                            objItem.setNewsDate(allArrayNewsDate[i]);
                            objItem.setNewsDescription(allArrayNewsDes[i]);
                            objItem.setNewsHeading(allArrayNewsHeading[i]);
                            objItem.setNewsImage(allArrayNewsImage[i]);
                            arrayOfLatestnews.add(objItem);

                        }
                    }
                }

                setAdapterToListview();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do something
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
