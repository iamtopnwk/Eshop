package com.infotop.eshop.login;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

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
import com.infotop.eshop.main.activity.EshopMainActivity;
import com.infotop.eshop.model.Account;
import com.infotop.eshop.urls.UrlInfo;
import com.infotop.eshop.utilities.UserSessionManager;

public class EshopLoginActivity extends Activity {

	private static final String TAG_EMAILID = "emailId";
	private static final String TAG_USERNAME = "userName";

	private EditText userEmail;
	private EditText password;
	private String serverURL;
	String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eshop_login);
		// serverURL = new HttpUrl().getUrl()+"/eshop/rest/login";
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
			Account account = new Account();
			account.setEmailId(userEmail.getText().toString());
			account.setPassword(password.getText().toString());
			account.setServiceUrl(UrlInfo.LOGIN_PATH);
			AsyncTask<Object, Void, String> respData = new AccountPostOperation()
					.execute(account);
			String pcontent;
			JSONObject jsonObj;
			try {
				pcontent = respData.get();
				jsonObj = new JSONObject(pcontent);
				if (jsonObj != null) {
					UserSessionManager us = new UserSessionManager(
							EshopLoginActivity.this);
					us.createUserLoginSession(jsonObj.getString(TAG_USERNAME),
							jsonObj.getString(TAG_EMAILID));
					/**
					 * Here we can store response object as string name and
					 * email
					 **/
					Intent i = new Intent(EshopLoginActivity.this,
							EshopMainActivity.class);
					startActivity(i);
				}
			} catch (InterruptedException e) {
				Toast.makeText(EshopLoginActivity.this, "Connection Error",
						Toast.LENGTH_SHORT).show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				Toast.makeText(EshopLoginActivity.this, "Connection Error",
						Toast.LENGTH_SHORT).show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				Toast.makeText(EshopLoginActivity.this, "Wrong Credentials",
						Toast.LENGTH_SHORT).show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
