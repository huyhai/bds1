package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.VrealUtilities;

public class VrealModel implements Serializable {
	private static final long serialVersionUID = 1L;
	// provice
	private String id = "";
	private String ProvinceName = "";
	private boolean IsEnable;
	private String ProvinceCode = "";
	private boolean isChoosen;
	private String ProvinceID = "";
	private String DistrictID = "";
	private String streetID = "";
	private String wardID = "";

	// listDienTichANDGia
	private int value1;
	private int value2;

	// detai
	private String address = "";
	private double price;
	private String Description = "";
	private String Icon = "";
	private ArrayList<String> listPhoto;
	private String Acreage = "";
	private String ContactName = "";
	private String ContactAddress = "";
	private String ContacPhone = "";
	private String ContactEmail = "";
	private double Latitude;
	private double Longitude;
	private String Developer = "";

	private int RealNewsTypeID;
	private String PublishStart = "";
	private String PublishEnd = "";
	private String UnitName = "";
	 private int AreaID;
	 private String filterString;
	// private String ContactAddress;

	private ArrayList<String> listTienIch;

	private ArrayList<VrealModel> listSub;

	public VrealModel() {

	}

	public VrealModel(String name) {
		this.setProvinceName(name);
	}

	public void setSeachData(JSONObject jSonInfo) throws JSONException {
		this.setUnitName(VrealUtilities.getDataString(jSonInfo, "UnitName"));
		this.setId(VrealUtilities.getDataString(jSonInfo, "RealID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "RealName"));
		setChoosen(false);
		this.setAddress(VrealUtilities.getDataString(jSonInfo, "Address"));
		this.setPrice(VrealUtilities.getDataDouble(jSonInfo, "Price"));
		this.setDescription(VrealUtilities.getDataString(jSonInfo, "Description"));
		this.setIcon(VrealUtilities.getDataString(jSonInfo, "Icon"));
		this.setAcreage(VrealUtilities.getDataString(jSonInfo, "Acreage"));
		this.setContactName(VrealUtilities.getDataString(jSonInfo, "ContactName"));
		this.setContactAddress(VrealUtilities.getDataString(jSonInfo, "ContactAddress"));
		this.setContacPhone(VrealUtilities.getDataString(jSonInfo, "ContacPhone"));
		this.setContactEmail(VrealUtilities.getDataString(jSonInfo, "ContactEmail"));
		this.setLongitude(VrealUtilities.getDataDouble(jSonInfo, "Longitude"));
		this.setLatitude(VrealUtilities.getDataDouble(jSonInfo, "Latitude"));

		this.setRealNewsTypeID(VrealUtilities.getDataInt(jSonInfo, "RealNewsTypeID"));
		this.setPublishStart(VrealUtilities.getDataString(jSonInfo, "PublishStart"));
		this.setPublishEnd(VrealUtilities.getDataString(jSonInfo, "PublishEnd"));
		this.setAreaID(VrealUtilities.getDataInt(jSonInfo, "AreaID"));
//		setFilterString(getAreaID() + "-" + getPrice() + "-" + getRealNewsTypeID());

		setListPhoto(new ArrayList<String>());
		JSONArray listJson;
		try {
			listJson = jSonInfo.getJSONArray("RealPhoto");
			for (int i = 0; i < listJson.length(); i++) {
				String jSonOb = listJson.getString(i);
				getListPhoto().add(jSonOb);
			}
		} catch (Exception e) {
		}

		setListTienIch(new ArrayList<String>());
		JSONArray listJsonTC;
		try {
			listJsonTC = jSonInfo.getJSONArray("FeatureInHouseList");
			for (int i = 0; i < listJsonTC.length(); i++) {
				String jSonOb = listJsonTC.getString(i);
				getListTienIch().add(jSonOb);
			}
		} catch (Exception e) {
		}

	}

	public void setSeachProData(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "ProjectName"));
		setChoosen(false);
		this.setAddress(VrealUtilities.getDataString(jSonInfo, "Address"));
		this.setPrice(VrealUtilities.getDataDouble(jSonInfo, "Price"));
		this.setDescription(VrealUtilities.getDataString(jSonInfo, "Content"));
		this.setIcon(VrealUtilities.getDataString(jSonInfo, "Icon"));
		this.setAcreage(VrealUtilities.getDataString(jSonInfo, "Summary"));
		this.setDeveloper(VrealUtilities.getDataString(jSonInfo, "Developer"));
		// this.setContactAddress(HotdealUtilities.getDataString(jSonInfo,
		// "ContactAddress"));
		// this.setContacPhone(HotdealUtilities.getDataString(jSonInfo,
		// "ContacPhone"));
		// this.setContactEmail(HotdealUtilities.getDataString(jSonInfo,
		// "ContactEmail"));
		// this.setLongitude(HotdealUtilities.getDataDouble(jSonInfo,
		// "Longitude"));
		// this.setLatitude(HotdealUtilities.getDataDouble(jSonInfo,
		// "Latitude"));

		setListPhoto(new ArrayList<String>());
		JSONArray listJson;
		try {
			listJson = jSonInfo.getJSONArray("GalleryList");
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject job = listJson.getJSONObject(i);
				String jSonOb = VrealUtilities.getDataString(job, "Url");
				getListPhoto().add(jSonOb);
			}
		} catch (Exception e) {
		}

