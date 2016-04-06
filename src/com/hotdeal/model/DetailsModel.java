package com.hotdeal.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.hotdeal.libs.HotdealUtilities;

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
	private String listPrice;
	private String price;
	private String discountValue;
	private ArrayList<String> listSlides = new ArrayList<String>();
	private String rateTotal;
	private String rateVal;
	private String nowNumber;
	private String address;
	private String image;
	private String total_comment;
	private String productKind;
	private String evoucherSelected;
	private String link;
	//SKU
	private String sellEnd;
	private String maxQty;
	private String minQty;
	private String skuOptionSelected;
	private String productType;
	private boolean isShow;
//	private String sellEnd;
//	private String sellEnd;
	
	//user action
	private int quantityUserChoosen;
	private int yesgoAdults;
	private int yesgoChilds;
	private long ngayNhan=0;
	private long ngayTra=0;
//	public static int DINHNGAY_COa=1;
//	public static int DINHNGAY_KHONG=0;
	private boolean isChonNgay;
	private String nameBooked="";
	private String emailBooked="";
	private String phoneBooled="";
	private String noteBooked="";

	private ArrayList<DiaDiemModel> listDiaDiem;
	private ArrayList<CommentsModel> listComment;
	private int type;
	private boolean setImage=false;

	public DetailsModel() {

	}

	public void setData(JSONObject jSonInfo) throws JSONException {
		this.setProductId(HotdealUtilities.getDataString(jSonInfo, "productId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setDescription(HotdealUtilities.getDataString(jSonInfo, "description"));
		this.setIntroduce(HotdealUtilities.getDataString(jSonInfo, "introduce"));
		this.setConditions(HotdealUtilities.getDataString(jSonInfo, "conditions"));
		this.setListPrice(HotdealUtilities.getDataString(jSonInfo, "listPrice"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "price"));
		this.setDiscountValue(HotdealUtilities.getDataString(jSonInfo, "discountValue"));
		this.setRateTotal(HotdealUtilities.getDataString(jSonInfo, "rateTotal"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setRateVal(HotdealUtilities.getDataString(jSonInfo, "rateVal"));
		this.setNowNumber(HotdealUtilities.getDataString(jSonInfo, "nowNumber"));
		this.setTotal_comment(HotdealUtilities.getDataString(jSonInfo, "total_comments"));
		this.setEvoucherSelected(HotdealUtilities.getDataString(jSonInfo, "evoucherSelected"));
		this.setProductKind(HotdealUtilities.getDataString(jSonInfo, "productKind"));
		this.setLink(HotdealUtilities.getDataString(jSonInfo, "link"));
		this.setShow("1".equals(HotdealUtilities.getDataString(jSonInfo, "isShow")));

		setListSlides(new ArrayList<String>());
		JSONArray listJson;
		try {
			listJson = jSonInfo.getJSONArray("imageSlides");
			getListSlides().clear();
			for (int i = 0; i < listJson.length(); i++) {
				String jSonOb = listJson.getString(i);
				getListSlides().add(jSonOb);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		JSONArray listJsonAddress;
		boolean isFirst=false;
		try {
			listJsonAddress = jSonInfo.getJSONArray("address");
			setListDiaDiem(new ArrayList<DiaDiemModel>());
			for(int i=0;i<listJsonAddress.length();i++){
				JSONObject obAdd = listJsonAddress.getJSONObject(i);
				DiaDiemModel md = new DiaDiemModel();
				md.setData(obAdd);
				if(!isFirst){
					md.setClick(true);
					isFirst=true;
				}
				getListDiaDiem().add(md);
			}
		
		} catch (Exception e) {
//			 e.printStackTrace();
		}
		
		JSONArray listJsonComment;
		try {
			listJsonComment = jSonInfo.getJSONArray("comments");
			setListComment(new ArrayList<CommentsModel>());
		
			for(int i=0;i<listJsonComment.length();i++){
				JSONObject obAdd = listJsonComment.getJSONObject(i);
				CommentsModel md = new CommentsModel();
				md.setData(obAdd);
				getListComment().add(md);
			}
		} catch (Exception e) {
//			 e.printStackTrace();
		}

	}
	
	private ArrayList<String> listFilter;
	private ArrayList<DetailsModel> productChilds;
	
	public void setDataSKU(JSONObject jSonInfo) throws JSONException {
		this.setProductId(HotdealUtilities.getDataString(jSonInfo, "productId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setListPrice(HotdealUtilities.getDataString(jSonInfo, "listPrice"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "price"));
		this.setDiscountValue(HotdealUtilities.getDataString(jSonInfo, "discountValue"));
		this.setSellEnd(HotdealUtilities.getDataString(jSonInfo, "sellEnd"));
		this.setMaxQty(HotdealUtilities.getDataString(jSonInfo, "maxQty"));
		this.setMinQty(HotdealUtilities.getDataString(jSonInfo, "minQty"));
		this.setProductType(HotdealUtilities.getDataString(jSonInfo, "productType"));
		this.setShow("1".equals(HotdealUtilities.getDataString(jSonInfo, "isShow")));
		try {
			setQuantityUserChoosen(Integer.parseInt(getMinQty()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		HotdealUtilities.showALog(isShow()+"");

		setListFilter(new ArrayList<String>());
		JSONArray listJson;
		try {
			listJson = jSonInfo.getJSONArray("listFilter");
			getListFilter().clear();
			for (int i = 0; i < listJson.length(); i++) {
				String jSonOb = listJson.getString(i);
				getListFilter().add(jSonOb);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		
		JSONArray listJsonComment;
		try {
			listJsonComment = jSonInfo.getJSONArray("productChilds");
			setProductChilds(new ArrayList<DetailsModel>());
		
			for(int i=0;i<listJsonComment.length();i++){
				JSONObject obAdd = listJsonComment.getJSONObject(i);
				DetailsModel md = new DetailsModel();
				md.setDataSKUChild(obAdd);
				getProductChilds().add(md);
			}
		} catch (Exception e) {
//			 e.printStackTrace();
		}

	}
	public void setDataSKUChild(JSONObject jSonInfo) throws JSONException {
		this.setProductId(HotdealUtilities.getDataString(jSonInfo, "productId"));
		this.setName(HotdealUtilities.getDataString(jSonInfo, "name"));
		this.setListPrice(HotdealUtilities.getDataString(jSonInfo, "listPrice"));
		this.setPrice(HotdealUtilities.getDataString(jSonInfo, "price"));
		this.setDiscountValue(HotdealUtilities.getDataString(jSonInfo, "discountValue"));
		this.setSellEnd(HotdealUtilities.getDataString(jSonInfo, "sellEnd"));
		this.setMaxQty(HotdealUtilities.getDataString(jSonInfo, "maxQty"));
		this.setMinQty(HotdealUtilities.getDataString(jSonInfo, "minQty"));
		this.setImage(HotdealUtilities.getDataString(jSonInfo, "image"));
		this.setSkuOptionSelected(HotdealUtilities.getDataString(jSonInfo, "skuOptionSelected"));
		this.setProductType(HotdealUtilities.getDataString(jSonInfo, "productType"));
		this.setShow("1".equals(HotdealUtilities.getDataString(jSonInfo, "isShow")));
//		try {
//			setQuantityUserChoosen(Integer.parseInt(getMinQty()));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		HotdealUtilities.showALog(isShow()+" child");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// /**
	// * @return the listDeal
	// */
	// public ArrayList<DealHomeItemModel> getListDeal() {
	// return listDeal;
	// }
	//
	// /**
	// * @param listDeal
	// * the listDeal to set
	// */
	// public void setListDeal(ArrayList<DealHomeItemModel> listDeal) {
	// this.listDeal = listDeal;
	// }

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

	/**
	 * @return the listPrice
	 */
	public String getListPrice() {
		return listPrice;
	}

	/**
	 * @param listPrice
	 *            the listPrice to set
	 */
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the discountValue
	 */
	public String getDiscountValue() {
		return discountValue;
	}

	/**
	 * @param discountValue
	 *            the discountValue to set
	 */
	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	/**
	 * @return the listSlides
	 */
	public ArrayList<String> getListSlides() {
		return listSlides;
	}

	/**
	 * @param listSlides
	 *            the listSlides to set
	 */
	public void setListSlides(ArrayList<String> listSlides) {
		this.listSlides = listSlides;
	}

	/**
	 * @return the rateTotal
	 */
	public String getRateTotal() {
		return rateTotal;
	}

	/**
	 * @param rateTotal
	 *            the rateTotal to set
	 */
	public void setRateTotal(String rateTotal) {
		this.rateTotal = rateTotal;
	}

	/**
	 * @return the rateVal
	 */
	public String getRateVal() {
		return rateVal;
	}

	/**
	 * @param rateVal
	 *            the rateVal to set
	 */
	public void setRateVal(String rateVal) {
		this.rateVal = rateVal;
	}

	/**
	 * @return the nowNumber
	 */
	public String getNowNumber() {
		return nowNumber;
	}

	/**
	 * @param nowNumber
	 *            the nowNumber to set
	 */
	public void setNowNumber(String nowNumber) {
		this.nowNumber = nowNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the listDiaDiem
	 */
	public ArrayList<DiaDiemModel> getListDiaDiem() {
		return listDiaDiem;
	}

	/**
	 * @param listDiaDiem
	 *            the listDiaDiem to set
	 */
	public void setListDiaDiem(ArrayList<DiaDiemModel> listDiaDiem) {
		this.listDiaDiem = listDiaDiem;
	}

	/**
	 * @return the total_comment
	 */
	public String getTotal_comment() {
		return total_comment;
	}

	/**
	 * @param total_comment the total_comment to set
	 */
	public void setTotal_comment(String total_comment) {
		this.total_comment = total_comment;
	}

	/**
	 * @return the listComment
	 */
	public ArrayList<CommentsModel> getListComment() {
		return listComment;
	}

	/**
	 * @param listComment the listComment to set
	 */
	public void setListComment(ArrayList<CommentsModel> listComment) {
		this.listComment = listComment;
	}

	/**
	 * @return the evoucherSelected
	 */
	public String getEvoucherSelected() {
		return evoucherSelected;
	}

	/**
	 * @param evoucherSelected the evoucherSelected to set
	 */
	public void setEvoucherSelected(String evoucherSelected) {
		this.evoucherSelected = evoucherSelected;
	}

	/**
	 * @return the productKind
	 */
	public String getProductKind() {
		return productKind;
	}

	/**
	 * @param productKind the productKind to set
	 */
	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}

	/**
	 * @return the sellEnd
	 */
	public String getSellEnd() {
		return sellEnd;
	}

	/**
	 * @param sellEnd the sellEnd to set
	 */
	public void setSellEnd(String sellEnd) {
		this.sellEnd = sellEnd;
	}

	/**
	 * @return the maxQty
	 */
	public String getMaxQty() {
		return maxQty;
	}

	/**
	 * @param maxQty the maxQty to set
	 */
	public void setMaxQty(String maxQty) {
		this.maxQty = maxQty;
	}

	/**
	 * @return the listFilter
	 */
	public ArrayList<String> getListFilter() {
		return listFilter;
	}

	/**
	 * @param listFilter the listFilter to set
	 */
	public void setListFilter(ArrayList<String> listFilter) {
		this.listFilter = listFilter;
	}

	/**
	 * @return the productChilds
	 */
	public ArrayList<DetailsModel> getProductChilds() {
		return productChilds;
	}

	/**
	 * @param productChilds the productChilds to set
	 */
	public void setProductChilds(ArrayList<DetailsModel> productChilds) {
		this.productChilds = productChilds;
	}

	/**
	 * @return the skuOptionSelected
	 */
	public String getSkuOptionSelected() {
		return skuOptionSelected;
	}

	/**
	 * @param skuOptionSelected the skuOptionSelected to set
	 */
	public void setSkuOptionSelected(String skuOptionSelected) {
		this.skuOptionSelected = skuOptionSelected;
	}

	/**
	 * @return the quantityUserChoosen
	 */
	public int getQuantityUserChoosen() {
		return quantityUserChoosen;
	}

	/**
	 * @param quantityUserChoosen the quantityUserChoosen to set
	 */
	public void setQuantityUserChoosen(int quantityUserChoosen) {
		this.quantityUserChoosen = quantityUserChoosen;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}


	/**
	 * @return the isChonNgay
	 */
	public boolean isChonNgay() {
		return isChonNgay;
	}

	/**
	 * @param isChonNgay the isChonNgay to set
	 */
	public void setChonNgay(boolean isChonNgay) {
		this.isChonNgay = isChonNgay;
	}

	/**
	 * @return the ngayTra
	 */
	public long getNgayTra() {
		return ngayTra;
	}

	/**
	 * @param ngayTra the ngayTra to set
	 */
	public void setNgayTra(long ngayTra) {
		this.ngayTra = ngayTra;
	}

	/**
	 * @return the ngayNhan
	 */
	public long getNgayNhan() {
		return ngayNhan;
	}

	/**
	 * @param ngayNhan the ngayNhan to set
	 */
	public void setNgayNhan(long ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public int getYesgoAdults() {
		return yesgoAdults;
	}

	public void setYesgoAdults(int yesgoAdults) {
		this.yesgoAdults = yesgoAdults;
	}

	public int getYesgoChilds() {
		return yesgoChilds;
	}

	public void setYesgoChilds(int yesgoChilds) {
		this.yesgoChilds = yesgoChilds;
	}

	public String getNameBooked() {
		return nameBooked;
	}

	public void setNameBooked(String nameBooked) {
		this.nameBooked = nameBooked;
	}

	public String getEmailBooked() {
		return emailBooked;
	}

	public void setEmailBooked(String emailBooked) {
		this.emailBooked = emailBooked;
	}

	public String getPhoneBooled() {
		return phoneBooled;
	}

	public void setPhoneBooled(String phoneBooled) {
		this.phoneBooled = phoneBooled;
	}

	public String getNoteBooked() {
		return noteBooked;
	}

	public void setNoteBooked(String noteBooked) {
		this.noteBooked = noteBooked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMinQty() {
		return minQty;
	}

	public void setMinQty(String minQty) {
		this.minQty = minQty;
	}

	public boolean isSetImage() {
		return setImage;
	}

	public void setSetImage(boolean setImage) {
		this.setImage = setImage;
	}



}