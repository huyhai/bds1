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
	public static final String KEY_NAME = "name";
	public static final String KEY_PASS = "pass";
	public static final String KEY_ISLOGIN = "log";
	public static final String KEY_TINH = "tinhthanh";
	public static final String KEY_LOCATIONID = "locationid";
	public static final String KEY_TS = "ts";
	public static final String KEY_FID = "fID";

	@SuppressLint("CommitPrefEdits")
	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void saveLogina(String name, String pass, boolean islog) {
		editor.putString(KEY_NAME, name);
		editor.putString(KEY_PASS, pass);
		editor.putBoolean(KEY_ISLOGIN, islog);

		editor.commit();
	}

	public HashMap<String, String> getUserLogin() {
		HashMap<String, String> pic = new HashMap<String, String>();
		pic.put(KEY_NAME, pref.getString(KEY_NAME, ""));
		pic.put(KEY_PASS, pref.getString(KEY_PASS, ""));

		return pic;
	}

	public String getFID() {
		return pref.getString(KEY_FID, "");
	}

	public void setFID(String tinh) {
		editor.putString(KEY_FID, tinh);
		editor.commit();
	}

	// public int getTinh() {
	//
	// return pref.getInt(KEY_TINH, 1);
	// }
	//
	// public void saveTinh(int tinh) {
	// editor.putInt(KEY_TINH, tinh);
	// editor.commit();
	// }
	// public boolean isLogin() {
	// return pref.getBoolean(KEY_IS_LOGIN, false);
	// }
	//
	// public void saveTinh(boolean islog) {
	// editor.putBoolean(KEY_IS_LOGIN, islog);
	// editor.commit();
	// }

	public String getPass() {
		return pref.getString(KEY_PASS, "");
	}

	public void setPass(String tinh) {
		editor.putString(KEY_PASS, tinh);
		editor.commit();
	}

	public float getTextSize() {
		return pref.getFloat(KEY_TS, 0);
	}

	public void setTextSize(float tinh) {
		editor.putFloat(KEY_TS, tinh);
		editor.commit();
	}

	public String getLocationID() {
		return pref.getString(KEY_LOCATIONID, "");
	}

	public void setLocationID(String tinh) {
		editor.putString(KEY_LOCATIONID, tinh);
		editor.commit();
	}

	public void saveisLoggedIn(boolean name) {
		editor.putBoolean(KEY_ISLOGIN, name);
		editor.commit();
	}

	public boolean isLoggedIn() {
		return pref.getBoolean(KEY_ISLOGIN, false);
	}

	public String getJsonCache(String Key) {
		return pref.getString(Key, "");
	}

	public void setJsonCache(String Key, String json) {
		editor.putString(Key, json);
		editor.commit();
	}

	public Long getTimeStart() {
		return pref.getLong("TimeStart", 0);
	}

	public void setTimeStart(long json) {
		editor.putLong("TimeStart", json);
		editor.commit();
	}
	public Long getSVTime() {
		return pref.getLong("SVTime", 0);
	}

	public void setSVTime(long json) {
		editor.putLong("SVTime", json);
		editor.commit();
	}
}
