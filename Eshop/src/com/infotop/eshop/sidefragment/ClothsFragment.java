package com.infotop.eshop.sidefragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infotop.eshop.ClothsMainActivity;
import com.infotop.eshop.R;
import com.infotop.eshop.activities.ProductDetailsActivity;
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
import android.widget.Toast;

public class ClothsFragment extends Fragment {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_cloths, container,
				false);
		expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView1);
		prepareListData();
		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		// Intent i = new Intent(getActivity(), ClothsMainActivity.class);
		// i.putExtra("book_item", product);
		// startActivity(i);
		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
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
				return false;
			}
		});

		return rootView;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Mens");
		listDataHeader.add("Womens");
		listDataHeader.add("Kids");

		// Adding child data
		List<String> mens = new ArrayList<String>();
		mens.add("T-shirts");
		mens.add("Jeans");
		mens.add("Shirts");
		List<String> womens = new ArrayList<String>();
		womens.add("Sarees");
		womens.add("Kurtha");
		womens.add("Jeans");
		List<String> kids = new ArrayList<String>();
		kids.add("T-shirts");
		kids.add("Jeans");
		kids.add("Shirts");

		listDataChild.put(listDataHeader.get(0), mens); // Header, Child data
		listDataChild.put(listDataHeader.get(1), womens);
		listDataChild.put(listDataHeader.get(2), kids);
	}
}