		setListTienIch(new ArrayList<String>());
		JSONArray listJsonTC;
		try {
			listJsonTC = jSonInfo.getJSONArray("FacilitiesList");
			for (int i = 0; i < listJsonTC.length(); i++) {
				String jSonOb = listJsonTC.getString(i);
				getListTienIch().add(jSonOb);
			}
		} catch (Exception e) {
		}

	}

	public void setDataProvince(JSONObject jSonInfo) throws JSONException {
		this.setProvinceID(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "ProvinceName"));
		this.setIsEnable(VrealUtilities.getDataBool(jSonInfo, "IsEnable"));
		this.setProvinceCode(VrealUtilities.getDataString(jSonInfo, "ProvinceCode"));
		setChoosen(false);
		// this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo,
		// "shipping_cost"));
		// this.setQuantity(HotdealUtilities.getDataString(jSonInfo,
		// "quantity"));
		// this.setDiscounts(HotdealUtilities.getDataString(jSonInfo,
		// "discounts"));

	}

	public void setDataDistrict(JSONObject jSonInfo) throws JSONException {
		this.setProvinceID(VrealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "DistrictName"));
		this.setIsEnable(VrealUtilities.getDataBool(jSonInfo, "IsEnable"));
		setChoosen(false);

	}

	public void setDataWard(JSONObject jSonInfo) throws JSONException {
		this.setWardID(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceID(VrealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(VrealUtilities.getDataString(jSonInfo, "DistrictID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "WardName"));
		this.setIsEnable(VrealUtilities.getDataBool(jSonInfo, "IsEnable"));
		setChoosen(false);

	}

	public void setDataAREA(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceID(VrealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(VrealUtilities.getDataString(jSonInfo, "DistrictID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "AreaName"));
		this.setIsEnable(VrealUtilities.getDataBool(jSonInfo, "IsEnable"));
		setChoosen(false);

	}

	public void setDataStreet(JSONObject jSonInfo) throws JSONException {
		this.setStreetID(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "StreetName"));
		this.setIsEnable(VrealUtilities.getDataBool(jSonInfo, "IsEnable"));
		this.setProvinceID(VrealUtilities.getDataString(jSonInfo, "ProvinceID"));
		this.setDistrictID(VrealUtilities.getDataString(jSonInfo, "DistrictID"));
		setChoosen(false);

	}

	public void setTypeProperty(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "TypeName"));
		setChoosen(false);

	}

	public void setHuong(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "DirectionName"));
		setChoosen(false);

	}

	public void setDuan(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setLoaiDuan(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setTienich(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setGia(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setDIENTICH(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "Name"));
		setChoosen(false);

	}

	public void setListSub(JSONObject jSonInfo) throws JSONException {
		setChoosen(false);

	}

	public void setLoaiNhaDat(JSONObject jSonInfo) throws JSONException {
		this.setId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setProvinceName(VrealUtilities.getDataString(jSonInfo, "RealsCateName"));
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}

	public ArrayList<String> getListPhoto() {
		return listPhoto;
	}

	public void setListPhoto(ArrayList<String> listPhoto) {
		this.listPhoto = listPhoto;
	}

	public String getAcreage() {
		return Acreage;
	}

	public void setAcreage(String acreage) {
		Acreage = acreage;
	}

	public String getContactName() {
		return ContactName;
	}

	public void setContactName(String contactName) {
		ContactName = contactName;
	}

	public String getContactAddress() {
		return ContactAddress;
	}

	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}

	public String getContacPhone() {
		return ContacPhone;
	}

	public void setContacPhone(String contacPhone) {
		ContacPhone = contacPhone;
	}

	public String getContactEmail() {
		return ContactEmail;
	}

	public void setContactEmail(String contactEmail) {
		ContactEmail = contactEmail;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public ArrayList<String> getListTienIch() {
		return listTienIch;
	}

	public void setListTienIch(ArrayList<String> listTienIch) {
		this.listTienIch = listTienIch;
	}

	public String getDeveloper() {
		return Developer;
	}

	public void setDeveloper(String developer) {
		Developer = developer;
	}

	public String getPublishStart() {
		return PublishStart;
	}

	public void setPublishStart(String publishStart) {
		PublishStart = publishStart;
	}

	public String getPublishEnd() {
		return PublishEnd;
	}

	public void setPublishEnd(String publishEnd) {
		PublishEnd = publishEnd;
	}

	public int getRealNewsTypeID() {
		return RealNewsTypeID;
	}

	public void setRealNewsTypeID(int realNewsTypeID) {
		RealNewsTypeID = realNewsTypeID;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String unitName) {
		UnitName = unitName;
	}


	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public int getAreaID() {
		return AreaID;
	}

	public void setAreaID(int areaID) {
		AreaID = areaID;
	}

}