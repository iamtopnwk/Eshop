package com.infotop.eshop.product;


import java.util.concurrent.ExecutionException;



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.activity.CartListMainActivity;
import com.infotop.eshop.login.ContactUsActivity;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.login.EshopPoliciesActivity;
import com.infotop.eshop.login.NoItemFoundActivity;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.urls.UrlInfo;

import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.PostOperation;
import com.infotop.eshop.utilities.UserSessionManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

@SuppressLint("ClickableViewAccessibility")
public class ProductListViewActivity extends Activity {

	
	
	String subCatId;
	DisplayImageOptions op;
	ImageButton ib;
	UserSessionManager usMgr;
	String chilCategoryName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list_view);
		ib = (ImageButton) findViewById(R.id.listviewbtn1);
		// get the action bar
		ActionBar actionBar = getActionBar();
		ProductListAdapter listAdapter;
		ListView list;
		// Enabling Back navigation on Action Bar icon
		actionBar.setDisplayHomeAsUpEnabled(true);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();

		list = (ListView) findViewById(R.id.productListView);
		subCatId = getIntent().getExtras().getString("ccId");
		chilCategoryName = getIntent().getExtras().getString(
				"childCategoryName");
		System.out.println("ChildNameProductList:" + chilCategoryName);
		String serverURL = UrlInfo.GET_ALLPRODUCTS +"/"+ subCatId;
		
		AsyncTask<String, Void, String> data = new GetOperation().execute(serverURL);
		
		try {
			final Product[] pdata= (Product[]) JsonHelper.toObject(data.get(), Product[].class);
			System.out.println("data::::::"+data.get());
			System.out.println("productId---"+pdata[0].getUuid());
            listAdapter=new ProductListAdapter(ProductListViewActivity.this, pdata, op);
		
			
			list.setAdapter(listAdapter);
            list.setTextFilterEnabled(true);
			list.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent arg1) {
					// TODO Auto-generated method stub
					ib.setVisibility(v.VISIBLE);
					return false;
				}
			});
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					// pass Data to other Activity
					Intent i = new Intent(ProductListViewActivity.this,
							ProductDetailsActivity.class);
					// i.putStringArrayListExtra("productData", productData);
					i.putExtra("productId", pdata[position].getUuid());
					i.putExtra("childCategoryName", chilCategoryName);
					startActivity(i);
				}
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		  System.gc();
		}

	

	/*
	 * public void wishlistMethod(View view){
	 * System.out.println("Wish list button clicked...."); }
	 */
	public void gridView(View view) {

		System.out.println("Button is GridView");
		Intent s = new Intent(ProductListViewActivity.this,
				ProductGridViewActivity.class);
		s.putExtra("ccId", subCatId);
		s.putExtra("childCategoryName", chilCategoryName);
		startActivity(s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_list_view, menu);
		MenuItem logInitem = menu.findItem(R.id.abLogin);
		MenuItem logOutitem = menu.findItem(R.id.logOut);
		UserSessionManager usMgr = new UserSessionManager(this);
		if (!usMgr.isUserLoggedIn()) {
			logOutitem.setVisible(false);
		} else {
			logInitem.setTitle(usMgr.getUserDetails().get("name"));
		}
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
			
				Intent cl = new Intent(this, CartListMainActivity.class);
				startActivity(cl);
			
			return true;
		case R.id.abLogin:
			Intent lgn = new Intent(this, EshopLoginActivity.class);
			startActivity(lgn);
			return true;
		/*case R.id.abwishlist:
			
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
