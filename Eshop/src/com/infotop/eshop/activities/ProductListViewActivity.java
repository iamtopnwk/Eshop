package com.infotop.eshop.activities;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.Utilities.UserSessionManager;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
//import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;



import android.app.SearchManager;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;



public class ProductListViewActivity extends Activity {

	ProductListAdapter listAdapter;
	ListView list;
	String[] pdct;
	Integer[] imgId;
	String[] pdctId;
	String[] pdesc;
	String[] price;
	String[] imageUrl;
	Bitmap[] bmp;
	String subCatId;
	private static final String TAG_AADATA = "aaData";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE="priductPrice";
	private static final String TAG_PID = "id";
	private static final String TAG_IMGURL = "imageUrl";
	static JSONObject jObj = null;
	JSONArray childCategory = null;
	Long totalRecords;
	protected ImageLoader loader = ImageLoader.getInstance();
	DisplayImageOptions op;
	ImageButton ib;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list_view);
		 ib=(ImageButton) findViewById(R.id.listviewbtn1);
		// get the action bar
		  ActionBar actionBar=getActionBar();

		  // Enabling Back navigation on Action Bar icon
		  actionBar.setDisplayHomeAsUpEnabled(true);
		  op = new DisplayImageOptions.Builder()
          .showStubImage(R.drawable.notavailable)
          .showImageForEmptyUri(R.drawable.notavailable)
          .showImageOnFail(R.drawable.notavailable)
          .cacheInMemory()
          .cacheOnDisc()
          .displayer(new RoundedBitmapDisplayer(20))
          .build();

		
		
		list = (ListView) findViewById(R.id.productListView);
		subCatId = getIntent().getExtras().getString("ccId");
		System.out.println("Product Subcategory id:"+ subCatId);
		String serverURL = "http://192.168.8.160:8989/eshop/rest/productbycatid/" + subCatId;

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);

		
        
	}
	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
		private ProgressDialog dialog = new ProgressDialog(
				ProductListViewActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {

			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				childCategory = jsonObj.getJSONArray(TAG_AADATA);
				pdct = new String[childCategory.length()];
				//imgId = new Integer[childCategory.length()];
				pdctId = new String[childCategory.length()];
				pdesc = new String[childCategory.length()];
				price= new String[childCategory.length()];
				imageUrl=new String[childCategory.length()];
				List<String> ccName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					pdct[i] = pc.getString(TAG_PNAME);
					pdctId[i] = pc.getString(TAG_PID);
					pdesc[i] = pc.getString(TAG_PDESC);
					price[i]=pc.getString(TAG_PPRICE);
					ccName.add(pdct[i]);
					imageUrl[i]=pc.getString(TAG_IMGURL);
					System.out.println("Image Url:"+imageUrl[i]);	
				}

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			dialog.dismiss();
			// NOTE: You can call UI Element here.
			listAdapter = new ProductListAdapter(ProductListViewActivity.this,
					pdctId,pdct, imageUrl, pdesc,price,op);
			System.out.println("ListAdapter value is:" + listAdapter);
			list.setAdapter(listAdapter);
			list.setTextFilterEnabled(true);
			list.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent arg1) {
					// TODO Auto-generated method stub
					ib.setVisibility(v.VISIBLE);
					return false;
				}}
					);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ArrayList<String> productData = new ArrayList<String>();
					productData.add(pdctId[position]);
					productData.add(pdct[position]);
					productData.add(pdesc[position]);
					productData.add(price[position]);
					productData.add(imageUrl[position]);
					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity
						Intent i = new Intent(ProductListViewActivity.this,
								BookDetailsActivity.class);
						i.putStringArrayListExtra("productData", productData);
						startActivity(i);
				}
			});
			// Close progress dialog
		}

	}
	/*public void wishlistMethod(View view){
		System.out.println("Wish list button clicked....");
	}*/
	public void gridView(View view) {

		System.out.println("Button is GridView");
		Intent s = new Intent(ProductListViewActivity.this,
				ProductGridViewActivity.class);
		s.putExtra("ccId", subCatId);
		startActivity(s);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_list_view, menu);
		
        return true;
	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		case R.id.abCartList:
			Intent i = new Intent(this, CartListMainActivity.class);
			startActivity(i);
			return true;
		case R.id.abLogin:
			Intent lgn = new Intent(this, EshopLoginActivity.class);
			startActivity(lgn);
			return true;
		case R.id.abwishlist:
			Intent wl = new Intent(this, WishListMainActivity.class);
			startActivity(wl);
			return true;
		case R.id.abTrackOrder:
			return true;
		case R.id.abRateApp:
			return true;
		case R.id.abShareApp:
			return true;
		case R.id.abPolicies:
			Intent policy = new Intent(this, EshopPoliciesActivity.class);
			startActivity(policy);
			return true;
		case R.id.abContactUs:
			Intent cu = new Intent(this, ContactUsActivity.class);
			startActivity(cu);
			return true;
		case R.id.logOut:
			UserSessionManager us = new UserSessionManager(this);
			us.logoutUser();
			Intent lout = new Intent(this, EshopMainActivity.class);
			startActivity(lout);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
