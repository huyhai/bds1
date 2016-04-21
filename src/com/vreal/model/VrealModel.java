package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class VrealModel implements Serializable {
	private static final long serialVersionUID = 1L;
	// provice
	private String id;
	private String ProvinceName;
	private boolean IsEnable;
	private String ProvinceCode;
	private boolean isChoosen;
	private String ProvinceID;
	private String DistrictID;
	private String streetID;
	private String wardID;

	public VrealModel() {

	}

	public void setDataProvince(JSONObject jSonInfo) throws JSONException {
		this.setProvinceID(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "ProvinceName"));
		this.setIsEnable(HotdealUtilities.getDataBool(jSonInfo, "IsEnable"));
		this.setProvinceCode(HotdealUtilities.getDataString(jSonInfo, "ProvinceCode"));
		setChoosen(false);
		// this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo,
		// "shipping_cost"));
		// this.setQuantity(HotdealUtilities.getDataString(jSonInfo,
		// "quantity"));
		// this.setDiscounts(HotdealUtilities.getDataString(jSonInfo,
		// "discounts"));

	}

	public void setDataDistrict(JSONObject jSonInfo) throws JSONException {
		this.setProvinceID(HotdealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "DistrictName"));
		this.setIsEnable(HotdealUtilities.getDataBool(jSonInfo, "IsEnable"));
		setChoosen(false);

	}

	public void setDataWard(JSONObject jSonInfo) throws JSONException {
		this.setWardID(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceID(HotdealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(HotdealUtilities.getDataString(jSonInfo, "DistrictID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "WardName"));
		this.setIsEnable(HotdealUtilities.getDataBool(jSonInfo, "IsEnable"));
		setChoosen(false);

	}

	public void setDataStreet(JSONObject jSonInfo) throws JSONException {
		this.setStreetID(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "StreetName"));
		this.setIsEnable(HotdealUtilities.getDataBool(jSonInfo, "IsEnable"));
		this.setProvinceID(HotdealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(HotdealUtilities.getDataString(jSonInfo, "DistrictID"));
		setChoosen(false);

	}

	public void setTypeProperty(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "TypeName"));
		setChoosen(false);

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

	public boolean isChoosen() {
		return isChoosen;
	}

	public void setChoosen(boolean isChoosen) {
		this.isChoosen = isChoosen;
	}

	public String getProvinceID() {
		return ProvinceID;
	}

	public void setProvinceID(String provinceID) {
		ProvinceID = provinceID;
	}

	public String getDistrictID() {
		return DistrictID;
	}

	public void setDistrictID(String districtID) {
		DistrictID = districtID;
	}

	public String getStreetID() {
		return streetID;
	}

	public void setStreetID(String streetID) {
		this.streetID = streetID;
	}

	public String getWardID() {
		return wardID;
	}

	public void setWardID(String wardID) {
		this.wardID = wardID;
	}

}