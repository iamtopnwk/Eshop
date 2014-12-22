package com.infotop.eshop.product;

import java.util.ArrayList;
import java.util.List;

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
import com.infotop.eshop.activities.EshopMainActivity;
import com.infotop.eshop.cartlist.CartListMainActivity;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.login.ContactUsActivity;
import com.infotop.eshop.login.EshopLoginActivity;
import com.infotop.eshop.login.EshopPoliciesActivity;
import com.infotop.eshop.login.NoItemFoundActivity;
import com.infotop.eshop.utilities.CustomGridViewAdapter;
import com.infotop.eshop.utilities.UserSessionManager;
import com.infotop.eshop.wishlist.WishListMainActivity;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

@SuppressLint("ClickableViewAccessibility")
public class ProductGridViewActivity extends Activity {

	private static final String TAG_RESPONSE = "response";
	private static final String TAG_DOCS = "docs";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE = "productPrice";
	private static final String TAG_PID = "uuid";
	private static final String TAG_IMGURL = "image";
	GridView grid;
	String subCatId;
	DisplayImageOptions op;
	ImageButton ib;
	UserSessionManager usMgr;
	String chilCategoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_grid_view);
		ib = (ImageButton) findViewById(R.id.gridviewbtn1);
		op = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.notavailable)
				.showImageForEmptyUri(R.drawable.notavailable)
				.showImageOnFail(R.drawable.notavailable).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20))
				.build();
			
		grid = (GridView) findViewById(R.id.productGridView);
		subCatId = getIntent().getExtras().getString("ccId");
		chilCategoryName=getIntent().getExtras().getString("childCategoryName");
		String serverURL = new HttpUrl().getUrl()+":8983/solr/collection1/select?q=categoryid%3A*&fq=categoryid%3A"
				+ subCatId + "&rows=100&wt=json&indent=true";

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);
		
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		CustomGridViewAdapter gridAdapter;
		String[] pdct;
		String[] pdctId;
		String[] pdesc;
		String[] price;
		String[] imageUrl;
		private ProgressDialog dialog = new ProgressDialog(
				ProductGridViewActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			JSONArray childCategory = null;
			String pcontent;
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent).getJSONObject(TAG_RESPONSE);
				childCategory = jsonObj.getJSONArray(TAG_DOCS);
				pdct = new String[childCategory.length()];
				pdctId = new String[childCategory.length()];
				pdesc = new String[childCategory.length()];
				price = new String[childCategory.length()];
				imageUrl = new String[childCategory.length()];
				List<String> ccName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					pdct[i] = pc.getString(TAG_PNAME);
					pdctId[i] = pc.getString(TAG_PID);
					pdesc[i] = pc.getString(TAG_PDESC);
					price[i] = pc.getString(TAG_PPRICE);
					ccName.add(pdct[i]);
					imageUrl[i] = pc.getString(TAG_IMGURL);
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
			gridAdapter = new CustomGridViewAdapter(
					ProductGridViewActivity.this, pdctId, pdct, imageUrl,
					pdesc, price, op);
			grid.setAdapter(gridAdapter);
			System.gc();
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
					/*ArrayList<String> productData = new ArrayList<String>();
					productData.add(pdctId[position]);
					productData.add(pdct[position]);
					productData.add(pdesc[position]);
					productData.add(price[position]);
					productData.add((imageUrl[position]).toString());
					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity
                     */
					Intent i = new Intent(ProductGridViewActivity.this,
							BookDetailsActivity.class);
					i.putExtra("productId", pdctId[position]);
					i.putExtra("childCategoryName", chilCategoryName);
					startActivity(i);

				}
			});
			// Close progress dialog
		}

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
			usMgr = new UserSessionManager(this);
			if (!usMgr.isUserLoggedIn()) {

				Intent lgn1 = new Intent(this, NoItemFoundActivity.class);
				startActivity(lgn1);
			} else {
				Intent wl = new Intent(this, CartListMainActivity.class);
				startActivity(wl);
			}
			return true;
		case R.id.abLogin:
			Intent lgn = new Intent(this, EshopLoginActivity.class);
			startActivity(lgn);
			return true;
		case R.id.abwishlist:
			UserSessionManager usMgr = new UserSessionManager(this);
			if (!usMgr.isUserLoggedIn()) {

				Intent lgn1 = new Intent(this, NoItemFoundActivity.class);
				startActivity(lgn1);
			} else {
				Intent wl = new Intent(this, WishListMainActivity.class);
				startActivity(wl);
			}
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
