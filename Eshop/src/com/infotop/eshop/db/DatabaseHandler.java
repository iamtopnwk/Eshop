package com.infotop.eshop.db;

import java.util.ArrayList;
import java.util.List;

import com.infotop.eshop.model.Product;

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
	private static final String KEY_PRODUCTID = "uuid";
	private static final String KEY_NAME = "productName";
	private static final String KEY_DESCRIPTION = "productDescription";
	private static final String KEY_PRICE = "productPrice";
	private static final String KEY_IMAGE = "image";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WISHLIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_PRODUCTID + " TEXT," + KEY_NAME + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " TEXT," + KEY_IMAGE
				+ " TEXT " + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);

		String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + TABLE_CARTLIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_PRODUCTID + " TEXT," + KEY_NAME + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " TEXT," + KEY_IMAGE
				+ " TEXT " + ")";
		db.execSQL(CREATE_CONTACTS_TABLE1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/** InSerting Record **/

	public void addWishList(Product plist) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCTID, plist.getUuid());
		values.put(KEY_NAME, plist.getProductName());
		values.put(KEY_DESCRIPTION, plist.getProductDescription());
		values.put(KEY_PRICE, plist.getProductPrice());
		values.put(KEY_IMAGE, plist.getMainimage());

		// Inserting Row
		db.insert(TABLE_WISHLIST, null, values);
		db.close(); // Closing database connection
	}

	public void addCartList(Product pList) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCTID, pList.getUuid());
		values.put(KEY_NAME, pList.getProductName());
		values.put(KEY_DESCRIPTION, pList.getProductDescription());
		values.put(KEY_PRICE, pList.getProductPrice());
		values.put(KEY_IMAGE, pList.getMainimage());
		// Inserting Row
		db.insert(TABLE_CARTLIST, null, values);
		db.close(); // Closing database connection
		
	}

	/** Retrieving all Records **/

	public Product[] getAllWishListItems() {
	Product[] wListdata = null;
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_WISHLIST;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	    	int i=0;
	    	wListdata=new Product[cursor.getCount()];
	        do {
	      
			// looping through all rows and adding to list
			Product p=new Product();
			p.setUuid(cursor.getString(1));
			p.setProductName(cursor.getString(2));
			p.setProductDescription(cursor.getString(3));
			p.setProductPrice(cursor.getString(4));
			p.setMainimage(cursor.getString(5));
			wListdata[i]=p;
			i++;
	        } while (cursor.moveToNext());
	    }
		System.out.println(wListdata);
		// return contact list
		return wListdata;
	}

	public Product[] getAllCartListItems() {
		Product[] cartListData = null ;

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CARTLIST;
        SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			int i=0;
			cartListData=new Product[cursor.getCount()];
			do {
				Product p = new Product();
				p.setUuid(cursor.getString(1));
				p.setProductName(cursor.getString(2));
				p.setProductDescription(cursor.getString(3));
				p.setProductPrice(cursor.getString(4));
				p.setMainimage(cursor.getString(5));
				
				cartListData[i]=p;
				i++;
			} while (cursor.moveToNext());
		}

		// return contact list
		return cartListData;
	}

	public void deleteWishListItem(String productId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String deleteQuery = "delete from " + TABLE_WISHLIST + " where "
				+ KEY_PRODUCTID + "='" + productId + "'";

		db.execSQL(deleteQuery);
		db.close();
	}

	public void deleteCartListItem(String productId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String deleteQuery = "delete from " + TABLE_CARTLIST + " where "
				+ KEY_PRODUCTID + "='" + productId + "'";

		db.execSQL(deleteQuery);
		db.close();
	}
}
