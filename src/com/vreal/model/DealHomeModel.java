package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class DealHomeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String name;
	private ArrayList<DetailsModel> listTintuc;
	
	public DealHomeModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "NewsTypeName"));
		setListTintuc(new ArrayList<DetailsModel>());
		JSONArray listJson;
		listJson=HotdealUtilities.getArray(jSonInfo, "DSTin");
//		listJson = jSonInfo.getJSONArray("DSTin");
		getListTintuc().clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			DetailsModel md=new DetailsModel();
			md.setData(jSonOb);
			getListTintuc().add(md);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ArrayList<DetailsModel> getListTintuc() {
		return listTintuc;
	}

	public void setListTintuc(ArrayList<DetailsModel> listTintuc) {
		this.listTintuc = listTintuc;
	}



}