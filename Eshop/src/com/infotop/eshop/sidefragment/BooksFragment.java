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
	
    String[] author={"Author:Pabitra","Author:Balchander","Author: S.K Dutta",
    		"Author: Devid","Author: Dephenar L","Author: Rajesh P","Author: Rakesh Menon",
    		"Author: Snthil Kumar","Author: PrabaKaran","Author: Rajesh","Author: Arun K.",
    		"Author:Tian Xiangly","Author: Mrs Leo","Author: Chian Yang","Author: Sudeep ",
    		"Author: Summy Darien","Author:Petor Burg","Author: L.Kluzner","Author: P.Sepherd",
    		"Author: Sakil","Author: L.M Nahama","Author: S.Babu","Author: N Murthy","Author: S.pati",
    		"Author: Z.Nelson","Author: P.S Panda","Author: J K Mantri","Author: J.M Tripathy",
    		"Author: R.K Burma"};
    
    String[] price={"Price: $102","Price: $110","Price: $40","Price: $65","Price: $73","Price: $86",
			"Price: $89","Price: $320","Price: $326","Price: $560","Price: $143","Price: $150",
			"Price: $120","Price: $430","Price: $210","Price: $540","Price: $15","Price: $45",
			"Price: $42","Price: $75","Price: $65","Price: $143","Price: $320","Price: $70",
			"Price: $156","Price: $436","Price: $210","Price: $301","Price: $430"};
	
	
	
	CustomListAdapter adapter;

	public BooksFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.activity_books_list_view, container, false);
		// Custom Adapter object
		adapter = new CustomListAdapter(getActivity(), web, imageId,author,price);
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

