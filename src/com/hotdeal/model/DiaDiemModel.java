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
	private String lont;
	private String latt;
	private boolean click;
	

	public DiaDiemModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		 this.setName(HotdealUtilities.getDataString(jSonInfo, "partnerName"));
		this.setAddress(HotdealUtilities.getDataString(jSonInfo, "partnerAddress"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "partnerPhone"));
		this.setLont(HotdealUtilities.getDataString(jSonInfo, "partnerLng"));
		this.setLatt(HotdealUtilities.getDataString(jSonInfo, "partnerLat"));

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
	 * @return the lont
	 */
	public String getLont() {
		return lont;
	}

	/**
	 * @param lont
	 *            the lont to set
	 */
	public void setLont(String lont) {
		this.lont = lont;
	}

	/**
	 * @return the latt
	 */
	public String getLatt() {
		return latt;
	}

	/**
	 * @param latt
	 *            the latt to set
	 */
	public void setLatt(String latt) {
		this.latt = latt;
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

}