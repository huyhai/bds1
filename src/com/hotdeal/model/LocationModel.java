package com.hotdeal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;


public class LocationModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private boolean check;
	private String phone;

	public LocationModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setName(HotdealUtilities.getDataString(jSonInfo, "stateName"));
		this.setId(HotdealUtilities.getDataString(jSonInfo, "stateId"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "phoneSupport"));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the check
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(boolean check) {
		this.check = check;
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





}