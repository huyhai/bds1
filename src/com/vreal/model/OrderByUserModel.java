package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;


public class OrderByUserModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String order_id;
	private String timestamp;
	private String total;
	private String status;
	private ArrayList<OrderByUserChildModel> orderDetails;

	//OrderDetail
	private String subtotal;
	private String notes;
	private String firstname;
	private String s_address;
	private String phone;
	private String email;
	private String payment_mess;
	private String shipping_mess;
	private String shipping_cost;
	private String discount;
	private String street;
	private String ward;
	private String dis;
	private String city;
	private String money;

	public OrderByUserModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setOrder_id(HotdealUtilities.getDataString(jSonInfo, "order_id"));
		this.setTimestamp(HotdealUtilities.getDataString(jSonInfo, "timestamp"));
		this.setTotal(HotdealUtilities.getDataString(jSonInfo, "total"));
		this.setStatus(HotdealUtilities.getDataString(jSonInfo, "status"));
		this.setMoney(HotdealUtilities.getDataString(jSonInfo, "money"));
		setOrderDetails(new ArrayList<OrderByUserChildModel>());
		JSONArray listJson;
		listJson = jSonInfo.getJSONArray("orderDetails");
		getOrderDetails().clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			OrderByUserChildModel md=new OrderByUserChildModel();
			md.setData(jSonOb);
			getOrderDetails().add(md);
		}
	}
	public void setDataDetail(JSONObject jSonInfo) throws JSONException {
		this.setOrder_id(HotdealUtilities.getDataString(jSonInfo, "order_id"));
		this.setTimestamp(HotdealUtilities.getDataString(jSonInfo, "timestamp"));
		this.setTotal(HotdealUtilities.getDataString(jSonInfo, "total"));
		this.setStatus(HotdealUtilities.getDataString(jSonInfo, "status"));
		
		this.setShipping_mess(HotdealUtilities.getDataString(jSonInfo, "shipping_mess"));
		this.setPayment_mess(HotdealUtilities.getDataString(jSonInfo, "payment_mess"));
		this.setEmail(HotdealUtilities.getDataString(jSonInfo, "email"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "phone"));
		this.setS_address(HotdealUtilities.getDataString(jSonInfo, "s_address"));
		this.setFirstname(HotdealUtilities.getDataString(jSonInfo, "firstname"));
		this.setNotes(HotdealUtilities.getDataString(jSonInfo, "notes"));
		this.setSubtotal(HotdealUtilities.getDataString(jSonInfo, "subtotal"));
		this.setShipping_cost(HotdealUtilities.getDataString(jSonInfo, "shipping_cost"));
		this.setDiscount(HotdealUtilities.getDataString(jSonInfo, "discount"));
		
		this.setStreet(HotdealUtilities.getDataString(jSonInfo, "s_address_2"));
		this.setWard(HotdealUtilities.getDataString(jSonInfo, "ward_name"));
		this.setDis(HotdealUtilities.getDataString(jSonInfo, "district_name"));
		this.setCity(HotdealUtilities.getDataString(jSonInfo, "state_name"));
		this.setMoney(HotdealUtilities.getDataString(jSonInfo, "money"));
		
		setOrderDetails(new ArrayList<OrderByUserChildModel>());
		JSONArray listJson;
		listJson = jSonInfo.getJSONArray("order_detail");
		getOrderDetails().clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			OrderByUserChildModel md=new OrderByUserChildModel();
			md.setDataDetail(jSonOb);
			getOrderDetails().add(md);
		}
	}

	/**
	 * @return the order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<OrderByUserChildModel> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(ArrayList<OrderByUserChildModel> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayment_mess() {
		return payment_mess;
	}

	public void setPayment_mess(String payment_mess) {
		this.payment_mess = payment_mess;
	}

	public String getShipping_mess() {
		return shipping_mess;
	}

	public void setShipping_mess(String shipping_mess) {
		this.shipping_mess = shipping_mess;
	}

	public String getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(String shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}


}