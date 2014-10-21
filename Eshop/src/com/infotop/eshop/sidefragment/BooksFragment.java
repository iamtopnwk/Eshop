package com.infotop.eshop.sidefragment;

import com.infotop.eshop.R;
import com.infotop.eshop.activities.ProductDetailsActivity;
import com.infotop.eshop.adapters.CustomListAdapter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class BooksFragment extends Fragment {

	ListView list;
	String[] web = { "Oxford Dictionary", "Telugu Dictionary",
			"Oriya Dictionary", "Oracle 10g", "C I O", "FootSteps",
			"Church & Home", "Kill Zumbiles", "Doctor's Guide", "Oil Traders",
			"Mordern Love", "Flex Youth ", "Physics", "Multiply Bussiness",
			"Money Savers", "It's your Time", "Redeployed", "Simple Learning",
			"Still Soul", "BareFoot", "Miracle Morning", "Hooked",
			"Blase Flaming", "Creating Games", "#D Book Mock up",
			"Never Look back", "Ebola", "Transformation", "Game Art Me" };

	Integer[] imageId = { R.drawable.ic_book1, R.drawable.ic_book2,
			R.drawable.ic_book3, R.drawable.ic_book4, R.drawable.ic_book5,
			R.drawable.ic_book6, R.drawable.ic_book7, R.drawable.ic_book8,
			R.drawable.ic_book9, R.drawable.ic_book10, R.drawable.ic_book11,
			R.drawable.ic_book12, R.drawable.ic_book13, R.drawable.ic_book14,
			R.drawable.ic_book15, R.drawable.ic_book16, R.drawable.ic_book17,
			R.drawable.ic_book18, R.drawable.ic_book19, R.drawable.ic_book20,
			R.drawable.ic_book21, R.drawable.ic_book22, R.drawable.ic_book23,
			R.drawable.ic_book24, R.drawable.ic_book25, R.drawable.ic_book26,
			R.drawable.ic_book27, R.drawable.ic_book28, R.drawable.ic_book29 };
	CustomListAdapter adapter;

	public BooksFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.activity_books_list_view, container, false);
		// Custom Adapter object
		adapter = new CustomListAdapter(getActivity(), web, imageId);
		// Adapter Object set to a list
		list = (ListView) rootView.findViewById(R.id.list);
		list.setAdapter(adapter);
		// Click to any item
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String product = (String) adapter.getItem(position);
				// pass Data to other Activity
				Intent i = new Intent(getActivity(),
						ProductDetailsActivity.class);
				i.putExtra("book_item", product);
				startActivity(i);

				// Toast.makeText(BooksListViewActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
			}

		});
		return rootView;
	}

}
