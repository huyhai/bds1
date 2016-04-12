package com.hotdeal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class DiaDiemModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private double lont;
	private double latt;
	private boolean click;
	

	public DiaDiemModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		 this.setName(HotdealUtilities.getDataString(jSonInfo, "partnerName"));
		this.setAddress(HotdealUtilities.getDataString(jSonInfo, "partnerAddress"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "partnerPhone"));
		try {
			setLont(jSonInfo.getDouble("partnerLng"));
			setLatt(jSonInfo.getDouble("partnerLat"));
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	public String getName() {
		return name;
	}

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
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the click
	 */
	public boolean isClick() {
		return click;
	}

	/**
	 * @param click the click to set
	 */
	public void setClick(boolean click) {
		this.click = click;
	}

	public double getLont() {
		return lont;
	}

	public void setLont(double lont) {
		this.lont = lont;
	}

	public double getLatt() {
		return latt;
	}

	public void setLatt(double latt) {
		this.latt = latt;
	}

}