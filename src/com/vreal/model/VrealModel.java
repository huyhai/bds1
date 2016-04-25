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

	// listDienTichANDGia
	private int value1;
	private int value2;
	
	//detai
	private String address;
	private String price;
	private String Description;

	private ArrayList<VrealModel> listSub;

	public VrealModel() {

	}

	public void setSeachData(JSONObject jSonInfo) throws JSONException {
//		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "RealName"));
		setChoosen(false);
		this.setAddress(HotdealUtilities.getDataString(jSonInfo, "Address"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "Price"));
		this.setDescription(HotdealUtilities.getDataString(jSonInfo, "Description"));


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

	public void setHuong(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "DirectionName"));
		setChoosen(false);

	}

	public void setDuan(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setListSub(JSONObject jSonInfo) throws JSONException {
		setChoosen(false);

	}

	public void setLoaiNhaDat(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(HotdealUtilities.getDataString(jSonInfo, "RealsCateName"));
		JSONArray listJson = jSonInfo.getJSONArray("V_RealNews");
		getListSub().clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			VrealModel md = new VrealModel();
			md.setListSub(jSonOb);
			getListSub().add(md);
		}
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

	public ArrayList<VrealModel> getListSub() {
		if (null != listSub) {
			return listSub;
		} else {
			return listSub = new ArrayList<VrealModel>();
		}
	}

	public void setListSub(ArrayList<VrealModel> listSub) {
		this.listSub = listSub;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}