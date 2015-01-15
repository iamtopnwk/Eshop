package com.infotop.eshop.login;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.infotop.eshop.R;
import com.infotop.eshop.model.Account;
import com.infotop.eshop.urls.UrlInfo;

public class RegisterActivity extends Activity {

	private EditText userName, userEmail, userPwd, userCPwd, userBAdd,
			userSAdd, userMobile;
	private String serverURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// serverURL = new HttpUrl().getUrl()+"/eshop/rest/registration";
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
			Account account = new Account();
			account.setEmailId(userEmail.getText().toString());
			account.setPassword(userPwd.getText().toString());
			account.setBillingAddress(userBAdd.getText().toString());
			account.setMobileNumber(userMobile.getText().toString());
			account.setShippingAddress(userSAdd.getText().toString());
			account.setUserName(userName.getText().toString());
			account.setServiceUrl(UrlInfo.REGISTER_PATH);
			AsyncTask<Object, Void, String> respData = new AccountPostOperation()
					.execute(account);
			String pcontent;
			try {
				pcontent = respData.get();
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
			} catch (InterruptedException e) {
				Toast.makeText(getApplicationContext(), "Connection error",
						Toast.LENGTH_SHORT).show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				Toast.makeText(getApplicationContext(), "Connection error",
						Toast.LENGTH_SHORT).show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Toast.makeText(getApplicationContext(),
					"Both paswords should be same", Toast.LENGTH_SHORT).show();
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
