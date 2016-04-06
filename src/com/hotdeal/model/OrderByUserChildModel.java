package com.hotdeal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class OrderByUserChildModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nameUrl;
	private String price;
	private String image;
	private String id;

	// Evoucher
	private String codeEvoucher;
	private String sudung;
	private String exprite_time;

	public OrderByUserChildModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setNameUrl(HotdealUtilities.getDataString(jSonInfo, "nameUrl"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "price"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setId(HotdealUtilities.getDataString(jSonInfo, "product_id"));
	}

	public void setDataDetail(JSONObject jSonInfo) throws JSONException {
		this.setNameUrl(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "price"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setId(HotdealUtilities.getDataString(jSonInfo, "product_id"));
	}

	public void setDataEV(JSONObject jSonInfo) throws JSONException {
		this.setNameUrl(HotdealUtilities.getDataString(jSonInfo, "shortname"));
		this.setCodeEvoucher(HotdealUtilities.getDataString(jSonInfo, "voucher_code"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setSudung(HotdealUtilities.getDataString(jSonInfo, "used"));
		this.setExprite_time(HotdealUtilities.getDataString(jSonInfo, "expire_time"));
		this.setId(HotdealUtilities.getDataString(jSonInfo, "product_id"));
	}

	/**
	 * @return the nameUrl
	 */
	public String getNameUrl() {
		return nameUrl;
	}

	/**
	 * @param nameUrl
	 *            the nameUrl to set
	 */
	public void setNameUrl(String nameUrl) {
		this.nameUrl = nameUrl;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public String getCodeEvoucher() {
		return codeEvoucher;
	}

	public void setCodeEvoucher(String codeEvoucher) {
		this.codeEvoucher = codeEvoucher;
	}

	public String getSudung() {
		return sudung;
	}

	private void setSudung(String sudung) {
		this.sudung = sudung;
	}

	public String getExprite_time() {
		return exprite_time;
	}

	private void setExprite_time(String exprite_time) {
		this.exprite_time = exprite_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}