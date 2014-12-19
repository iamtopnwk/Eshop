package com.infotop.eshop.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.httpservice.HttpServiceHandler;
import com.infotop.eshop.httpservice.HttpUrl;

public class RegisterActivity extends Activity {

	private EditText userName, userEmail, userPwd, userCPwd, userBAdd,
			userSAdd, userMobile;
	private String serverURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		serverURL = HttpUrl.getUrl()+":8989/eshop/rest/registration";
	}

	public void getRegisterPage(View view) {
		userName = ((EditText) findViewById(R.id.RuserName));
		userEmail = ((EditText) findViewById(R.id.RuserEmail));
		userPwd = ((EditText) findViewById(R.id.Rpassword));
		userCPwd = ((EditText) findViewById(R.id.Rcpassword));
		userBAdd = ((EditText) findViewById(R.id.Rbaddress));
		userSAdd = ((EditText) findViewById(R.id.Rsaddress));
		userMobile = ((EditText) findViewById(R.id.Rmobile));
		if (userName.getText().toString().trim().equals("")) {
			userName.setError("User name is required!");
		} else if (userEmail.getText().toString().trim().equals("")) {
			userEmail.setError("Email is required!");
		} else if (userPwd.getText().toString().trim().equals("")) {
			userPwd.setError("password is required!");
		} else if (userCPwd.getText().toString().trim().equals("")) {
			userCPwd.setError("Confirm password is required!");
		} else if (userBAdd.getText().toString().trim().equals("")) {
			userBAdd.setError("Billing Address is required!");
		} else if (userSAdd.getText().toString().trim().equals("")) {
			userSAdd.setError("Shipping Address is required!");
		} else if (userMobile.getText().toString().trim().equals("")) {
			userMobile.setError("Mobile Number is required!");
		} else if (userPwd.getText().toString()
				.equals(userCPwd.getText().toString())) {
			new LongOperation().execute(serverURL);
		} else {
			Toast.makeText(getApplicationContext(),
					"Both paswords should be same", Toast.LENGTH_SHORT).show();
		}
	}

	private class LongOperation extends AsyncTask<String, Void, Void> {
		private String pcontent;
		private ProgressDialog dialog = new ProgressDialog(
				RegisterActivity.this);

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			// Start Progress Dialog (Message)
			dialog.show();

		}

		@Override
		protected Void doInBackground(String... urls) {
			String jsonData = "";
			// Send data
			try {
				HttpServiceHandler hs = new HttpServiceHandler();
				JSONObject json = new JSONObject();
				json.accumulate("userName", userName.getText().toString());
				json.accumulate("emailId", userEmail.getText().toString());
				json.accumulate("password", userPwd.getText().toString());
				json.accumulate("shippingAddress", userSAdd.getText()
						.toString());
				json.accumulate("billingAddress", userBAdd.getText().toString());
				json.accumulate("mobileNumber", userMobile.getText().toString());
				jsonData = json.toString();
				pcontent = hs.httpPost(urls[0], jsonData);
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
			Toast.makeText(getApplicationContext(),
					"Your Registration has been done successfully",
					Toast.LENGTH_SHORT).show();
			if (pcontent.equalsIgnoreCase("Success")) {
				Intent intent = new Intent(RegisterActivity.this,
						EshopLoginActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), "Connection error",
						Toast.LENGTH_SHORT).show();
			}
			// Close progress dialog
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
