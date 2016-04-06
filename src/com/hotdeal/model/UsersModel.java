package com.hotdeal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;


public class UsersModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String userID;
	private String user_login;
	private String email;
	private String phone;
	private String delivery_saturday;
	private String sex;
	private String birthday;

	public UsersModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setUserID(HotdealUtilities.getDataString(jSonInfo, "user_id"));
		this.setUsername(HotdealUtilities.getDataString(jSonInfo, "firstname"));
		this.setUser_login(HotdealUtilities.getDataString(jSonInfo, "user_login"));
		this.setEmail(HotdealUtilities.getDataString(jSonInfo, "email"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "phone"));
		this.setDelivery_saturday(HotdealUtilities.getDataString(jSonInfo, "delivery_saturday"));
		
		this.setSex(HotdealUtilities.getDataString(jSonInfo, "gender"));
		this.setBirthday(HotdealUtilities.getDataString(jSonInfo, "birthday"));
	}
	public void setDataUpdate(JSONObject jSonInfo) throws JSONException {
		this.setUsername(HotdealUtilities.getDataString(jSonInfo, "firstname"));
		this.setPhone(HotdealUtilities.getDataString(jSonInfo, "phone"));
		this.setSex(HotdealUtilities.getDataString(jSonInfo, "gender"));
		this.setBirthday(HotdealUtilities.getDataString(jSonInfo, "birthday"));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the user_login
	 */
	public String getUser_login() {
		return user_login;
	}

	/**
	 * @param user_login the user_login to set
	 */
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the delivery_saturday
	 */
	public String getDelivery_saturday() {
		return delivery_saturday;
	}

	/**
	 * @param delivery_saturday the delivery_saturday to set
	 */
	public void setDelivery_saturday(String delivery_saturday) {
		this.delivery_saturday = delivery_saturday;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}




}