package com.infotop.eshop.sidefragment;


import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AnalogClock;
import android.widget.TabHost;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {

	private TabHost mTabHost;
	View rootView;
	int mFlipping = 0 ;
	CustomListHorizontalAdapter hAdapter;
	HorizontalListView list;
	
	
	String[] productName = { 
			"Fashion &\n Accessories", 
			"Electronics &\n Appliances", 
			"Books & \n Entertainment ", 
			"Cloths &\n Accessories",
	};

Integer[] productImage = { 
		R.drawable.cloth, 
		R.drawable.mobile,
	    R.drawable.book, 
	    R.drawable.girlcloth,
	 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		
		
		ViewFlipper flipper = (ViewFlipper) rootView.findViewById(R.id.flipper1);
		
		if(mFlipping==0){
            /** Start Flipping */
            flipper.startFlipping();
            mFlipping=1;
           
        } else{
            /** Stop Flipping */
            flipper.stopFlipping();
            mFlipping=0;
           
        }
		
		
		hAdapter = new CustomListHorizontalAdapter(getActivity(),  productName,
				productImage);
		// Adapter Object set to a list
		list = (HorizontalListView)rootView.findViewById(R.id.listhorizontal);
		list.setAdapter(hAdapter);
		// Click to any item
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// String products = (String) hAdapter.getItem(position);
				// pass Data to other Activity
				/*Intent i = new Intent(ClothDetailsActivity.this,
						ClothDetailsActivity.class);
				i.putExtra("book_item", position);
				startActivity(i);*/

				// Toast.makeText(BooksListViewActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
			}
		});
	

		return rootView;
	}
}
