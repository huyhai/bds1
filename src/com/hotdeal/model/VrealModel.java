package com.hotdeal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class VrealModel implements Serializable {
	private static final long serialVersionUID = 1L;
	//provice
	private String id;
	private String ProvinceName;
	private boolean IsEnable;
	private String ProvinceCode;

	public VrealModel() {

	}

	public void setDataProvince(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "ProvinceName"));
		this.setIsEnable(HotdealUtilities.getDataBool(jSonInfo, "IsEnable"));
		this.setProvinceCode(HotdealUtilities.getDataString(jSonInfo, "ProvinceCode"));
//		this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo, "shipping_cost"));
//		this.setQuantity(HotdealUtilities.getDataString(jSonInfo, "quantity"));
//		this.setDiscounts(HotdealUtilities.getDataString(jSonInfo, "discounts"));

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}

	public boolean isIsEnable() {
		return IsEnable;
	}

	public void setIsEnable(boolean isEnable) {
		IsEnable = isEnable;
	}

	public String getProvinceCode() {
		return ProvinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		ProvinceCode = provinceCode;
	}

}