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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.ExpandableListAdapter;

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
	private static final String TAG_DeleteFlag = "deleteFlag";
	String selectedParentId,parentCategoryName;
	String selectedUuid;
	String jsondata;
	JSONArray childCategory = null;
	String[] uuidData;
	String[] categoryName;
	String[] categoryParentId;
	List<String> uuidPosition;
	int count = 0;
	HashMap<String, List<String>> childData;
	HashMap<String, List<String>> childData1;
	ImageView imageview1,imageview2,imageview3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);
		
		// Create Expandable List and set it's properties
		ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.catexpeId);
		expandableList.setGroupIndicator(null);
		// expandableList.setClickable(true);
		TextView tv = (TextView) findViewById(R.id.selectedTextView);
		selectedParentId = getIntent().getExtras().getString("UUID");
		jsondata = getIntent().getExtras().getString("jsonData");
		parentCategoryName = getIntent().getExtras().getString("CategoryName");
		tv.setText("In "+parentCategoryName);
		
		try {
			
			childData = new HashMap<String, List<String>>();
			childData1 = new HashMap<String, List<String>>();
			JSONObject jsonObj;
			jsonObj = new JSONObject(jsondata).getJSONObject(TAG_RESPONSE);
			childCategory = jsonObj.getJSONArray(TAG_DOCS);
			uuidData = new String[childCategory.length()];
			categoryName = new String[childCategory.length()];
			categoryParentId = new String[childCategory.length()];
			uuidPosition = new ArrayList<String>();
			for (int i = 0; i < childCategory.length(); i++) {
				JSONObject pc = childCategory.getJSONObject(i);
				categoryName[i] = pc.getString(TAG_CNAME);
				/*if (pc.getString(TAG_UUID).equals(selectedParentId)) {
					tv.setText("In" + pc.getString(TAG_CNAME));
				}*/
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
					Intent i=new Intent(getApplicationContext(),ProductListViewActivity.class);
					i.putExtra("ccId",  parentUuids.get(groupPosition));
					startActivity(i);
				}
				return false;

			}
		});
		expandableList.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				/*Toast.makeText(
						getApplicationContext(),
						"The position of child category:"
								+ childData1.get(parentItems.get(groupPosition))
										.get(childPosition), Toast.LENGTH_SHORT)
						.show();*/
				
				Intent i=new Intent(getApplicationContext(),ProductListViewActivity.class);
				i.putExtra("ccId",  childData1.get(parentItems.get(groupPosition)).get(childPosition));
				startActivity(i);
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_list_category, menu);
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
