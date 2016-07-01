package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.vreal.libs.VrealUtilities;

public class DetailsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int ITEM = 0;
	public static final int SECTION = 1;
	private String id;
	private String productId;
	private String name;
	private String description;
	private String introduce;
	private String conditions;
	private int NewsTypeID;
	private String image;

	public DetailsModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setProductId(VrealUtilities.getDataString(jSonInfo, "ID"));
		this.setName(VrealUtilities.getDataString(jSonInfo, "Title"));
		this.setDescription(VrealUtilities.getDataString(jSonInfo, "Content"));
		this.setIntroduce(VrealUtilities.getDataString(jSonInfo, "CreatedDate"));
		this.setConditions(VrealUtilities.getDataString(jSonInfo, "Summary"));
		this.setNewsTypeID(VrealUtilities.getDataInt(jSonInfo, "NewsTypeID"));
		this.setImage(VrealUtilities.getDataString(jSonInfo, "Icon"));

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the conditions
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 *            the conditions to set
	 */
	private void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNewsTypeID() {
		return NewsTypeID;
	}

	public void setNewsTypeID(int newsTypeID) {
		NewsTypeID = newsTypeID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}