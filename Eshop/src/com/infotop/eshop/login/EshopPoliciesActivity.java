package com.infotop.eshop.login;

import com.infotop.eshop.R;

import com.infotop.eshop.category.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ExpandableListView;

public class EshopPoliciesActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eshop_policies);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		expListView.setGroupIndicator(null);
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Terms Of Use");
		listDataHeader.add("Privacy Policy");
		listDataHeader.add("Affiliate Policy");
		listDataHeader.add("Infrigment Policy");
		// Adding child data
		List<String> termOfUse = new ArrayList<String>();
		termOfUse.add(getResources().getString(R.string.termofUse));

		List<String> privacyPolicy = new ArrayList<String>();
		privacyPolicy.add(getResources().getString(R.string.privacyPolicy));

		List<String> affiliatePolicy = new ArrayList<String>();
		affiliatePolicy.add(getResources().getString(R.string.afflitedpolicy));

		List<String> infrigmentPolicy = new ArrayList<String>();
		infrigmentPolicy.add(getResources().getString(R.string.infrmentPolicy));

		listDataChild.put(listDataHeader.get(0), termOfUse); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), privacyPolicy);
		listDataChild.put(listDataHeader.get(2), affiliatePolicy);
		listDataChild.put(listDataHeader.get(3), infrigmentPolicy);
	}
}