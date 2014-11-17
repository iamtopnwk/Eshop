package com.infotop.eshop.sidefragment;

import java.util.ArrayList;
import java.util.HashMap;
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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.infotop.eshop.R;
import com.infotop.eshop.activities.ProductListViewActivity;
import com.infotop.eshop.adapters.ExpandableListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;

public class ClothsFragment extends Fragment {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	HashMap<String, List<String>> listDataChild1;
	private static final String TAG_AADATA = "aaData";
	private static final String TAG_PCNAME = "categoryName";
	private static final String TAG_PCTRecrds = "iTotalRecords";
	private static final String TAG_PCid = "id";
	private static final String TAG_CCid = "id";
	private static final String TAG_CCNAME = "categoryName";
	static JSONObject jObj = null;
	JSONArray parentCategory = null;
	JSONArray childCategory = null;
	Long totalRecords;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_cloths, container,
				false);
		expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView1);
		expListView.setGroupIndicator(null);
		// WebServer Request URL
		String serverURL = "http://192.168.8.160:8989/eshop/rest/ccategory/" + 3;

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);

		return rootView;
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
		private String ccontent;
		private ProgressDialog dialog = new ProgressDialog(getActivity());

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			// TODO Auto-generated method stub

			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				pcontent = hs.httpContent(urls[0]);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				parentCategory = jsonObj.getJSONArray(TAG_AADATA);
				String[] prnt = new String[100];
				String pcId;
				listDataHeader = new ArrayList<String>();
				listDataChild = new HashMap<String, List<String>>();
				listDataChild1 = new HashMap<String, List<String>>();
				for (int i = 0; i < parentCategory.length(); i++) {
					JSONObject pc = parentCategory.getJSONObject(i);
					prnt[i] = pc.getString(TAG_PCNAME);
					pcId = pc.getString(TAG_PCid);
					listDataHeader.add(prnt[i]);
					List<String> pcName = new ArrayList<String>();
					List<String> chidIdName = new ArrayList<String>();
					ccontent = hs
							.httpContent("http://192.168.8.160:8989/eshop/rest/subCategorybycatid/"
									+ pcId);
					JSONObject jsonObj1;
					jsonObj1 = new JSONObject(ccontent);
					childCategory = jsonObj1.getJSONArray(TAG_AADATA);
					String[] chld = new String[childCategory.length()];
					String[] chlId = new String[childCategory.length()];
					for (int j = 0; j < childCategory.length(); j++) {
						JSONObject cc = childCategory.getJSONObject(j);
						chld[j] = cc.getString(TAG_CCNAME);
						chlId[j] = cc.getString(TAG_CCid);
						System.out.println(chld[j]);
						pcName.add(chld[j]);
						chidIdName.add(chlId[j]);
					}
					listDataChild.put(listDataHeader.get(i), pcName);
					listDataChild1.put(listDataHeader.get(i), chidIdName);
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
			listAdapter = new ExpandableListAdapter(getActivity(),
					listDataHeader, listDataChild);
			System.out.println("ListAdapter value is:" + listAdapter);
			expListView.setAdapter(listAdapter);
			// Close progress dialog
			expListView.setOnChildClickListener(new OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					// TODO Auto-generated method stub
					/*
					 * Toast.makeText(getActivity(),
					 * "The position of child category:"
					 * +listDataChild1.get(listDataHeader.get(groupPosition))
					 * .get(childPosition), Toast.LENGTH_SHORT).show();
					 */
					Intent i = new Intent(getActivity(),
							ProductListViewActivity.class);
					i.putExtra(
							"ccId",
							listDataChild1.get(
									listDataHeader.get(groupPosition)).get(
									childPosition));
					startActivity(i);

					return false;
				}
			});
		}
	}
}
