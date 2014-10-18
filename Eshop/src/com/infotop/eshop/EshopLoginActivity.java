package com.infotop.eshop;

import com.infotop.eshop.activities.EshopMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EshopLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eshop_login);	
	}

	public void getHomePage(View view) {
		EditText editText = (EditText) findViewById(R.id.editText1);
		EditText editText1 = (EditText) findViewById(R.id.editText2);
		//TextView textView = (TextView) findViewById(R.id.textView4);
		String userName=editText.getText().toString();
		String password=editText1.getText().toString();
		if(userName.equals("iamtop")&&password.equals("1234")){
			Intent intent = new Intent(this, EshopMainActivity.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
				      Toast.LENGTH_SHORT).show();
			//textView.setText("wrong credentials");
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
