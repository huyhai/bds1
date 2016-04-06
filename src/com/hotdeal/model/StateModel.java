package com.hotdeal.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hotdeal.libs.HotdealUtilities;

public class StateModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String stateID;
	private String stateName;
	private String sort;
	private boolean check;
	private int image;

	public StateModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "state_id"));
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "state"));
	}

	public void setDataDis(JSONObject jSonInfo) throws JSONException {
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "district_name"));
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "district_id"));
	}

	public void setDataWard(JSONObject jSonInfo) throws JSONException {
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "ward_name"));
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "ward_id"));
	}

	public void setDataAboutRoot(JSONObject jSonInfo) throws JSONException {
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "pageId"));
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "name"));
	}
	public void setDataAboutRoot3(JSONObject jSonInfo) throws JSONException {
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "pageId"));
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "description"));
	}
	public void setDataTabs(JSONObject jSonInfo) throws JSONException {
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "tab"));
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "name"));
	}
	public void setDataSort(JSONObject jSonInfo) throws JSONException {
		this.setStateID(HotdealUtilities.getDataString(jSonInfo, "field"));
		this.setStateName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setSort(HotdealUtilities.getDataString(jSonInfo, "sort"));
		setCheck(false);
	}


	/**
	 * @return the stateID
	 */
	public String getStateID() {
		return stateID;
	}

	/**
	 * @param stateID
	 *            the stateID to set
	 */
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

}