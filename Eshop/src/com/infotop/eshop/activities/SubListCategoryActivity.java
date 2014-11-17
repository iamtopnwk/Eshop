package com.infotop.eshop.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.ExpandableListAdapter;

public class SubListCategoryActivity extends Activity {
	
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<String> childItems = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_list_category);

		// Create Expandable List and set it's properties
		ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.catexpeId);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		// Set the Items of Parent
		parentItems.add("Fruits");
		parentItems.add("Flowers");
		parentItems.add("Animals");
		parentItems.add("Birds");
		// Set The Child Data
		childItems.add("ABC");
		childItems.add("BBC");
		childItems.add("CBC");

		HashMap<String, List<String>> childData = new HashMap<String, List<String>>();
		childData.put(parentItems.get(0), childItems);
		childData.put(parentItems.get(1), childItems);
		childData.put(parentItems.get(2), childItems);
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
