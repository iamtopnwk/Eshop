package com.infotop.eshop.activities;

import com.infotop.eshop.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WishListLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list_login);
		
		Button logIn =(Button)findViewById(R.id.logIn);
		logIn.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                
                 Intent i = new Intent(WishListLoginActivity.this, EshopLoginActivity.class);
                 startActivity(i);
             }
         });
		
		Button signUp =(Button)findViewById(R.id.signUp);
		signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(WishListLoginActivity.this,RegisterActivity.class);
				startActivity(i);
			
				
			}
		});
		
     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wish_list_login, menu);
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
