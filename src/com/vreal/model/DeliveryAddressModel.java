package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;


public class DeliveryAddressModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String street;
	private String block;
	private String phone;
	private String city;
	private String distric;
	private String ward;
	
	private String cityID;
	private String disID;
	private String wardID;
	private String userProfileId="0";
	private String address_type="C";

	public DeliveryAddressModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setAddress(HotdealUtilities.getDataString(jSonInfo, "address"));
		this.setStreet(HotdealUtilities.getDataString(jSonInfo, "street"));
		this.setBlock(HotdealUtilities.getDataString(jSonInfo, "address_note"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "phone"));
		this.setCity(HotdealUtilities.getDataString(jSonInfo, "city"));
		this.setDistric(HotdealUtilities.getDataString(jSonInfo, "district"));
		this.setWard(HotdealUtilities.getDataString(jSonInfo, "ward"));
		
		this.setCityID(HotdealUtilities.getDataString(jSonInfo, "city_id"));
		this.setDisID(HotdealUtilities.getDataString(jSonInfo, "district_id"));
		this.setWardID(HotdealUtilities.getDataString(jSonInfo, "ward_id"));
		this.setUserProfileId(HotdealUtilities.getDataString(jSonInfo, "id"));
		this.setAddress_type(HotdealUtilities.getDataString(jSonInfo, "address_type"));
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the distric
	 */
	public String getDistric() {
		return distric;
	}

	/**
	 * @param distric the distric to set
	 */
	public void setDistric(String distric) {
		this.distric = distric;
	}

	/**
	 * @return the ward
	 */
	public String getWard() {
		return ward;
	}

	/**
	 * @param ward the ward to set
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}

	/**
	 * @return the cityID
	 */
	public String getCityID() {
		return cityID;
	}

	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	/**
	 * @return the disID
	 */
	public String getDisID() {
		return disID;
	}

	/**
	 * @param disID the disID to set
	 */
	public void setDisID(String disID) {
		this.disID = disID;
	}

	/**
	 * @return the wardID
	 */
	public String getWardID() {
		return wardID;
	}

	/**
	 * @param wardID the wardID to set
	 */
	public void setWardID(String wardID) {
		this.wardID = wardID;
	}

	/**
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the address_type
	 */
	public String getAddress_type() {
		return address_type;
	}

	/**
	 * @param address_type the address_type to set
	 */
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}


}