package com.infotop.eshop.sidefragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.infotop.eshop.R;
import com.infotop.eshop.activities.BookListMainActivity;
import com.infotop.eshop.adapters.ExpandableListAdapter;

public class BooksFragment extends Fragment {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_books, container,
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
						.get(childPosition).equals("Dictionaries")) {
					System.out.println("This is Dictionary Activity");
					Intent i = new Intent(getActivity(),
							BookListMainActivity.class);
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
		listDataHeader.add("Academic");
		listDataHeader.add("Fiction");
		listDataHeader.add("Horror");

		// Adding child data
		List<String> academic = new ArrayList<String>();
		academic.add("Dictionaries");
		academic.add("Oracle");
		academic.add("Java");
		List<String> fiction = new ArrayList<String>();
		fiction.add("RobinSharma");
		fiction.add("KaranSharma");
		fiction.add("RohitSharma");
		List<String> horror = new ArrayList<String>();
		horror.add("Robert");
		horror.add("David");
		horror.add("Srinivasan");

		listDataChild.put(listDataHeader.get(0), academic); // Header, Child
															// data
		listDataChild.put(listDataHeader.get(1), fiction);
		listDataChild.put(listDataHeader.get(2), horror);
	}
}