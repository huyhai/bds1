package com.hotdeal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class FilterModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String field;
	private ArrayList<FilterModel> listFilterChild;
	private String categoryId;
	private String valueSet = "Tất cả";
	private String valueSetID = "";
	private boolean check;
	private String min_price;
	private String max_price;
	private String range;

	public FilterModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setField(HotdealUtilities.getDataString(jSonInfo, "field"));
		this.setMin_price(HotdealUtilities.getDataString(jSonInfo, "min_price"));
		this.setMax_price(HotdealUtilities.getDataString(jSonInfo, "max_price"));
		this.setRange(HotdealUtilities.getDataString(jSonInfo, "range"));
		setListFilterChild(new ArrayList<FilterModel>());
		JSONArray listJson;
		try {
			listJson = jSonInfo.getJSONArray("listCategory");
			getListFilterChild().clear();
			FilterModel md1 = new FilterModel();
			md1.setName("Tất cả");
			md1.setCategoryId("");
			getListFilterChild().add(md1);
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject ob = listJson.getJSONObject(i);
				FilterModel md = new FilterModel();
				md.setDataChild(ob);
				getListFilterChild().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		try {
			listJson = jSonInfo.getJSONArray("listDistrict");
			getListFilterChild().clear();
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject ob = listJson.getJSONObject(i);
				FilterModel md = new FilterModel();
				md.setDataChild2(ob);
				getListFilterChild().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			listJson = jSonInfo.getJSONArray("listLocation");
			getListFilterChild().clear();
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject ob = listJson.getJSONObject(i);
				FilterModel md = new FilterModel();
				md.setDataChildLocation(ob);
				getListFilterChild().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			listJson = jSonInfo.getJSONArray("listStar");
			getListFilterChild().clear();
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject ob = listJson.getJSONObject(i);
				FilterModel md = new FilterModel();
				md.setDataChildStar(ob);
				getListFilterChild().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		try {
			listJson = jSonInfo.getJSONArray("listState");
			getListFilterChild().clear();
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject ob = listJson.getJSONObject(i);
				FilterModel md = new FilterModel();
				md.setDataChildState(ob);
				getListFilterChild().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void setDataChild(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "categoryId"));
	}

	public void setDataChild2(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "districtName"));
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "districtId"));
	}

	public void setDataChildLocation(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "locationName"));
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "locationId"));
	}

	public void setDataChildStar(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "starName"));
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "starId"));
	}
	public void setDataChildState(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "stateName"));
		this.setCategoryId(HotdealUtilities.getDataString(jSonInfo, "stateId"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FilterModel> getListFilterChild() {
		return listFilterChild;
	}

	public void setListFilterChild(ArrayList<FilterModel> listFilterChild) {
		this.listFilterChild = listFilterChild;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getValueSet() {
		return valueSet;
	}

	public void setValueSet(String valueSet) {
		this.valueSet = valueSet;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getValueSetID() {
		return valueSetID;
	}

	public void setValueSetID(String valueSetID) {
		this.valueSetID = valueSetID;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMin_price() {
		return min_price;
	}

	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}

	public String getMax_price() {
		return max_price;
	}

	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

}