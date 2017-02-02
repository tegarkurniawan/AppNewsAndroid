package com.solodroid.androidnewsapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class About_Us extends ActionBarActivity {

    Toolbar toolbar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
		{
		case android.R.id.home: 
			onBackPressed();
			break;

		default:
			return super.onOptionsItemSelected(menuItem);
		}
		return true;
	}

}
