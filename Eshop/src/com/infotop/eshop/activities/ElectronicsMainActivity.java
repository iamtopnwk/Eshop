package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomGridViewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ShareActionProvider;
import android.widget.Toast;



public class ElectronicsMainActivity extends  Activity  {
	
	
	
		private ShareActionProvider mShareActionProvider;
		//RatingBar[] ratingBar;
		  GridView grid;
		  String[] web = {
		        "Dell Inspiron 7000 Series",
		        " Dell-Inspiron 15.6",
		        "Lenovo-15.6 ThinkPad",
		        "MacBook Pro",
		        " Dell-Inspiron 15.6",
		        " Apple®-MacBook Pro",
		        "Sony-VAIO 11.6 2-in-1",
		        " Dell-Inspiron 15.6",
		        
		  } ;
		  
		 String[] desc = {
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			        "Model: MGX82LL",
			       
			  } ;
		  Integer[] imageId = {
		      R.drawable.dell,
		      R.drawable.lap1,
		      R.drawable.lap8,
		      R.drawable.mac,
		      R.drawable.lap6,
		      R.drawable.lap7,
		      R.drawable.vaio,
		      R.drawable.samsung,
		      
		     
		  };
		  
			String[] productPrice = { "Price: $102", 
					"Price: $110", 
					"Price: $40",
					"Price: $65", 
					"Price: $73",
					"Price: $73",
					"Price: $73",
					"Price: $73"};
		  

		@Override
		  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_cloths_main);
		 
		    CustomGridViewAdapter adapter = new CustomGridViewAdapter(ElectronicsMainActivity.this, web,imageId,desc,productPrice);
		    grid=(GridView)findViewById(R.id.clothsgridView);
		        grid.setAdapter(adapter);
		        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		                @Override
		                public void onItemClick(AdapterView<?> parent, View view,
		                                        int position, long id) {
		                	Intent intent = new Intent(getApplicationContext(), ElectronicsDetailsActivity.class);
		                	intent.putExtra("index", position);
		                	System.out.println("++++++++++++++intent"+intent);
	                        startActivity(intent);   

		                    Toast.makeText(ElectronicsMainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
		                }
		            });
		  }
		  
		  
		  @Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				//getMenuInflater().inflate(R.menu.main, menu);
				//MenuItem item = menu.findItem(R.id.menu_item_share);
				 //mShareActionProvider = (ShareActionProvider) item.getActionProvider();
				 return true;
				//return super.onCreateOptionsMenu(menu);
			}
		  private void setShareIntent(Intent shareIntent) {
			    if (mShareActionProvider != null) {
			        mShareActionProvider.setShareIntent(shareIntent);
			    }
	}	}
		


