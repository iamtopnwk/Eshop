package com.infotop.eshop.sidefragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.ExpandableListAdapter;

//Page Fragment
public class PagesFragment extends Fragment {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
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

		View rootView = inflater.inflate(R.layout.fragment_pages, container,
				false);

		expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView2);
		// WebServer Request URL
		String serverURL = "http://192.168.8.160:8989/eshop/rest/pcategory";

		// Use AsyncTask execute Method To Prevent ANR Problem
		new LongOperation().execute(serverURL);

		return rootView;
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private final HttpClient client = new DefaultHttpClient();
		private String pcontent;
		private String ccontent;
		private String error = null;
		private ProgressDialog dialog = new ProgressDialog(getActivity());
		String data = "";

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Please wait..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			// TODO Auto-generated method stub
			HttpContext localContext = new BasicHttpContext();

			// Send data
			try {
				HttpGet httpGet = new HttpGet(urls[0]);
				HttpResponse response = client.execute(httpGet, localContext);
				HttpEntity entity = response.getEntity();
				pcontent = getASCIIContentFromEntity(entity);
				JSONObject jsonObj;
				jsonObj = new JSONObject(pcontent);
				parentCategory = jsonObj.getJSONArray(TAG_AADATA);
				String[] prnt = new String[100];
				String pcId;
				listDataHeader = new ArrayList<String>();
				listDataChild = new HashMap<String, List<String>>();
				for (int i = 0; i < parentCategory.length(); i++) {
					JSONObject pc = parentCategory.getJSONObject(i);
					prnt[i] = pc.getString(TAG_PCNAME);
					pcId = pc.getString(TAG_PCid);
					listDataHeader.add(prnt[i]);
					List<String> pcName = new ArrayList<String>();
					HttpGet httpGet1 = new HttpGet(
							"http://192.168.8.160:8989/eshop/rest/ccategory/"
									+ pcId);
					HttpResponse response1 = client.execute(httpGet1,
							localContext);
					HttpEntity entity1 = response1.getEntity();
					ccontent = getASCIIContentFromEntity(entity1);
					JSONObject jsonObj1;
					jsonObj1 = new JSONObject(ccontent);
					childCategory = jsonObj1.getJSONArray(TAG_AADATA);
					String[] chld = new String[100];
					for (int j = 0; j < childCategory.length(); j++) {
						JSONObject cc = childCategory.getJSONObject(j);
						chld[j] = cc.getString(TAG_CCNAME);
						System.out.println(chld[j]);
						pcName.add(chld[j]);
					}
					listDataChild.put(listDataHeader.get(i), pcName);
				}

			} catch (Exception ex) {
				error = ex.getMessage();
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

		}

		private String getASCIIContentFromEntity(HttpEntity entity)
				throws IllegalStateException, IOException {
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n > 0) {
				byte[] b = new byte[4096];
				n = in.read(b);
				if (n > 0)
					out.append(new String(b, 0, n));
			}
			return out.toString();
		}

	}
}
