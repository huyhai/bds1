package com.hotdeal.libs;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "Wiim";
	// public static final String KEY_NAME = "name";
	// public static final String KEY_PASS = "pass";
	// public static final String KEY_ISLOGIN = "log";
	// public static final String KEY_TINH = "tinhthanh";
	// public static final String KEY_LOCATIONID = "locationid";
	// public static final String KEY_TS = "ts";
	// public static final String KEY_FID = "fID";

	public static final String KEY_PROVICE = "provice";
	public static final String KEY_DIS = "dis";
	public static final String KEY_AREA = "area";
	public static final String KEY_PRICE = "price";
	public static final String KEY_WAY = "way";
	public static final String KEY_PROVICEID = "provicei";
	public static final String KEY_DISID = "disi";
	public static final String KEY_AREAID = "areai";
	public static final String KEY_PRICEID = "pricei";
	public static final String KEY_WAYID = "wayi";
	public static final String KEY_SAVESETTINGS = "saves";

	@SuppressLint("CommitPrefEdits")
	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public HashMap<String, String> getSettings() {
		HashMap<String, String> pic = new HashMap<String, String>();
		pic.put(KEY_PROVICE, pref.getString(KEY_PROVICE, ""));
		pic.put(KEY_PROVICEID, pref.getString(KEY_PROVICEID, ""));
		pic.put(KEY_DIS, pref.getString(KEY_DIS, ""));
		pic.put(KEY_DISID, pref.getString(KEY_DISID, ""));
		pic.put(KEY_AREA, pref.getString(KEY_AREA, ""));
		pic.put(KEY_AREAID, pref.getString(KEY_AREAID, ""));
		pic.put(KEY_PRICE, pref.getString(KEY_PRICE, ""));
		pic.put(KEY_PRICEID, pref.getString(KEY_PRICEID, ""));
		pic.put(KEY_WAY, pref.getString(KEY_WAY, ""));
		pic.put(KEY_WAYID, pref.getString(KEY_WAYID, ""));
		return pic;
	}

	public void setSettings(HashMap<String, String> set) {
		editor.putString(KEY_PROVICE, set.get(KEY_PROVICE));
		editor.putString(KEY_PROVICEID, set.get(KEY_PROVICEID));
		editor.putString(KEY_DIS, set.get(KEY_DIS));
		editor.putString(KEY_DISID, set.get(KEY_DISID));
		editor.putString(KEY_AREA, set.get(KEY_AREA));
		editor.putString(KEY_AREAID, set.get(KEY_AREAID));
		editor.putString(KEY_PRICE, set.get(KEY_PRICE));
		editor.putString(KEY_PRICEID, set.get(KEY_PRICEID));
		editor.putString(KEY_WAY, set.get(KEY_WAY));
		editor.putString(KEY_WAYID, set.get(KEY_WAYID));
		editor.commit();
	}

	//
	// public String getFID() {
	// return pref.getString(KEY_FID, "");
	// }
	//
	// public void setFID(String tinh) {
	// editor.putString(KEY_FID, tinh);
	// editor.commit();
	// }

	// public int getTinh() {
	//
	// return pref.getInt(KEY_TINH, 1);
	// }
	//
	// public void saveTinh(int tinh) {
	// editor.putInt(KEY_TINH, tinh);
	// editor.commit();
	// }
	public boolean isSaveSetting() {
		return pref.getBoolean(KEY_SAVESETTINGS, false);
	}

	public void setSaveSetting(boolean islog) {
		editor.putBoolean(KEY_SAVESETTINGS, islog);
		editor.commit();
	}

	// public String getPass() {
	// return pref.getString(KEY_PASS, "");
	// }
	//
	// public void setPass(String tinh) {
	// editor.putString(KEY_PASS, tinh);
	// editor.commit();
	// }
	//
	// public float getTextSize() {
	// return pref.getFloat(KEY_TS, 0);
	// }
	//
	// public void setTextSize(float tinh) {
	// editor.putFloat(KEY_TS, tinh);
	// editor.commit();
	// }
	//
	// public String getLocationID() {
	// return pref.getString(KEY_LOCATIONID, "");
	// }
	//
	// public void setLocationID(String tinh) {
	// editor.putString(KEY_LOCATIONID, tinh);
	// editor.commit();
	// }
	//
	// public void saveisLoggedIn(boolean name) {
	// editor.putBoolean(KEY_ISLOGIN, name);
	// editor.commit();
	// }
	//
	// public boolean isLoggedIn() {
	// return pref.getBoolean(KEY_ISLOGIN, false);
	// }
	//
	// public String getJsonCache(String Key) {
	// return pref.getString(Key, "");
	// }
	//
	// public void setJsonCache(String Key, String json) {
	// editor.putString(Key, json);
	// editor.commit();
	// }
	//
	// public Long getTimeStart() {
	// return pref.getLong("TimeStart", 0);
	// }
	//
	// public void setTimeStart(long json) {
	// editor.putLong("TimeStart", json);
	// editor.commit();
	// }
	//
	// public Long getSVTime() {
	// return pref.getLong("SVTime", 0);
	// }
	//
	// public void setSVTime(long json) {
	// editor.putLong("SVTime", json);
	// editor.commit();
	// }
}
