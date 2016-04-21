package com.vreal.connection;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Hai on 1/25/2016.
 */
public interface JsonObjectInterface {
	void callResultJOb(Context activity, JSONObject result);

	void callResultJAr(Context activity, JSONArray result);
}
