package com.infotop.eshop.activities;

import com.infotop.eshop.R;
import com.infotop.eshop.adapters.CustomListHorizontalAdapter;
import com.infotop.eshop.adapters.HorizontalListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


public class ElectronicsDetailsActivity extends Activity {
	 private ImageView imageView;
	 HorizontalListView list;
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
	      R.drawable.sam,
	      
	     
	  };
	  
		String[] productPrice = { "Price: $102", 
				"Price: $110", 
				"Price: $40",
				"Price: $65", 
				"Price: $73",
				"Price: $73",
				"Price: $73",
				"Price: $73"};
		
		CustomListHorizontalAdapter hAdapter;
	 
	 
	@Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_electro_details);
       
       Intent i =getIntent();
       int pos=i.getExtras().getInt("index");
       ElectronicsMainActivity mn=new  ElectronicsMainActivity();
        imageView = (ImageView) findViewById(R.id.full_image_view);
     
       imageView.setImageResource(mn.imageId[pos]);
       imageView.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	System.out.println("====================================");
           	  ImageView info = (ImageView) findViewById(R.id.info_image);
           	  ImageView share = (ImageView) findViewById(R.id.share_image);
           	  if(info.getVisibility()==v.GONE){
           	  info.setVisibility(v.VISIBLE);
           	  share.setVisibility(v.VISIBLE);
           	  }
           	  else{
           	  
           	        info.setVisibility(v.GONE);
           	        share.setVisibility(v.GONE);
           	  
                     }
           
           }
         });
       TextView textView = (TextView) findViewById(R.id.full_grid_text);
       textView.setText(mn.web[pos]);
       
       TextView textView1 = (TextView) findViewById(R.id.full_grid_text1);
       textView1.setText(mn.desc[pos]);
       
       
       
       
       hAdapter = new CustomListHorizontalAdapter(this, web,
				imageId, productPrice);
		// Adapter Object set to a list
		list = (HorizontalListView) findViewById(R.id.listhorizontal);
		list.setAdapter(hAdapter);
		// Click to any item
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// String products = (String) hAdapter.getItem(position);
				// pass Data to other Activity
				Intent i = new Intent(ElectronicsDetailsActivity.this,
						ElectronicsDetailsActivity.class);
				i.putExtra("index", position);
				startActivity(i);

				// Toast.makeText(BooksListViewActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
			}
		});
	}
       
      }
	
       



