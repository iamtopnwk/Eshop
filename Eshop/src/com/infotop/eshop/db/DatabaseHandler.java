package com.infotop.eshop.db;

import java.util.ArrayList;
import java.util.List;

import com.infotop.eshop.model.Wishlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Contacts table name
	private static final String TABLE_WISHLIST = "wishlist";
	private static final String TABLE_CARTLIST = "cartList";
	private static final String KEY_ID = "id";
	private static final String KEY_PRODUCTID = "productId";
	private static final String KEY_NAME = "productName";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_PRICE = "price";
	private static final String KEY_CATID = "categoryId";
	private static final String KEY_CREATEDATE = "createdDate";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WISHLIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_PRODUCTID + " TEXT,"+ KEY_NAME + " TEXT,"
				+ KEY_DESCRIPTION+" TEXT,"+KEY_PRICE+" TEXT," + KEY_CATID + " TEXT," + KEY_CREATEDATE + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		
		String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + TABLE_CARTLIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_PRODUCTID + " TEXT,"+ KEY_NAME + " TEXT,"
				+ KEY_DESCRIPTION+" TEXT,"+KEY_PRICE+" TEXT," + KEY_CATID + " TEXT," + KEY_CREATEDATE + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/** InSerting Record**/
	
	public void addWishList(Wishlist wishlist) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCTID, wishlist.getProductId());
		values.put(KEY_NAME, wishlist.getProductName()); 
		values.put(KEY_DESCRIPTION, wishlist.getDescription());
		values.put(KEY_PRICE, wishlist.getPrice());
		values.put(KEY_CATID, wishlist.getCategoryId());
		values.put(KEY_CREATEDATE, wishlist.getCreatedDate());
		// Inserting Row
		db.insert(TABLE_WISHLIST, null, values);
		db.close(); // Closing database connection
	}
	
	public void addCartList(Wishlist wishlist) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCTID, wishlist.getProductId());
		values.put(KEY_NAME, wishlist.getProductName()); 
		values.put(KEY_DESCRIPTION, wishlist.getDescription());
		values.put(KEY_PRICE, wishlist.getPrice());
		values.put(KEY_CATID, wishlist.getCategoryId());
		values.put(KEY_CREATEDATE, wishlist.getCreatedDate());
		// Inserting Row
		db.insert(TABLE_CARTLIST, null, values);
		db.close(); // Closing database connection
	}
	
	/** Retrieving all Records **/
	
	public List<Wishlist> getAllWishListItems() {
	    List<Wishlist> wishListData = new ArrayList<Wishlist>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_WISHLIST;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	Wishlist wList=new Wishlist();
	        	wList.setId(Integer.parseInt(cursor.getString(0)));
	        	wList.setProductId(cursor.getString(1));
	        	wList.setProductName(cursor.getString(2));
	        	wList.setDescription(cursor.getString(3));
	        	wList.setPrice(cursor.getString(4));
	        	wList.setCategoryId(cursor.getString(5));
	        	wList.setCreatedDate(cursor.getString(6));
	        	wishListData.add(wList);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wishListData;
	}
	
	public List<Wishlist> getAllCartListItems() {
	    List<Wishlist> wishListData = new ArrayList<Wishlist>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_CARTLIST;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	Wishlist wList=new Wishlist();
	        	wList.setId(Integer.parseInt(cursor.getString(0)));
	        	wList.setProductId(cursor.getString(1));
	        	wList.setProductName(cursor.getString(2));
	        	wList.setDescription(cursor.getString(3));
	        	wList.setPrice(cursor.getString(4));
	        	wList.setCategoryId(cursor.getString(5));
	        	wList.setCreatedDate(cursor.getString(6));
	        	wishListData.add(wList);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wishListData;
	}
}
