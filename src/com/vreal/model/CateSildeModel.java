package com.vreal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vreal.libs.HotdealUtilities;

public class CateSildeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cateID;
	private String name;
	private int image;
	private int background;
	private boolean isThem;
	private ArrayList<CateSildeSubModel> listSub;

	public CateSildeModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setCateID(HotdealUtilities.getDataString(jSonInfo, "categoryId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		setThem(false);
		setListSub(new ArrayList<CateSildeSubModel>());
		JSONArray listJson;
		listJson = jSonInfo.getJSONArray("listSubCategory");
		listSub.clear();
		for (int i = 0; i < listJson.length(); i++) {
			JSONObject jSonOb = new JSONObject();
			jSonOb = listJson.getJSONObject(i);
			CateSildeSubModel md = new CateSildeSubModel();
			md.setData(jSonOb);
//			if (!md.getName().equals("null"))
				getListSub().add(md);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCateID() {
		return cateID;
	}

	public void setCateID(String cateID) {
		this.cateID = cateID;
	}

	public ArrayList<CateSildeSubModel> getListSub() {
		if(null!=listSub){
			return listSub;
		}else{
			return listSub=new ArrayList<CateSildeSubModel>();
		}
		
	}

	public void setListSub(ArrayList<CateSildeSubModel> listSub) {
		this.listSub = listSub;
	}

	/**
	 * @return the image
	 */
	public int getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(int image) {
		this.image = image;
	}

	public boolean isThem() {
		return isThem;
	}

	public void setThem(boolean isThem) {
		this.isThem = isThem;
	}

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

}