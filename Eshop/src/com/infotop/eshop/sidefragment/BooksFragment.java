package com.infotop.eshop.sidefragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.infotop.eshop.R;
import com.infotop.eshop.activities.ProductListViewActivity;
import com.infotop.eshop.adapters.CategoryListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;

public class BooksFragment extends Fragment {

	CategoryListAdapter listAdapter;
	ListView list;
	String[] chld;
	Integer[] imgId;
	String[] ccId;
	String[] cdesc;
	private static final String TAG_AADATA = "aaData";
	private static final String TAG_CCNAME = "categoryName";
	private static final String TAG_CCDESC = "categoryDescription";
	private static final String TAG_CCid = "id";
	static JSONObject jObj = null;
	JSONArray childCategory = null;
	Long totalRecords;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_books, container,
				false);
		list = (ListView) rootView.findViewById(R.id.subCatListView);
		// String parentCatId = getIntent().getExtras().getString("pcId");

		String serverURL = "http://192.168.8.160:8989/eshop/rest/ccategory/" + 1;

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);
		return rootView;
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
		private ProgressDialog dialog = new ProgressDialog(getActivity());

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {

			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				childCategory = jsonObj.getJSONArray(TAG_AADATA);
				chld = new String[childCategory.length()];
				imgId = new Integer[childCategory.length()];
				ccId = new String[childCategory.length()];
				cdesc = new String[childCategory.length()];
				List<String> ccName = new ArrayList<String>();
				for (int i = 0; i < childCategory.length(); i++) {
					JSONObject pc = childCategory.getJSONObject(i);
					chld[i] = pc.getString(TAG_CCNAME);
					ccId[i] = pc.getString(TAG_CCid);
					cdesc[i] = pc.getString(TAG_CCDESC);
					ccName.add(chld[i]);
					System.out.println(chld[i]);
					if (chld[i].equals("Horror")) {
						imgId[i] = R.drawable.horror;
						System.out.println(imgId[i]);
					} else if (chld[i].equals("Fiction")) {
						imgId[i] = R.drawable.fiction;
						System.out.println(imgId[i]);
					} else if (chld[i].equals("Romantic")) {
						imgId[i] = R.drawable.romantic;
						System.out.println(imgId[i]);
					} else if (chld[i].equals("Comdedy")) {
						imgId[i] = R.drawable.comdedy;
						System.out.println(imgId[i]);
					} else if (chld[i].equals("Academics")) {
						imgId[i] = R.drawable.academic;
						System.out.println(imgId[i]);
					}

				}

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			dialog.dismiss();
			// NOTE: You can call UI Element here.
			listAdapter = new CategoryListAdapter(getActivity(), chld, imgId,
					cdesc);
			System.out.println("ListAdapter value is:" + listAdapter);
			list.setAdapter(listAdapter);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// String product = (String) adapter.getItem(position);
					// pass Data to other Activity

					if (position == 0) {
						Intent i = new Intent(getActivity(),
								ProductListViewActivity.class);
						i.putExtra("ccId", ccId[position]);
						startActivity(i);
					}
				}
			});
			// Close progress dialog
		}

	}
}