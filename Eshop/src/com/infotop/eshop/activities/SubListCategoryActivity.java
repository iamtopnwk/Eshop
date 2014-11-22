package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
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
import com.infotop.eshop.utilities.UserSessionManager;

public class SubListCategoryActivity extends Activity {

	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<String> parentUuids = new ArrayList<String>();
	private ArrayList<String> childItems;
	private ArrayList<String> childUuids;
	private static final String TAG_DOCS = "docs";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_CNAME = "categoryName";
	private static final String TAG_CPID = "categoryParentId";
	private static final String TAG_UUID = "uuid";
	int count = 0;
	HashMap<String, List<String>> childData;
	HashMap<String, List<String>> childData1;
	UserSessionManager usMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);
		String selectedParentId, parentCategoryName, selectedUuid, jsondata;
		String[] categoryName;
		List<String> uuidPosition;
		JSONArray childCategory = null;
		usMgr=new UserSessionManager(this);
		// Create Expandable List and set it's properties
		ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.catexpeId);
		expandableList.setGroupIndicator(null);
		// expandableList.setClickable(true);
		TextView tv = (TextView) findViewById(R.id.selectedTextView);
		selectedParentId = getIntent().getExtras().getString("UUID");
		jsondata = getIntent().getExtras().getString("jsonData");
		parentCategoryName = getIntent().getExtras().getString("CategoryName");
		tv.setText("In " + parentCategoryName);

		try {

			childData = new HashMap<String, List<String>>();
			childData1 = new HashMap<String, List<String>>();
			JSONObject jsonObj;
			jsonObj = new JSONObject(jsondata).getJSONObject(TAG_RESPONSE);
			childCategory = jsonObj.getJSONArray(TAG_DOCS);
			categoryName = new String[childCategory.length()];
			uuidPosition = new ArrayList<String>();
			for (int i = 0; i < childCategory.length(); i++) {
				JSONObject pc = childCategory.getJSONObject(i);
				categoryName[i] = pc.getString(TAG_CNAME);
				/*
				 * if (pc.getString(TAG_UUID).equals(selectedParentId)) {
				 * tv.setText("In" + pc.getString(TAG_CNAME)); }
				 */
				if (pc.getString(TAG_CPID).equals(selectedParentId)) {
					selectedUuid = pc.getString(TAG_UUID);
					uuidPosition.add(selectedUuid);
					parentItems.add(pc.getString(TAG_CNAME));
					parentUuids.add(pc.getString(TAG_UUID));
					childItems = new ArrayList<String>();
					childUuids = new ArrayList<String>();
					for (int j = 0; j < childCategory.length(); j++) {
						JSONObject sc = childCategory.getJSONObject(j);
						if (sc.getString(TAG_CPID).equals(selectedUuid)) {
							childItems.add(sc.getString(TAG_CNAME));
							childUuids.add(sc.getString(TAG_UUID));
						}
					}
					childData.put(parentItems.get(count), childItems);
					childData1.put(parentItems.get(count), childUuids);
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}

		// Create the Adapter
		ExpandableListAdapter adapter = new ExpandableListAdapter(this,
				parentItems, childData);

		// Set the Adapter to expandableList
		expandableList.setAdapter(adapter);
		expandableList.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (childData.get(parentItems.get(groupPosition)).size() == 0) {
					Intent i = new Intent(getApplicationContext(),
							ProductListViewActivity.class);
					i.putExtra("ccId", parentUuids.get(groupPosition));
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
				 * Toast.makeText( getApplicationContext(),
				 * "The position of child category:" +
				 * childData1.get(parentItems.get(groupPosition))
				 * .get(childPosition), Toast.LENGTH_SHORT) .show();
				 */

				Intent i = new Intent(getApplicationContext(),
						ProductListViewActivity.class);
				i.putExtra(
						"ccId",
						childData1.get(parentItems.get(groupPosition)).get(
								childPosition));
				startActivity(i);
				return false;
			}
		});
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
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
			//return true;
		//}
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
		
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.gc();
		super.onDestroy();
	}
}
