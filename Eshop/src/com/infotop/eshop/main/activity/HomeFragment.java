package com.infotop.eshop.main.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.infotop.eshop.R;
import com.infotop.eshop.category.activity.SubListCategoryActivity;
import com.infotop.eshop.commonadapters.CustomListHorizontalAdapter;
import com.infotop.eshop.model.Category;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.GetOperation;
import com.infotop.eshop.utilities.HorizontalListView;
import com.infotop.eshop.utilities.JsonHelper;

public class HomeFragment extends Fragment {

	View rootView;
	int mFlipping = 0;
	CustomListHorizontalAdapter hAdapter;
	HorizontalListView list;

	String[] productName = { "Electronis", "Home Appliance", "Books", "Sports" };

	Integer[] productImage = { R.drawable.laptops, R.drawable.mobile,
			R.drawable.girlcloth, R.drawable.book, R.drawable.cloth,
			R.drawable.kids,

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container, false);
		ImageView iv1, iv2, iv3, iv4, iv5;
		iv1 = (ImageView) rootView.findViewById(R.id.homeviewflipper1);
		iv2 = (ImageView) rootView.findViewById(R.id.homeviewflipper2);
		iv3 = (ImageView) rootView.findViewById(R.id.homeviewflipper3);
		iv4 = (ImageView) rootView.findViewById(R.id.homeviewflipper4);
		iv5 = (ImageView) rootView.findViewById(R.id.homeviewflipper5);

		Bitmap b1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.offer);
		Bitmap b2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.img_laptops);
		Bitmap b3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.img_cloths);
		Bitmap b4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.img_books);
		Bitmap b5 = BitmapFactory.decodeResource(getResources(),
				R.drawable.tanishq);

		iv1.setImageBitmap(b1);
		iv2.setImageBitmap(b2);
		iv3.setImageBitmap(b3);
		iv4.setImageBitmap(b4);
		iv5.setImageBitmap(b5);

		String serverURL = UrlInfo.HOMEPAGE_PATH;

		// Use AsyncTask execute Method To Prevent ANR Problem
		AsyncTask<String, Void, String> data = new GetOperation()
				.execute(serverURL);
		try {
			final Category[] pdata = (Category[]) JsonHelper.toObject(
					data.get(), Category[].class);
			hAdapter = new CustomListHorizontalAdapter(getActivity(), pdata,
					productImage);
			// Adapter Object set to a list
			list = (HorizontalListView) rootView
					.findViewById(R.id.listhorizontal);
			list.setAdapter(hAdapter);
			// Click to any item
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					System.out.println("UUID POsition:"
							+ pdata[position].getUuid());
					Intent i = new Intent(getActivity(),
							SubListCategoryActivity.class);
					i.putExtra("UUID", pdata[position].getUuid());
					i.putExtra("CategoryName",
							pdata[position].getCategoryName());
					// i.putExtra("jsonData", pcontent);
					startActivity(i);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		ViewFlipper flipper = (ViewFlipper) rootView
				.findViewById(R.id.flipper1);

		if (mFlipping == 0) {
			/** Start Flipping */
			flipper.startFlipping();
			mFlipping = 1;

		} else {
			/** Stop Flipping */
			flipper.stopFlipping();
			mFlipping = 0;

		}
		System.gc();
		return rootView;
	}

}
