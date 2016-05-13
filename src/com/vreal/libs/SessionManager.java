package com.vreal.libs;

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
	public static final String KEY_WARDJSON = "wardJSON";
	public static final String KEY_STREET = "streetJson";
	public static final String KEY_KHUVUC1 = "khucuc";
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
	public static final String KEY_KHUVUC= "kv";
	public static final String KEY_KHUVUCID = "kvi";
	public static final String KEY_DUONG= "duong";
	public static final String KEY_DUONGID = "duongi";
	public static final String KEY_WARD= "ward";
	public static final String KEY_WARDID = "wardi";
	public static final String KEY_TYPE= "type";
	public static final String KEY_TYPEID = "typei";
	public static final String KEY_DTFROM = "dtF";
	public static final String KEY_DTTO= "dtT";
	public static final String KEY_DTNAME = "dtN";
	public static final String KEY_GIAFROM = "GF";
	public static final String KEY_GIATO= "GT";
	public static final String KEY_GIANAME = "GN";
	public static final String HUONGID= "huongi";
	public static final String HUONGNAME = "huongN";
	public static final String SOPHONG = "sophong";
	public static final String DUANID = "duanI";
	public static final String DUANNAME = "duanN";
	
	public static final String KEY_SAVESETTINGS = "saves";
	private static final String KEY_PROVICEJSON = "proJson";
	private static final String KEY_DISTRICTJSON = "disJson";

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
		pic.put(KEY_KHUVUC, pref.getString(KEY_KHUVUC, ""));
		pic.put(KEY_KHUVUCID, pref.getString(KEY_KHUVUCID, ""));
		pic.put(KEY_DUONG, pref.getString(KEY_DUONG, ""));
		pic.put(KEY_DUONGID, pref.getString(KEY_DUONGID, ""));
		pic.put(KEY_WARD, pref.getString(KEY_WARD, ""));
		pic.put(KEY_WARDID, pref.getString(KEY_WARDID, ""));
		pic.put(KEY_TYPE, pref.getString(KEY_TYPE, ""));
		pic.put(KEY_TYPEID, pref.getString(KEY_TYPEID, ""));
		pic.put(KEY_DTFROM, pref.getString(KEY_DTFROM, ""));
		pic.put(KEY_DTTO, pref.getString(KEY_DTTO, ""));
		pic.put(KEY_DTNAME, pref.getString(KEY_DTNAME, ""));
		pic.put(KEY_GIAFROM, pref.getString(KEY_GIAFROM, ""));
		pic.put(KEY_GIATO, pref.getString(KEY_GIATO, ""));
		pic.put(KEY_GIANAME, pref.getString(KEY_GIANAME, ""));
		pic.put(HUONGID, pref.getString(HUONGID, ""));
		pic.put(HUONGNAME, pref.getString(HUONGNAME, ""));
		pic.put(SOPHONG, pref.getString(SOPHONG, "0"));
		
		pic.put(DUANID, pref.getString(DUANID, ""));
		pic.put(DUANNAME, pref.getString(DUANNAME, "0"));
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
		editor.putString(KEY_KHUVUC, set.get(KEY_KHUVUC));
		editor.putString(KEY_KHUVUCID, set.get(KEY_KHUVUCID));
		editor.putString(KEY_DUONG, set.get(KEY_DUONG));
		editor.putString(KEY_DUONGID, set.get(KEY_DUONGID));
		editor.putString(KEY_WARD, set.get(KEY_WARD));
		editor.putString(KEY_WARDID, set.get(KEY_WARDID));
		editor.putString(KEY_TYPE, set.get(KEY_TYPE));
		editor.putString(KEY_TYPEID, set.get(KEY_TYPEID));
		editor.putString(KEY_DTFROM, set.get(KEY_DTFROM));
		editor.putString(KEY_DTTO, set.get(KEY_DTTO));
		editor.putString(KEY_DTNAME, set.get(KEY_DTNAME));
		editor.putString(KEY_GIAFROM, set.get(KEY_GIAFROM));
		editor.putString(KEY_GIATO, set.get(KEY_GIATO));
		editor.putString(KEY_GIANAME, set.get(KEY_GIANAME));
		editor.putString(HUONGID, set.get(HUONGID));
		editor.putString(HUONGNAME, set.get(HUONGNAME));
		editor.putString(SOPHONG, set.get(SOPHONG));
		
		editor.putString(DUANID, set.get(DUANID));
		editor.putString(DUANNAME, set.get(DUANNAME));
		editor.commit();
	}

	public String getKhuVucJson() {
		return pref.getString(KEY_KHUVUC1, "");
	}

	public void setKhuVucJson(String tinh) {
		editor.putString(KEY_KHUVUC1, tinh);
		editor.commit();
	}

	public String getStreetJson() {
		return pref.getString(KEY_STREET, "");
	}

	public void setStreetJson(String tinh) {
		editor.putString(KEY_STREET, tinh);
		editor.commit();
	}

	public String getWardJson() {
		return pref.getString(KEY_WARDJSON, "");
	}

	public void setWardJson(String tinh) {
		editor.putString(KEY_WARDJSON, tinh);
		editor.commit();
	}

	public String getProviceJson() {
		return pref.getString(KEY_PROVICEJSON, "");
	}

	public void setProviceJson(String tinh) {
		editor.putString(KEY_PROVICEJSON, tinh);
		editor.commit();
	}

	public String getDisJson() {
		return pref.getString(KEY_DISTRICTJSON, "");
	}

	public void setDisJson(String tinh) {
		editor.putString(KEY_DISTRICTJSON, tinh);
		editor.commit();
	}

	public boolean isSaveSetting() {
		return pref.getBoolean(KEY_SAVESETTINGS, false);
	}

	public void setSaveSetting(boolean islog) {
		editor.putBoolean(KEY_SAVESETTINGS, islog);
		editor.commit();
	}
	public float getTextSize() {
		return pref.getFloat("TES", 0);
	}

	public void setTextSize(float tinh) {
		editor.putFloat("TES", tinh);
		editor.commit();
	}

}
