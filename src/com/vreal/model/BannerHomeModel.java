package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;


public class BannerHomeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String image;
	private String name;
	private String url;

	public BannerHomeModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "title"));
		this.setUrl(HotdealUtilities.getDataString(jSonInfo, "url"));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}