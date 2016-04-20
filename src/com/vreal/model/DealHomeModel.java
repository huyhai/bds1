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
	private ArrayList<DetailsModel> listDeal;
	
	public DealHomeModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "categoryId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		setListDeal(new ArrayList<DetailsModel>());
		JSONArray listJson;
		listJson = jSonInfo.getJSONArray("listProduct");
		getListDeal().clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			DetailsModel md=new DetailsModel();
			md.setData(jSonOb);
			getListDeal().add(md);
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

	/**
	 * @return the listDeal
	 */
	public ArrayList<DetailsModel> getListDeal() {
		return listDeal;
	}

	/**
	 * @param listDeal the listDeal to set
	 */
	public void setListDeal(ArrayList<DetailsModel> listDeal) {
		this.listDeal = listDeal;
	}


}