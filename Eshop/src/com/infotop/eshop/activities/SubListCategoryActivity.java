package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.ExpandableListAdapter;
import com.infotop.eshop.model.NavDrawerItem;

public class SubListCategoryActivity extends Activity {
	
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<String> childItems = new ArrayList<String>();
	private static final String TAG_DOCS = "docs";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_CNAME = "categoryName";
	private static final String TAG_CPID = "categoryParentId";
	private static final String TAG_UUID = "uuid";
	private static final String TAG_DeleteFlag = "deleteFlag";
	String selectedParentId;
	String jsondata;
	JSONArray childCategory = null;
	String[] uuidData;
	String[] categoryName;
	String[] categoryParentId;
	List<String> uuidPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);
		
		// Create Expandable List and set it's properties
		ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.catexpeId);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);
		TextView tv=(TextView) findViewById(R.id.selectedTextView);
		try {
		selectedParentId=getIntent().getExtras().getString("UUID");
		jsondata=getIntent().getExtras().getString("jsonData");
		System.out.println("Postion of uuid"+selectedParentId);
		System.out.println(jsondata);
		
		JSONObject jsonObj;
		jsonObj = new JSONObject(jsondata).getJSONObject(TAG_RESPONSE);
		childCategory = jsonObj.getJSONArray(TAG_DOCS);
		uuidData = new String[childCategory.length()];
		categoryName = new String[childCategory.length()];
		categoryParentId = new String[childCategory.length()];
		uuidPosition=new ArrayList<String>();
		for (int i = 0; i < childCategory.length(); i++) {
			JSONObject pc = childCategory.getJSONObject(i);
			categoryName[i] = pc.getString(TAG_CNAME);
			if(pc.getString(TAG_UUID).equals(selectedParentId)){
				tv.setText("In"+pc.getString(TAG_CNAME));
			}
			if (pc.getString(TAG_CPID).equals(selectedParentId)) {
				uuidPosition.add(pc.getString(TAG_UUID));
				parentItems.add(pc.getString(TAG_CNAME));
			}
		}
		}
		catch(Exception e){
			System.out.println("Exception:"+e.getMessage());
		}
		// Set The Child Data
		childItems.add("ABC");
		childItems.add("BBC");
		childItems.add("CBC");

		HashMap<String, List<String>> childData = new HashMap<String, List<String>>();
		childData.put(parentItems.get(0), childItems);

		// Create the Adapter
		ExpandableListAdapter adapter = new ExpandableListAdapter(this,
				parentItems, childData);

		// Set the Adapter to expandableList
		expandableList.setAdapter(adapter);
		// expandableList.setOnChildClickListener(this);
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
