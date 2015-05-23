package com.infotop.eshop.category.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.activity.CartListMainActivity;
import com.infotop.eshop.category.adapter.CategoryGridViewAdapter;
import com.infotop.eshop.category.adapter.ExpandableListAdapter;
import com.infotop.eshop.commonadapters.CustomGridViewAdapter;
import com.infotop.eshop.login.ContactUsActivity;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.login.EshopPoliciesActivity;
import com.infotop.eshop.login.NoItemFoundActivity;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.Category;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.product.ProductDetailsActivity;
import com.infotop.eshop.product.ProductGridViewActivity;
import com.infotop.eshop.product.ProductListViewActivity;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.HttpServiceHandler;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.UserSessionManager;


public class SubListCategoryActivity extends Activity {

	
	/*private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<String> parentUuids = new ArrayList<String>();*/
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	HashMap<String, List<String>> listDataChild1;
	ExpandableListView expandableList;
	UserSessionManager usMgr;
	CategoryGridViewAdapter gridAdapter;
	GridView grid;
	int imageId = 
		      R.drawable.plus;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);
		String selectedParentId, parentCategoryName;

		usMgr = new UserSessionManager(this);
		grid = (GridView) findViewById(R.id.categoryGridView);
	//	TextView tv = (TextView) findViewById(R.id.selectedTextView);
		selectedParentId = getIntent().getExtras().getString("UUID");
	
		parentCategoryName = getIntent().getExtras().getString("CategoryName");
		//tv.setText("In " + parentCategoryName);
		

		String serverURL = UrlInfo.SUBCATEGORY_PATH +"/" +selectedParentId;
		AsyncTask<String, Void, String> data = new GetOperation().execute(serverURL);
		System.out.println("product subcategory id::--"+data);
		
		try {
			final Category[] cdata= (Category[]) JsonHelper.toObject(data.get(), Category[].class);
		    System.out.println("CDATA:::-"+cdata);
		    
			gridAdapter = new CategoryGridViewAdapter(SubListCategoryActivity.this,cdata,imageId);
			grid.setAdapter(gridAdapter);
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					// pass Data to other Activity
					Intent i = new Intent(SubListCategoryActivity.this,
							ProductListViewActivity.class);
					
					i.putExtra("productId", cdata[position].getUuid());
					i.putExtra("childCategoryName", cdata[position].getCategoryDescription());
					System.out.println("GETUUID+++++++========"+cdata[position].getUuid());
					//i.putExtra("childCategoryName", chilCategoryName);
					startActivity(i);
				}
			});
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void add(){
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_list_category, menu);
		MenuItem logInitem = menu.findItem(R.id.abLogin);
		MenuItem logOutitem = menu.findItem(R.id.logOut);
		// usMgr = new UserSessionManager(this);
		if (!usMgr.isUserLoggedIn()) {
			logOutitem.setVisible(false);
		} else {
			logInitem.setTitle(usMgr.getUserDetails().get("name"));
		}
		return super.onCreateOptionsMenu(menu);
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
			// usMgr = new UserSessionManager(this);
			
				Intent cl = new Intent(this, CartListMainActivity.class);
				startActivity(cl);
			
			return true;
		case R.id.abLogin:
			if (!usMgr.isUserLoggedIn()) {
				Intent lgn = new Intent(this, EshopLoginActivity.class);
				startActivity(lgn);
			}
			return true;
		/*case R.id.abwishlist:

			// usMgr = new UserSessionManager(this);
			
				Intent wl = new Intent(this, WishListMainActivity.class);
				startActivity(wl);
		
			return true;*/
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
