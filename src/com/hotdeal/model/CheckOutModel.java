package com.hotdeal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class CheckOutModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String shipping_id;
	private String payment_id;
	private String order_id;
	private ArrayList<PaymentModel> listPayment;
	private ArrayList<PaymentModel> listShipping;
	private String subtotal;
	private String total;
	private String shipping_cost;
	private String quantity;
	private String discounts;

	public CheckOutModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setId(HotdealUtilities.getDataString(jSonInfo, "id"));
		// this.setShipping_id(HotdealUtilities.getDataString(jSonInfo,
		// "shipping_id"));
		// this.setPayment_id(HotdealUtilities.getDataString(jSonInfo,
		// "payment_id"));
		this.setOrder_id(HotdealUtilities.getDataString(jSonInfo, "order_id"));

		this.setSubtotal(HotdealUtilities.getDataString(jSonInfo, "subtotal"));
		this.setTotal(HotdealUtilities.getDataString(jSonInfo, "total"));
		this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo, "shipping_cost"));
		this.setQuantity(HotdealUtilities.getDataString(jSonInfo, "quantity"));
		this.setDiscounts(HotdealUtilities.getDataString(jSonInfo, "discounts"));

	}

	public void setDataPayment(JSONArray jSonInfo) throws JSONException {
		setListPayment(new ArrayList<PaymentModel>());
		try {
			getListPayment().clear();
			for (int i = 0; i < jSonInfo.length(); i++) {
				PaymentModel md = new PaymentModel();
				JSONObject ob = jSonInfo.getJSONObject(i);
				md.setData(ob);
				if (i == 0) {
					md.setCheck(true);
				} else {
					md.setCheck(false);
				}
				getListPayment().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public void setDataShipping(JSONArray jSonInfo) throws JSONException {
		setListShipping(new ArrayList<PaymentModel>());
		try {
			getListShipping().clear();
			for (int i = 0; i < jSonInfo.length(); i++) {
				PaymentModel md = new PaymentModel();
				JSONObject ob = jSonInfo.getJSONObject(i);
				md.setDataShippingMethod(ob);
				if (i == 0) {
					md.setCheck(true);
				} else {
					md.setCheck(false);
				}
				getListShipping().add(md);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(String shipping_id) {
		this.shipping_id = shipping_id;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public ArrayList<PaymentModel> getListPayment() {
		return listPayment;
	}

	public void setListPayment(ArrayList<PaymentModel> listPayment) {
		this.listPayment = listPayment;
	}

	public ArrayList<PaymentModel> getListShipping() {
		return listShipping;
	}

	public void setListShipping(ArrayList<PaymentModel> listShipping) {
		this.listShipping = listShipping;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

}