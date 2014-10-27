package com.infotop.eshop.sidefragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infotop.eshop.R;
import com.infotop.eshop.activities.BookListMainActivity;
import com.infotop.eshop.activities.ElectronicsMainActivity;
import com.infotop.eshop.adapters.ExpandableListAdapter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ElectronicsFragment extends Fragment {
	
	
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_electro, container,
				false);
		expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView1);
		prepareListData();
		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				/*
				 * Toast.makeText(getActivity(),
				 * listDataHeader.get(groupPosition), Toast.LENGTH_SHORT)
				 * .show();
				 */
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				if (listDataChild.get(listDataHeader.get(groupPosition))
						.get(childPosition).equals("Apple")) {
					System.out.println("This is Electronics Activity");
					Intent i = new Intent(getActivity(),
							ElectronicsMainActivity.class);
					// i.putExtra("book_item", product);
					startActivity(i);
				} else {
					System.out.println("Non Dictionary Item");
				}
				return false;
			}
		});

		return rootView;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Laptops");
		listDataHeader.add("Tablets");
		listDataHeader.add("TVs");

		// Adding child data
		List<String> laptops = new ArrayList<String>();
		laptops.add("Apple");
		laptops.add("Dell");
		laptops.add("Lenevo");
		laptops.add("Sony");
		laptops.add("Accer");
		laptops.add("ThinkPad");
		laptops.add("Samsung");
		
		List<String> tablets = new ArrayList<String>();
		tablets.add("mi");
		tablets.add("Samsung");
		tablets.add("micromax");
		tablets.add("Sony");
		tablets.add("LG");
		
		List<String> tvs = new ArrayList<String>();
		tvs.add("mi");
		tvs.add("Sony");
		tvs.add("LG");
		tvs.add("Videocon");
		tvs.add("Samsung");
		

		listDataChild.put(listDataHeader.get(0), laptops); // Header, Child
															// data
		listDataChild.put(listDataHeader.get(1), tablets);
		listDataChild.put(listDataHeader.get(2), tvs);
	}

    
}
