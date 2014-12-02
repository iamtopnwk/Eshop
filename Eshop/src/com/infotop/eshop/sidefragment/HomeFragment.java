package com.infotop.eshop.sidefragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
import com.infotop.eshop.activities.SubListCategoryActivity;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;
import com.infotop.eshop.httpservice.HttpServiceHandler;

public class HomeFragment extends Fragment {

	View rootView;
	int mFlipping = 0;
	CustomListHorizontalAdapter hAdapter;
	HorizontalListView list;
	private static final String TAG_DOCS = "docs";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_CNAME = "categoryName";
	private static final String TAG_CPID = "categoryParentId";
	private static final String TAG_UUID = "uuid";
	private static final String TAG_DeleteFlag = "deleteFlag";
	JSONArray childCategory = null;
	String[] uuidData;
	String[] categoryName;
	String[] categoryParentId;
	List<String> uuidPosition;
	List<String> parentCategoryName;

	String[] productName = { "Laptops &\n Appliances", "Mobiles", "Women",
			"Books & \n Entertainment", "Men", "Kids", };

	Integer[] productImage = { R.drawable.laptops, R.drawable.mobile,
			R.drawable.girlcloth, R.drawable.book, R.drawable.cloth,
			R.drawable.kids,

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container, false);
		ImageView iv1,iv2,iv3,iv4,iv5;
		iv1=(ImageView) rootView.findViewById(R.id.homeviewflipper1);
		iv2=(ImageView) rootView.findViewById(R.id.homeviewflipper2);
		iv3=(ImageView) rootView.findViewById(R.id.homeviewflipper3);
		iv4=(ImageView) rootView.findViewById(R.id.homeviewflipper4);
		iv5=(ImageView) rootView.findViewById(R.id.homeviewflipper5);
	
		Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.offer);
		Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_laptops);
		Bitmap b3 = BitmapFactory.decodeResource(getResources(), R.drawable.img_cloths);
		Bitmap b4 = BitmapFactory.decodeResource(getResources(), R.drawable.img_books);
		Bitmap b5 = BitmapFactory.decodeResource(getResources(), R.drawable.tanishq);

		iv1.setImageBitmap(b1);
		iv2.setImageBitmap(b2);
		iv3.setImageBitmap(b3);
		iv4.setImageBitmap(b4);
		iv5.setImageBitmap(b5);
		
		String serverURL = "http://192.168.21.212:8983/solr/collection1/select?q=categoryParentId%3A*&rows=1000&wt=json&indent=true";

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);

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

	private class LongOperation extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... urls) {
		    String pcontent;
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				System.out.println("*********Content*******");
				// System.out.println(pcontent);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent).getJSONObject(TAG_RESPONSE);
				childCategory = jsonObj.getJSONArray(TAG_DOCS);
				uuidData = new String[childCategory.length()];
				categoryName = new String[childCategory.length()];
				categoryParentId = new String[childCategory.length()];
				uuidPosition = new ArrayList<String>();
				parentCategoryName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					categoryName[i] = pc.getString(TAG_CNAME);
					if (pc.getString(TAG_CPID).equals("0")) {
						uuidPosition.add(pc.getString(TAG_UUID));
						parentCategoryName.add(pc.getString(TAG_CNAME));
					}
				}

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			// setting the nav drawer list adapter
			hAdapter = new CustomListHorizontalAdapter(getActivity(), productName,
					productImage);
			// Adapter Object set to a list
			list = (HorizontalListView) rootView.findViewById(R.id.listhorizontal);
			list.setAdapter(hAdapter);
			System.gc();
			// Click to any item
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					System.out.println("UUID POsition:"+uuidPosition.get(position));
					Intent i = new Intent(getActivity(),
							SubListCategoryActivity.class);
					i.putExtra("UUID", uuidPosition.get(position));
					i.putExtra("CategoryName", parentCategoryName.get(position));
					//i.putExtra("jsonData", pcontent);
					startActivity(i);
				}
			});
		}
	}
}
