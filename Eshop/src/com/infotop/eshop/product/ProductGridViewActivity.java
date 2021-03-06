package com.infotop.eshop.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.infotop.eshop.R;
import com.infotop.eshop.cartlist.activity.CartListMainActivity;
import com.infotop.eshop.commonadapters.CustomGridViewAdapter;
import com.infotop.eshop.login.ContactUsActivity;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.login.EshopPoliciesActivity;
import com.infotop.eshop.login.NoItemFoundActivity;
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.Product;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.HttpServiceHandler;
import com.infotop.eshop.utilities.JsonHelper;
import com.infotop.eshop.utilities.UserSessionManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

@SuppressLint("ClickableViewAccessibility")
public class ProductGridViewActivity extends Activity {

	/*private static final String TAG_RESPONSE = "response";
	private static final String TAG_DOCS = "docs";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE = "productPrice";
	private static final String TAG_PID = "uuid";
	private static final String TAG_IMGURL = "image";*/
	
	String subCatId;
	DisplayImageOptions op;
	ImageButton ib;
	UserSessionManager usMgr;
	String chilCategoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_grid_view);
		
		CustomGridViewAdapter gridAdapter;
		GridView grid;
		ib = (ImageButton) findViewById(R.id.gridviewbtn1);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();

		grid = (GridView) findViewById(R.id.productGridView);
		subCatId = getIntent().getExtras().getString("ccId");
		chilCategoryName = getIntent().getExtras().getString(
				"childCategoryName");
		String serverURL = UrlInfo.GET_ALLPRODUCTS +"/"+ subCatId;

        AsyncTask<String, Void, String> data = new GetOperation().execute(serverURL);
		
		try {
			final Product[] pdata= (Product[]) JsonHelper.toObject(data.get(), Product[].class);
			gridAdapter = new CustomGridViewAdapter(
					ProductGridViewActivity.this,pdata, op);
			grid.setAdapter(gridAdapter);
			
			grid.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent arg1) {
					// TODO Auto-generated method stub
					ib.setVisibility(v.VISIBLE);
					return false;
				}
			});
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i = new Intent(ProductGridViewActivity.this,
							ProductDetailsActivity.class);
					i.putExtra("productId", pdata[position].getUuid());
					i.putExtra("childCategoryName", chilCategoryName);
					startActivity(i);

				}
			});
			// Close progress dialog
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// Use AsyncTask execute Method To Prevent ANR Problem
		
			
			System.gc();
			
		}

	

	public void listViewdata(View view) {

		System.out.println("Button is ListView");
		Intent s = new Intent(ProductGridViewActivity.this,
				ProductListViewActivity.class);
		s.putExtra("ccId", subCatId);
		s.putExtra("childCategoryName", chilCategoryName);
		startActivity(s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_grid_view, menu);
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
