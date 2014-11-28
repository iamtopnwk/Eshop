package com.infotop.eshop.activities;

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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import com.infotop.eshop.R;
import com.infotop.eshop.adapters.ExpandableListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.utilities.UserSessionManager;

public class SubListCategoryActivity extends Activity {

	private static final String TAG_DOCS = "docs";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_CNAME = "categoryName";
	private static final String TAG_UUID = "uuid";
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<String> parentUuids = new ArrayList<String>();
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	HashMap<String, List<String>> listDataChild1;
	ExpandableListView expandableList;
	UserSessionManager usMgr;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);
		String selectedParentId, parentCategoryName;
		
		usMgr = new UserSessionManager(this);
		// expandableList.setClickable(true);
		TextView tv = (TextView) findViewById(R.id.selectedTextView);
		selectedParentId = getIntent().getExtras().getString("UUID");
		// jsondata = getIntent().getExtras().getString("jsonData");
		parentCategoryName = getIntent().getExtras().getString("CategoryName");
		tv.setText("In " + parentCategoryName);
		// Create Expandable List and set it's properties

		String serverURL = "http://192.168.8.160:8983/solr/collection1/select?q=categoryName%3A*&fq=categoryParentId%3A"
				+ selectedParentId + "&wt=json&indent=true";

		new LongOperation().execute(serverURL);
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... urls) {
			String pcontent;
			String ccontent;
			JSONArray parentCategory = null;
			JSONArray childCategory = null;
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent).getJSONObject(TAG_RESPONSE);
				parentCategory = jsonObj.getJSONArray(TAG_DOCS);
				listDataHeader = new ArrayList<String>();
				listDataChild = new HashMap<String, List<String>>();
				listDataChild1 = new HashMap<String, List<String>>();
				for (int i = 0; i < parentCategory.length(); i++) {
					JSONObject pc = parentCategory.getJSONObject(i);
					parentItems.add(pc.getString(TAG_CNAME));
					parentUuids.add(pc.getString(TAG_UUID));
					listDataHeader.add(parentItems.get(i));
					List<String> pcName = new ArrayList<String>();
					List<String> chidIdUUid = new ArrayList<String>();
					ccontent = hs
							.httpContent("http://192.168.8.160:8983/solr/collection1/select?q=categoryName%3A*&fq=categoryParentId%3A"
									+ parentUuids.get(i)
									+ "&wt=json&indent=true");
					JSONObject jsonObj1;
					jsonObj1 = new JSONObject(ccontent)
							.getJSONObject(TAG_RESPONSE);
					childCategory = jsonObj1.getJSONArray(TAG_DOCS);
					for (int j = 0; j < childCategory.length(); j++) {
						JSONObject cc = childCategory.getJSONObject(j);
						pcName.add(cc.getString(TAG_CNAME));
						chidIdUUid.add(cc.getString(TAG_UUID));
					}
					listDataChild.put(listDataHeader.get(i), pcName);
					listDataChild1.put(listDataHeader.get(i), chidIdUUid);
				}

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			expandableList = (ExpandableListView) findViewById(R.id.catexpeId);
			expandableList.setGroupIndicator(null);
			// Create the Adapter
			ExpandableListAdapter adapter = new ExpandableListAdapter(
					SubListCategoryActivity.this, listDataHeader, listDataChild);

			// Set the Adapter to expandableList
			expandableList.setAdapter(adapter);
			System.gc();
			expandableList.setOnGroupClickListener(new OnGroupClickListener() {
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					if (listDataChild.get(parentItems.get(groupPosition))
							.size() == 0) {
						System.out.println("Group Value is:"+parentUuids.get(groupPosition));
						Intent i = new Intent(getApplicationContext(),
								ProductListViewActivity.class);
						i.putExtra("ccId", parentUuids.get(groupPosition));
						i.putExtra("childCategoryName", listDataHeader.get(groupPosition));
						startActivity(i);
					}
					return false;

				}
			});
			expandableList.setOnChildClickListener(new OnChildClickListener() {
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {

					/*
					 *  * Toast.makeText( getApplicationContext(),
					 * "The position of child category:" +
					 * childData1.get(parentItems.get(groupPosition))
					 * .get(childPosition), Toast.LENGTH_SHORT) .show();
					 */

					Intent i = new Intent(getApplicationContext(),
							ProductListViewActivity.class);
					i.putExtra("ccId",
							listDataChild1.get(parentItems.get(groupPosition))
									.get(childPosition));
					i.putExtra("childCategoryName", listDataHeader.get(groupPosition));
					startActivity(i);
					return false;
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_list_category, menu);
		MenuItem logInitem = menu.findItem(R.id.abLogin);
		MenuItem logOutitem = menu.findItem(R.id.logOut);
		//usMgr = new UserSessionManager(this);
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
			if (!usMgr.isUserLoggedIn()) {
				Intent lgn1 = new Intent(this, NoItemFoundActivity.class);
				startActivity(lgn1);
			} else {
				Intent wl = new Intent(this, CartListMainActivity.class);
				startActivity(wl);
			}
			return true;
		case R.id.abLogin:
			if (!usMgr.isUserLoggedIn()) {
				Intent lgn = new Intent(this, EshopLoginActivity.class);
				startActivity(lgn);
			}
			return true;
		case R.id.abwishlist:

			// usMgr = new UserSessionManager(this);
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
