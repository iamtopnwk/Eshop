package com.infotop.eshop.sidefragment;


import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {

	
	View rootView;
	int mFlipping = 0 ;
	CustomListHorizontalAdapter hAdapter;
	HorizontalListView list;
	
	
	String[] productName = { 
			"Laptops &\n Appliances",
			"Books & \n Entertainment ", 
			"Mobiles",
			"Kids",
			"Men", 
			"Women"
		};

Integer[] productImage = { 
		R.drawable.laptops,
	    R.drawable.book, 
	    R.drawable.mobile,
	    R.drawable.kids,   
		R.drawable.cloth, 
		R.drawable.girlcloth
		
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
				
				
				
					Fragment fragment = null;
					switch (position) {
					
					case 0:
						fragment = new ClothsFragment();
						break;
					case 1:
						fragment = new ElectronicsFragment();
						break;
					case 2:
						fragment = new BooksFragment();
						break;
			
					case 3:
						fragment = new ClothsFragment();
						break;

					default:
						break;
					}

				FragmentManager fm =getFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.frame_container, fragment);
				transaction.addToBackStack(null);

				transaction.commit();
				
			}
		});
	

		return rootView;
	}
}
