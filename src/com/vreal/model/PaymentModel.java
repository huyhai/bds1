package com.vreal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class PaymentModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String payment_id;
	private String payment_name;
	private String fee_cost;
	private boolean isCheck;

	public PaymentModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setPayment_id(HotdealUtilities.getDataString(jSonInfo, "payment_id"));
		this.setPayment_name(HotdealUtilities.getDataString(jSonInfo, "payment_name"));
		this.setFee_cost(HotdealUtilities.getDataString(jSonInfo, "payment_cost"));
	}

	public void setDataShippingMethod(JSONObject jSonInfo) throws JSONException {
		this.setPayment_id(HotdealUtilities.getDataString(jSonInfo, "shipping_id"));
		this.setPayment_name(HotdealUtilities.getDataString(jSonInfo, "shipping_name"));
		this.setFee_cost(HotdealUtilities.getDataString(jSonInfo, "shipping_cost"));
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_name() {
		return payment_name;
	}

	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}

	public String getFee_cost() {
		return fee_cost;
	}

	public void setFee_cost(String fee_cost) {
		this.fee_cost = fee_cost;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

}