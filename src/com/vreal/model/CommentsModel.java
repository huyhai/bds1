package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;

public class CommentsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String commenterName;
	private String timestamp;
	
	private String message;
	private String ratingValue;
	private String hasChild;
	private Object tagGroup;
	private String parentId;
	private ArrayList<CommentsModel> listReply;

	public CommentsModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setCommenterName(HotdealUtilities.getDataString(jSonInfo, "commenterName"));
		this.setTimestamp(HotdealUtilities.getDataString(jSonInfo, "timestamp"));
		this.setMessage(HotdealUtilities.getDataString(jSonInfo, "message"));
		this.setRatingValue(HotdealUtilities.getDataString(jSonInfo, "ratingValue"));
		this.setHasChild(HotdealUtilities.getDataString(jSonInfo, "hasChild"));
		this.setParentId(HotdealUtilities.getDataString(jSonInfo, "parentId"));
		setListReply(new ArrayList<CommentsModel>());
		if(getHasChild().equals(ConstantValue.YES)){
			JSONArray listJson;
			listJson = jSonInfo.getJSONArray("reply");
			listReply.clear();
			for (int i = 0; i < listJson.length(); i++) {
				JSONObject jSonOb = new JSONObject();
				jSonOb = listJson.getJSONObject(i);
				CommentsModel md=new CommentsModel();
				md.setData(jSonOb);
				getListReply().add(md);
			}
		}


	}

	/**
	 * @return the commenterName
	 */
	public String getCommenterName() {
		return commenterName;
	}

	/**
	 * @param commenterName the commenterName to set
	 */
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the ratingValue
	 */
	public String getRatingValue() {
		return ratingValue;
	}

	/**
	 * @param ratingValue the ratingValue to set
	 */
	public void setRatingValue(String ratingValue) {
		this.ratingValue = ratingValue;
	}

	/**
	 * @return the hasChild
	 */
	public String getHasChild() {
		return hasChild;
	}

	/**
	 * @param hasChild the hasChild to set
	 */
	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * @return the listReply
	 */
	public ArrayList<CommentsModel> getListReply() {
		return listReply;
	}

	/**
	 * @param listReply the listReply to set
	 */
	public void setListReply(ArrayList<CommentsModel> listReply) {
		this.listReply = listReply;
	}

	/**
	 * @return the tagGroup
	 */
	public Object getTagGroup() {
		return tagGroup;
	}

	/**
	 * @param tagGroup the tagGroup to set
	 */
	public void setTagGroup(Object tagGroup) {
		this.tagGroup = tagGroup;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


}