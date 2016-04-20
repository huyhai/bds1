package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;


public class CateModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int image;

	public CateModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public int getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(int image) {
		this.image = image;
	}



}