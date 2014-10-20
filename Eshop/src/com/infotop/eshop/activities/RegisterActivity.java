package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.R.id;
import com.infotop.eshop.R.layout;
import com.infotop.eshop.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private EditText userName;
	private EditText userEmail;
	private EditText userPwd;
	private EditText userCPwd;
	private EditText userBAdd;
	private EditText userSAdd;
	private EditText userMobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
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
			Toast.makeText(getApplicationContext(), "Successfully Registered",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, EshopLoginActivity.class);
			startActivity(intent);

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
