package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomGridViewAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ToggleButton;


public class ProductGridViewActivity extends Activity {
	CustomGridViewAdapter gridAdapter;
	GridView grid;
	String[] pdct;
	Integer[] imgId;
	String[] pdctId;
	String[] pdesc;
	String[] price;
	String[] imageUrl;
	String subCatId;
	private static final String TAG_AADATA = "aaData";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE = "priductPrice";
	private static final String TAG_PID = "id";
	private static final String TAG_IMGURL = "imageUrl";
	static JSONObject jObj = null;
	JSONArray childCategory = null;
	Long totalRecords;
	protected ImageLoader loader = ImageLoader.getInstance();
	DisplayImageOptions op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_grid_view);
		 op = new DisplayImageOptions.Builder()
         .showStubImage(R.drawable.notavailable)
         .showImageForEmptyUri(R.drawable.notavailable)
         .showImageOnFail(R.drawable.notavailable)
         .cacheInMemory()
         .cacheOnDisc()
         .displayer(new RoundedBitmapDisplayer(20))
         .build();
		grid = (GridView) findViewById(R.id.productGridView);
		subCatId = getIntent().getExtras().getString("ccId");
		System.out.println("Product Subcategory id:" + subCatId);
		String serverURL = "http://192.168.8.160:8989/eshop/rest/productbycatid/"
				+ subCatId;

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
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

			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				System.out.println("Product Content:" + pcontent);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				childCategory = jsonObj.getJSONArray(TAG_AADATA);
				pdct = new String[childCategory.length()];
				imgId = new Integer[childCategory.length()];
				pdctId = new String[childCategory.length()];
				pdesc = new String[childCategory.length()];
				price = new String[childCategory.length()];
				imageUrl=new String[childCategory.length()];
				List<String> ccName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					pdct[i] = pc.getString(TAG_PNAME);
					pdctId[i] = pc.getString(TAG_PID);
					pdesc[i] = pc.getString(TAG_PDESC);
					price[i] = pc.getString(TAG_PPRICE);
					ccName.add(pdct[i]);
					imageUrl[i]=pc.getString(TAG_IMGURL);
					System.out.println(pdct[i]);
					//imgId[i] = R.drawable.productimg;
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
					ProductGridViewActivity.this, pdct, imageUrl, pdesc, price,op);
			System.out.println("ListAdapter value is:" + gridAdapter);
			grid.setAdapter(gridAdapter);
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ArrayList<String> productData = new ArrayList<String>();				
					productData.add(pdctId[position]);
					productData.add(pdct[position]);
					productData.add(pdesc[position]);
					productData.add(price[position]);
					productData.add((imageUrl[position]).toString());
					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity

					Intent i = new Intent(ProductGridViewActivity.this,
							BookDetailsActivity.class);
					i.putStringArrayListExtra("productData", productData);
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
		startActivity(s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_grid_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
