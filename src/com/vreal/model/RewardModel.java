package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class RewardModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderID;
	private String date;
	private String price;

	public RewardModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
	
		this.setDate(HotdealUtilities.getDataString(jSonInfo, "timestamp"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "amount"));
		try {
			JSONObject resultData = jSonInfo.getJSONObject("order_detail");
			this.setOrderID(HotdealUtilities.getDataString(resultData, "order_id"));
		} catch (Exception e) {
			e.printStackTrace();
			this.setOrderID("false");
		}
	
	
		
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}