package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.R.drawable;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ProductListViewActivity extends Activity {

	ProductListAdapter listAdapter;
	ListView list;
	String[] pdct;
	Integer[] imgId;
	String[] pdctId;
	String[] pdesc;
	String[] price;
	private static final String TAG_AADATA = "aaData";
	private static final String TAG_PNAME = "productName";
	private static final String TAG_PDESC = "productDescription";
	private static final String TAG_PPRICE="priductPrice";
	private static final String TAG_PID = "id";
	static JSONObject jObj = null;
	JSONArray childCategory = null;
	Long totalRecords;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list_view);
		list = (ListView) findViewById(R.id.productListView);
		String subCatId = getIntent().getExtras().getString("ccId");
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
				System.out.println("Product Content:"+pcontent);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				childCategory = jsonObj.getJSONArray(TAG_AADATA);
				pdct = new String[childCategory.length()];
				imgId = new Integer[childCategory.length()];
				pdctId = new String[childCategory.length()];
				pdesc = new String[childCategory.length()];
				price= new String[childCategory.length()];
				List<String> ccName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					pdct[i] = pc.getString(TAG_PNAME);
					pdctId[i] = pc.getString(TAG_PID);
					pdesc[i] = pc.getString(TAG_PDESC);
					price[i]=pc.getString(TAG_PPRICE);
					ccName.add(pdct[i]);
					System.out.println(pdct[i]);	
						imgId[i] = R.drawable.productimg;
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
					pdct, imgId, pdesc,price);
			System.out.println("ListAdapter value is:" + listAdapter);
			list.setAdapter(listAdapter);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity

					if (position == 0) {
					/*	Intent i = new Intent(EdealBooksMActivity.this,
								EdealBooksMActivity.class);
						i.putExtra("pcId", chld[position]);
						startActivity(i);*/
					}
				}
			});
			// Close progress dialog
		}

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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
