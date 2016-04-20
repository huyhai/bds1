package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class CateSildeSubModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subcateID;
	private String name;

	public CateSildeSubModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setSubcateID(HotdealUtilities.getDataString(jSonInfo, "categoryId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubcateID() {
		return subcateID;
	}

	public void setSubcateID(String subcateID) {
		this.subcateID = subcateID;
	}


}