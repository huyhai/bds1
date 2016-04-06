package com.hotdeal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class PaymentOrderModel implements Serializable {
	private String reward_point="0";
	private String total;
	private String shipping_cost="0";
	private String discounts="0";
	private String quantity;
	private String subtotal="0";

	public  PaymentOrderModel() {
		// TODO Auto-generated constructor stub
	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setReward_point(HotdealUtilities.getDataString(jSonInfo, "reward_point"));
		this.setTotal(HotdealUtilities.getDataString(jSonInfo, "total"));
		this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo, "shipping_cost"));
		this.setDiscounts(HotdealUtilities.getDataString(jSonInfo, "discounts"));
		this.setQuantity(HotdealUtilities.getDataString(jSonInfo, "quantity"));
		this.setSubtotal(HotdealUtilities.getDataString(jSonInfo, "subtotal"));
	}

	public String getReward_point() {
		return reward_point;
	}

	public void setReward_point(String reward_point) {
		this.reward_point = reward_point;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(String shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public String getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

}
