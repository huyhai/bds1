package com.vreal.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import com.vreal.libs.HotdealUtilities;
import com.vreal.model.DetailsModel;
import com.vreal.model.UsersModel;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "hotdeal";

	// Contacts table name
	private static final String TABLE_CACHEDATA = "cache_data";
	private static final String TABLE_SEARCH_HISTORY = "search_history";
	private static final String TABLE_USERS = "users";
	private static final String TABLE_CART = "carts";
	private static final String TABLE_THANHTOANTAM = "thanhtoantam";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME_API = "api";
	private static final String KEY_VALUE = "value";
	private static final String KEY_VALUE_HISTORY_SEARCH = "search_value";

	// user
	private static final String USER_ID = "userid";
	private static final String USER_LOGIN = "userlogin";
	private static final String USERNAME = "username";
	private static final String USER_EMAIL = "email";
	private static final String USER_PHONE = "phone";
	private static final String USER_DE_SAT = "de_sat";
	private static final String USER_BIRTHDAY = "birthday";
	private static final String USER_SEX = "sex";
	// cart
	private static final String PRODUCT_ID = "producid";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String QUANTITY = "quantity";
	private static final String IMAGE = "image";
	private static final String DINHNGAY = "dinhngay";
	private static final String NGAYNHAN = "ngaynhan";
	private static final String NGAYTRA = "ngaytra";
	private static final String PRODUCT_TYPE = "protype";
	private static final String MAXQUANTITY = "maxquan";

	// thanh toan tam
	private static final String CHECK_IN = "checkIn";
	private static final String CHECK_OUT = "checkOut";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String YESGO_ADULTS = "yesgoAdults";
	private static final String YESGO_CHILDS = "yesgoChilds";

	// Contacts Table Columns names
	// private static final String KEY_ID = "id";
	// private static final String KEY_NAME_API = "api";
	// private static final String KEY_VALUE = "value";
	// private static final String KEY_VALUE_HISTORY_SEARCH = "search_value";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CACHEDATA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME_API + " TEXT," + KEY_VALUE + " TEXT" + ")";

		String CREATE_SEARCH_HISTORY_TABLE = "CREATE TABLE " + TABLE_SEARCH_HISTORY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VALUE_HISTORY_SEARCH + " TEXT" + ")";

		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + USER_ID + " TEXT," + USER_DE_SAT + " TEXT," + USER_EMAIL + " TEXT," + USER_LOGIN
				+ " TEXT," + USER_PHONE + " TEXT," + USERNAME + " TEXT, " + USER_BIRTHDAY + " TEXT," + USER_SEX + " TEXT" + ")";

		String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "(" + KEY_ID + " INTEGER PRIMARY KEY," + PRODUCT_ID + " TEXT," + NAME + " TEXT," + PRICE + " TEXT," + QUANTITY + " INTEGER," + IMAGE
				+ " TEXT," + DINHNGAY + " TEXT, " + NGAYNHAN + " TEXT," + NGAYTRA + " TEXT," + PRODUCT_TYPE + " TEXT," + MAXQUANTITY + " TEXT" + ")";
		String CREATE_THANHTOANTAM_TABLE = "CREATE TABLE " + TABLE_THANHTOANTAM + "(" + KEY_ID + " INTEGER PRIMARY KEY," + PRODUCT_ID + " TEXT," + QUANTITY + " TEXT," + CHECK_IN + " TEXT,"
				+ CHECK_OUT + " TEXT," + NAME + " TEXT," + PHONE + " TEXT, " + EMAIL + " TEXT," + YESGO_ADULTS + " TEXT," + YESGO_CHILDS + " TEXT" + ")";
		// HotdealUtilities.showLog(CREATE_CART_TABLE);
		db.execSQL(CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_SEARCH_HISTORY_TABLE);
		db.execSQL(CREATE_USERS_TABLE);
		db.execSQL(CREATE_CART_TABLE);
		// db.execSQL(CREATE_THANHTOANTAM_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CACHEDATA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH_HISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_THANHTOANTAM);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
	// public void addANewCart(DetailsModel user) {
	// SQLiteDatabase db = this.getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put(PRODUCT_ID, user.getProductId());
	// values.put(NAME, user.getName());
	// values.put(PRICE, user.getPrice());
	// values.put(QUANTITY, user.getQuantityUserChoosen());
	// values.put(IMAGE, user.getImage());
	// values.put(NGAYNHAN, user.getNgayNhan() + "");
	// values.put(NGAYTRA, user.getNgayTra() + "");
	// values.put(PRODUCT_TYPE, user.getProductType() + "");
	// values.put(MAXQUANTITY, user.getMaxQty() + "");
	// // Inserting Row
	// db.insert(TABLE_CART, null, values);
	// db.close(); // Closing database connection
	// }

	public ArrayList<DetailsModel> getAllCart() {
		ArrayList<DetailsModel> list = new ArrayList<DetailsModel>();
		// String selectQuery = "SELECT  * FROM " + TABLE_CART + " ORDER BY " +
		// KEY_ID + " DESC";
		String selectQuery = "SELECT  * FROM " + TABLE_CART;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					try {
						DetailsModel md = new DetailsModel();
						md.setId(cursor.getString(0));
						md.setProductId(cursor.getString(1));
						md.setName(cursor.getString(2));
						md.setPrice(cursor.getString(3));
						md.setQuantityUserChoosen(cursor.getInt(4));
						md.setImage(cursor.getString(5));
						md.setNgayNhan(Integer.parseInt(cursor.getString(7)));
						md.setNgayTra(Integer.parseInt(cursor.getString(8)));
						md.setProductType(cursor.getString(9));
						md.setMaxQty(cursor.getString(10));
						list.add(md);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return list;
	}

	public void addANewCart(final DetailsModel user) {
		// check if exists
		SQLiteDatabase db = this.getWritableDatabase();
		if (checkProductExists(user.getProductId(), db)) {
			updateCartViaProductID(user.getProductId(), user.getQuantityUserChoosen(), db);
			// db.close();
		} else {
			ContentValues values = new ContentValues();
			values.put(PRODUCT_ID, user.getProductId());
			values.put(NAME, user.getName());
			values.put(PRICE, user.getPrice());
			values.put(QUANTITY, user.getQuantityUserChoosen());
			values.put(IMAGE, user.getImage());
			values.put(NGAYNHAN, user.getNgayNhan() + "");
			values.put(NGAYTRA, user.getNgayTra() + "");
			values.put(PRODUCT_TYPE, user.getProductType() + "");
			values.put(MAXQUANTITY, user.getMaxQty() + "");
			// Inserting Row
			db.insert(TABLE_CART, null, values);
			db.close(); // Closing database connection
		}

	}

	public boolean checkProductExists(String producID, SQLiteDatabase db) {
		String selectQuery = "SELECT * FROM " + TABLE_CART + " WHERE " + PRODUCT_ID + "='" + producID + "'";
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		try {
			if (cursor.getCount() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return false;
	}

	public void updateCartViaProductID(String Pid, int qly, SQLiteDatabase db) {
		// cong don
		// String selectQuery = "UPDATE " + TABLE_CART + " SET " + QUANTITY +
		// "=" + QUANTITY + "+"
		// + qly + " WHERE " + PRODUCT_ID + "='" + Pid + "'";
		// lay so cuoi cung
		String selectQuery = "UPDATE " + TABLE_CART + " SET " + QUANTITY + "=" + qly + " WHERE " + PRODUCT_ID + "='" + Pid + "'";
		db.execSQL(selectQuery);
	}

	public int updateCart(int value, String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(QUANTITY, value + "");
		return db.update(TABLE_CART, values, KEY_ID + " = ?", new String[] { id });
	}

	public void deleteCartAt(String pos) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CART, NAME + " = ?", new String[] { String.valueOf(pos) });
		db.close();
	}

	public void deleteCart() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CART, null, null);
		db.close();
	}

	public int getCountCart() {
		int count = 0;
		String selectQuery = "SELECT COUNT(*) FROM " + TABLE_CART;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		try {
			if (cursor.moveToFirst()) {
				do {
					try {
						count = cursor.getInt(0);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return count;
	}

	public void addNewUser(UsersModel user) {
		SQLiteDatabase db = this.getWritableDatabase();
		deleteUser(db);
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUserID());
		values.put(USER_DE_SAT, user.getDelivery_saturday());
		values.put(USER_EMAIL, user.getEmail());
		values.put(USER_LOGIN, user.getUser_login());
		values.put(USER_PHONE, user.getPhone());
		values.put(USERNAME, user.getUsername());
		values.put(USER_BIRTHDAY, user.getBirthday());
		values.put(USER_SEX, user.getSex());
//		values.put(USER_FB_ID, faceID);
		// Inserting Row
		db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
	}

	public void deleteUser(SQLiteDatabase db) {
		db.delete(TABLE_USERS, null, null);
	}

	public UsersModel getUser() {
		SQLiteDatabase db = this.getReadableDatabase();
		UsersModel md = null;
		Cursor cursor = db.query(TABLE_USERS, null, null, null, null, null, null, null);
		try {
			if (cursor != null && cursor.getCount() != 0)
				cursor.moveToFirst();
			md = new UsersModel();
			md.setUserID(cursor.getString(1));
			md.setDelivery_saturday(cursor.getString(2));
			md.setEmail(cursor.getString(3));
			md.setUser_login(cursor.getString(4));
			md.setPhone(cursor.getString(5));
			md.setUsername(cursor.getString(6));
			md.setBirthday(cursor.getString(7));
			md.setSex(cursor.getString(8));
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return md;
	}

	public void addNewCache(String api, String data) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME_API, api);
		values.put(KEY_VALUE, data);

		// Inserting Row
		db.insert(TABLE_CACHEDATA, null, values);
		db.close(); // Closing database connection
	}

	public void deleteApi(String api) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CACHEDATA, KEY_NAME_API + " = ?", new String[] { api });
		db.close();
	}

	public String getCache(String api) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CACHEDATA, new String[] { KEY_ID, KEY_NAME_API, KEY_VALUE }, KEY_NAME_API + "=?", new String[] { api }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		String value = "";
		try {
			value = cursor.getString(2);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (cursor != null)
				cursor.close();
		}
		// String contact = new String(Integer.parseInt(cursor.getString(0)),
		// cursor.getString(1), cursor.getString(2));
		// return contact
		return value;
	}

	public int updateValue(String api, String value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_VALUE, value);
		return db.update(TABLE_CACHEDATA, values, KEY_NAME_API + " = ?", new String[] { api });
	}

	public void addNewKeySearch(String a) {
		SQLiteDatabase db = this.getWritableDatabase();
		if (!checkKeySearchExists(a, db)) {
			ContentValues values = new ContentValues();
			values.put(KEY_VALUE_HISTORY_SEARCH, a);
			db.insert(TABLE_SEARCH_HISTORY, null, values);
			db.close(); // Closing database connection
		}
	}

	public boolean checkKeySearchExists(String producID, SQLiteDatabase db) {
		String selectQuery = "SELECT * FROM " + TABLE_SEARCH_HISTORY + " WHERE " + KEY_VALUE_HISTORY_SEARCH + " =?";
		Cursor cursor = db.rawQuery(selectQuery, new String[] { producID });

		// looping through all rows and adding to list
		try {
			if (cursor.getCount() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return false;
	}

	public void deleteHistory() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SEARCH_HISTORY, null, null);
		db.close();
	}

	public List<String> getAllKeySearch() {
		List<String> contactList = new ArrayList<String>();
		String selectQuery = "SELECT  * FROM " + TABLE_SEARCH_HISTORY + " ORDER BY " + KEY_ID + " DESC LIMIT 5";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		try {
			if (cursor.moveToFirst()) {
				do {
					try {
						contactList.add(cursor.getString(1));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}

		return contactList;
	}

	// Getting contacts Count
	// public int getContactsCount() {
	// String countQuery = "SELECT  * FROM " + TABLE_CACHEDATA;
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(countQuery, null);
	// cursor.close();
	//
	// // return count
	// return cursor.getCount();
	// }

}
