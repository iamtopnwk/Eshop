package com.infotop.eshop.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;
import com.infotop.eshop.adapters.ProductListAdapter;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;
import com.infotop.eshop.utilities.UserSessionManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EshopLoginActivity extends Activity {

	private EditText userEmail;
	private EditText password;
	private String serverURL;
	String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eshop_login);
		serverURL = new HttpUrl().getUrl()+":8989/eshop/rest/login";
	}

	public void getHomePage(View view) {
		userEmail = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		if (userEmail.getText().toString().trim().equals("")) {
			userEmail.setError("Email is required!");
		} else if (password.getText().toString().trim().equals("")) {
			password.setError("Password is required!");
		} else {
			// Intent intent = new Intent(this, EshopMainActivity.class);
			// startActivity(intent);
			new LongOperation().execute(serverURL);
		}

	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
		private ProgressDialog dialog = new ProgressDialog(
				EshopLoginActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)

			dialog.setMessage("Logging..");
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			String jsonData = "";
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				JSONObject json = new JSONObject();
				json.accumulate("emailId", userEmail.getText().toString());
				json.accumulate("password", password.getText().toString());
				jsonData = json.toString();
				pcontent = hs.httpPost(urls[0],jsonData);
				System.out.println("Executed data:" + pcontent);
				dialog.dismiss();
				// textView.setText("wrong credentials");

			} catch (Exception ex) {
				System.out.println("Exception e:" + ex.getMessage());
			}
			/*****************************************************/
			return null;
		}

		protected void onPostExecute(Void unused) {
			if(pcontent.equalsIgnoreCase("Success")){
				UserSessionManager us=new UserSessionManager(EshopLoginActivity.this);
				us.createUserLoginSession("test", userEmail.getText().toString());/** Here we can store response object as string name and email **/
				Intent i=new Intent(EshopLoginActivity.this,EshopMainActivity.class);
				startActivity(i);
			}
			else if(pcontent.equalsIgnoreCase("Fail")){
				Toast.makeText(EshopLoginActivity.this, "Wrong Credentials",
						Toast.LENGTH_SHORT).show();
			}
			dialog.dismiss();

			// Close progress dialog
		}

	}

	public void signUp(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eshop_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
