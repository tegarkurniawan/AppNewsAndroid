package com.solodroid.androidnewsapp;

import java.io.Serializable;

public class Constant implements Serializable{
 
	private static final long serialVersionUID = 1L;

	//this is the path of uploaded image of category
	public static final String SERVER_IMAGE_CATEGORY="http://10.0.2.2/news/upload/category/";

	//this is the path of uploaded image of newslist thumb
	public static final String SERVER_IMAGE_NEWSLIST_THUMBS="http://10.0.2.2/news/upload/thumbs/";

	//this is the path of uploaded image of news list
	public static final String SERVER_IMAGE_NEWSLISTDETAILS="http://10.0.2.2/news/upload/";

	//this url is used to get recent 20 news in 1st tab.
	public static final String LATEST_URL = "http://10.0.2.2/news/api.php?latest_news=50";

	//this url gives list of category in 2nd tab
	public static final String CATEGORY_URL = "http://10.0.2.2/news/api.php";

	//this url gives item of specific category.
	public static final String CATEGORY_ITEM_URL = "http://10.0.2.2/news/api.php?cat_id=";

	//this url gives your company details
	public static final String COMPANY_DETAILS_URL = "http://10.0.2.2/news/api.php?apps_details";

  
	public static final String CATEGORY_ARRAY_NAME="NewsApp";
	public static final String CATEGORY_NAME="category_name";
	public static final String CATEGORY_CID="cid";
	public static final String CATEGORY_IMAGE="category_image";

	public static final String CATEGORY_ITEM_CID="cid";
	public static final String CATEGORY_ITEM_NAME="category_name";
	public static final String CATEGORY_ITEM_IMAGE="category_image";
	public static final String CATEGORY_ITEM_STATUS="status";
 	public static final String CATEGORY_ITEM_CAT_ID="nid";
	public static final String CATEGORY_ITEM_NEWSHEADING="news_heading";
	public static final String CATEGORY_ITEM_NEWSDESCRI="news_description";
	public static final String CATEGORY_ITEM_NEWSIMAGE="news_image";
	public static final String CATEGORY_ITEM_NEWSDATE="news_date";
	public static final String CATEGORY_ITEM_NEWSSTATUS="news_status";
	
	public static final String COMPANY_DETAILS_ID="id";
	public static final String COMPANY_DETAILS_APPNAME="app_name";
	public static final String COMPANY_DETAILS_COMLOGO="app_logo";
	public static final String COMPANY_DETAILS_COMMAIL="app_email";
	public static final String COMPANY_DETAILS_COMSITE="app_website";
	public static final String COMPANY_DETAILS_COMDES="app_description";

	//for title display in CategoryItemF
	public static String CATEGORY_TITLE;
	public static int CATEGORY_IDD;

 
}
