package com.vrealvn.vrealapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.vreal.db.DatabaseHandler;
import com.vreal.libs.SessionManager;
import com.vreal.model.CommentsModel;

public class DataManager {
	private static DataManager instance;
	private HashMap<String, String> parameters;
	private static String message = "";
	private String server_time = "0";
	private final String KEY_MESSAGE = "message";
	private final String KEY_ERROR = "error";
	private final String KEY_DATA = "data";
	private SessionManager sm = new SessionManager(null);
	private DatabaseHandler db = new DatabaseHandler(null);
	private ArrayList<CommentsModel> listcommentModelObject = new ArrayList<CommentsModel>();

	public static DataManager getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = new DataManager();
	}

	public String getMessage() {
		if (message == null) {
			message = "message null";
		}
		return message;
	}
}

	/*private void notifiUI(NotifyDataListener no, int value) {
		if (no != null) {
			no.onNotify(value);
		}
	}

	public long getTimesTamp() {
		long disTime = 0;
		long timetap = 0;
		if (HotdealUtilities.getCurrentTime() < Long.parseLong(server_time)) {
			disTime = Long.parseLong(server_time)
					- HotdealUtilities.getCurrentTime();
			timetap = HotdealUtilities.getCurrentTime() + disTime;
		}
		if (HotdealUtilities.getCurrentTime() > Long.parseLong(server_time)) {
			disTime = HotdealUtilities.getCurrentTime()
					- Long.parseLong(server_time);
			timetap = Long.parseLong(server_time) + disTime;
		} else {
			timetap = Long.parseLong(server_time);
		}
		// HotdealUtilities.showLog(timetap + "");
		return timetap;
	}



	// private ArrayList<DetailsModel> listMuaNgay = new
	// ArrayList<DetailsModel>();

	private ArrayList<CateSildeModel> listCateSlide = new ArrayList<CateSildeModel>();

	public void getInfo(final NotifyDataListener notifi,
			final boolean showProcees)  {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_INFO);
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("apiVersion", "1");

		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleInfo(result, notifi);

					}
				});
	}

	public void handleInfo(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					KEY_ERROR)) == 0) {
				JSONObject resultData = resultJson.getJSONObject(KEY_DATA);
				server_time = HotdealUtilities.getDataString(resultData,
						"timestamp");
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
				db.deleteApi(ConstantValue.GET_INFO);
				db.addNewCache(ConstantValue.GET_INFO, result);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getListAbout(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ABOUT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleListAbout(result, notifi);

					}
				});
	}

	private ArrayList<StateModel> listAboutRoot = new ArrayList<StateModel>();

	public void handleListAbout(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			// if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
			// KEY_ERROR)) == 0) {
			JSONObject resultData = resultJson.getJSONObject(KEY_DATA);
			JSONArray listJson = resultData.getJSONArray("list_data");
			getListAboutRoot().clear();
			for (int j = 0; j < listJson.length(); j++) {
				JSONObject result1 = listJson.getJSONObject(j);
				StateModel md = new StateModel();
				md.setDataAboutRoot(result1);
				if (!md.getStateName().toUpperCase().equals("HỢP TÁC")
						|| !md.getStateID().equals("19")) {
					getListAboutRoot().add(md);
				}

			}

			// db.deleteApi(ConstantValue.GET_ABOUT);
			// db.addNewCache(ConstantValue.GET_ABOUT, result);
			notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			// }
			// else {
			// notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			// }

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<StateModel> listAbout2 = new ArrayList<StateModel>();

	public void getListAbout2(final NotifyDataListener notifi,
			final boolean showProcees, String pageID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ABOUT2);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("pageId", pageID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							// if
							// (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
							// KEY_ERROR)) == 0) {
							JSONObject resultData = resultJson
									.getJSONObject(KEY_DATA);
							JSONArray listJson = resultData
									.getJSONArray("list_data");
							listAbout2.clear();
							for (int j = 0; j < listJson.length(); j++) {
								JSONObject result1 = listJson.getJSONObject(j);
								StateModel md = new StateModel();
								md.setDataAboutRoot(result1);
								listAbout2.add(md);
							}
							notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							// }
							// else {
							// notifiUI(notifi,
							// NotifyDataListener.NOTIFY_FAILED);
							// }

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}

					}
				});
	}

	private ArrayList<StateModel> listAbout3 = new ArrayList<StateModel>();

	public void getListAbout3(final NotifyDataListener notifi,
			final boolean showProcees, String pageID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ABOUT3);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("pageId", pageID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							// if
							// (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
							// KEY_ERROR)) == 0) {
							JSONObject resultData = resultJson
									.getJSONObject(KEY_DATA);
							JSONArray listJson = resultData
									.getJSONArray("list_data");
							listAbout3.clear();
							for (int j = 0; j < listJson.length(); j++) {
								JSONObject result1 = listJson.getJSONObject(j);
								StateModel md = new StateModel();
								md.setDataAboutRoot3(result1);
								listAbout3.add(md);
							}
							notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							// }
							// else {
							// notifiUI(notifi,
							// NotifyDataListener.NOTIFY_FAILED);
							// }

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}

					}
				});
	}

	public void getListCateSlide(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_LIST_CATE_SLIDE);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleListCateSlide(result, notifi);

					}
				});
	}

	public void handleListCateSlide(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					KEY_ERROR)) == 0) {
				JSONArray listData = resultJson.getJSONArray(KEY_DATA);
				for (int j = 0; j < listData.length(); j++) {
					JSONObject resultData = listData.getJSONObject(j);
					JSONArray listJson;
					listJson = resultData.getJSONArray("listCategory");
					listCateSlide.clear();
					CateSildeModel md1 = new CateSildeModel();
					md1.setName("Trang chủ");
					md1.setThem(true);
					md1.setImage(R.drawable.ic_home);
					md1.setBackground(R.color.white);
					listCateSlide.add(md1);
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jSonOb = new JSONObject();
						jSonOb = listJson.getJSONObject(i);
						CateSildeModel md = new CateSildeModel();
						md.setData(jSonOb);
						md.setBackground(R.color.white);
						try {
							if (i == 0) {
								md.setImage(R.drawable.ic_anuong);
							} else if (i == 1) {
								md.setImage(R.drawable.ic_spa);
							} else if (i == 2) {
								md.setImage(R.drawable.ic_daotao);
							} else if (i == 3) {
								md.setImage(R.drawable.ic_sanpham);
							} else if (i == 4) {
								md.setImage(R.drawable.ic_thoitrang);
							} else if (i == 5) {
								md.setImage(R.drawable.ic_dulich);
							} else {
								md.setImage(R.drawable.ic_dulich);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

						listCateSlide.add(md);
					}
				}

				db.deleteApi(ConstantValue.GET_LIST_CATE_SLIDE);
				db.addNewCache(ConstantValue.GET_LIST_CATE_SLIDE, result);
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<BannerHomeModel> listBannerHome = new ArrayList<BannerHomeModel>();

	public void getListBanner(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_BANNER);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());

		parameters.put("type", "home");
		parameters.put("stateId", sm.getLocationID());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleListBanner(result, notifi);

					}
				});
	}

	public void handleListBanner(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					KEY_ERROR)) == 0) {
				JSONObject resultData = resultJson.getJSONObject(KEY_DATA);
				JSONArray listJson;
				listJson = resultData.getJSONArray("listBanner");
				getListBannerHome().clear();
				for (int i = 0; i < listJson.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = listJson.getJSONObject(i);
					BannerHomeModel md = new BannerHomeModel();
					md.setData(jSonOb);
					getListBannerHome().add(md);
				}
				db.deleteApi(ConstantValue.GET_BANNER);
				db.addNewCache(ConstantValue.GET_BANNER, result);
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<DealHomeModel> listDealHome = new ArrayList<DealHomeModel>();

	public void getListDealHome(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_DEAL_HOME);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put(ConstantValue.STATEID, sm.getLocationID());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {
					//
					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleListDealHome(result, notifi);

					}
				});
	}

	public void handleListDealHome(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					KEY_ERROR)) == 0) {
				JSONObject resultData = resultJson.getJSONObject(KEY_DATA);
				JSONArray listJson;
				listJson = resultData.getJSONArray("listCategory");
				listDealHome.clear();
				for (int i = 0; i < listJson.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = listJson.getJSONObject(i);
					DealHomeModel md = new DealHomeModel();
					md.setData(jSonOb);
					listDealHome.add(md);
				}
				db.deleteApi(ConstantValue.GET_DEAL_HOME);
				db.addNewCache(ConstantValue.GET_DEAL_HOME, result);
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<LocationModel> listLocation = new ArrayList<LocationModel>();

	public void getListLocation(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_LIST_LOCATION);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						handleListLocation(result, notifi);

					}
				});
	}

	public void handleListLocation(String result, NotifyDataListener notifi) {
		try {
			JSONObject resultJson = new JSONObject(result);
			message = HotdealUtilities.getDataString(resultJson, KEY_MESSAGE);
			if (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					KEY_ERROR)) == 0) {
				JSONObject resultData = resultJson.getJSONObject(KEY_DATA);
				JSONArray listJson;
				listJson = resultData.getJSONArray("listState");
				listLocation.clear();
				for (int i = 0; i < listJson.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = listJson.getJSONObject(i);
					LocationModel md = new LocationModel();
					md.setData(jSonOb);
					listLocation.add(md);
				}
				db.deleteApi(ConstantValue.GET_LIST_LOCATION);
				db.addNewCache(ConstantValue.GET_LIST_LOCATION, result);
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<DetailsModel> listCategory = new ArrayList<DetailsModel>();

	public void getListCategory(final NotifyDataListener notifi,
			final boolean showProcees, final String cateID, final int offset,
			final int limit) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, "product.getList");
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());

		parameters.put("categoryId", cateID);
		parameters.put("offset", String.valueOf(offset));
		parameters.put("limit", String.valueOf(limit));
		parameters.put("stateId", sm.getLocationID());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData
										.getJSONArray("listProduct");
								// listCategory.clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									DetailsModel md = new DetailsModel();
									md.setData(jSonOb);
									listCategory.add(md);
								}
								if (listJson.length() == 0) {
									notifiUI(notifi,
											NotifyDataListener.NOTIFY_FAILED);
								} else {
									notifiUI(notifi,
											NotifyDataListener.NOTIFY_OK);
								}

							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private DetailsModel detailProduct;

	public void getDetailsProduct(final NotifyDataListener notifi,
			final boolean showProcees, final String proID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_DETAILS_PRODUCT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());

		parameters.put("productId", proID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultPro = resultData
										.getJSONObject("product");
								setDetailProduct(new DetailsModel());
								getDetailProduct().setData(resultPro);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
						// HotdealUtilities.writeObject(ConstantValue.GET_DETAILS_PRODUCT,
						// result);
					}
				});
	}

	private ArrayList<DetailsModel> listSimilar = new ArrayList<DetailsModel>();

	public void getListSmilar(final NotifyDataListener notifi,
			final boolean showProcees, String producID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_LIST_SIMILAR);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("productId", producID);
		parameters.put(ConstantValue.STATEID, sm.getLocationID());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								setListSimilar(new ArrayList<DetailsModel>());
								JSONArray listJson;
								listJson = resultData
										.getJSONArray("listProduct");
								getListSimilar().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									DetailsModel md = new DetailsModel();
									md.setData(jSonOb);
									getListSimilar().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
						// HotdealUtilities.writeObject(ConstantValue.GET_LIST_SIMILAR,
						// result);
					}
				});
	}

	private ArrayList<DetailsModel> listSearch = new ArrayList<DetailsModel>();

	public void getListSearch(final NotifyDataListener notifi,
			final boolean showProcees, final String key, final int offset,
			final int limit) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, "product.search");
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());

		parameters.put("term", key);
		parameters.put("offset", String.valueOf(offset));
		parameters.put("limit", String.valueOf(limit));
		parameters.put(ConstantValue.STATEID, sm.getLocationID());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData
										.getJSONArray("listProduct");
								listSearch.clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									DetailsModel md = new DetailsModel();
									md.setData(jSonOb);
									listSearch.add(md);
								}

								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);

							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private UsersModel userModel = new UsersModel();;

	public void login(final NotifyDataListener notifi,
			final boolean showProcees, final String username, final String pass) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.LOGIN);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("username", username);
		parameters.put("email", username);
		parameters.put("password", pass);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultUser = resultData
										.getJSONObject("user");
								getUserModel().setData(resultUser);
								db.addNewUser(getUserModel());
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);

							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void loginFaceGG(final NotifyDataListener notifi,
			final boolean showProcees, final String email, final String gender,
			final String birthDay) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.LOGIN_FACE_GG);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("email", email);
		parameters.put("gender", gender);
		parameters.put("birthday", birthDay);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultUser = resultData
										.getJSONObject("user");
								getUserModel().setData(resultUser);
								db.addNewUser(getUserModel());
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);

							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void register(final NotifyDataListener notifi,
			final boolean showProcees, final String username,
			final String pass, String fullname, String email, String gender,
			String birthday) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, "user.register");
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("username", username);
		parameters.put("password", pass);
		// parameters.put("fullname", fullname);
		parameters.put("email", email);
		parameters.put("gender", gender);
		parameters.put("birthday", birthday);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultUser = resultData
										.getJSONObject("user");
								userModel = new UsersModel();
								userModel.setData(resultUser);
								db.addNewUser(getUserModel());
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void updateUserInfo(final NotifyDataListener notifi,
			final boolean showProcees, final String username, final String id,
			String phone, String gender, String birthday) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.UPDATE_USER_INFO);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("full_name", username);
		parameters.put("user_id", id);
		// parameters.put("fullname", fullname);
		parameters.put("phone", phone);
		parameters.put("gender", gender);
		parameters.put("birthday", birthday);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								if (userModel == null) {
									userModel = new UsersModel();
								}
								userModel.setDataUpdate(resultData);
								db.addNewUser(getUserModel());
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<CommentsModel> listAllComments = new ArrayList<CommentsModel>();

	public void getAllComments(final NotifyDataListener notifi,
			final boolean showProcees, String producID, String off, String li) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ALL_COMMENT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("productId", producID);
		parameters.put("offset", off);
		parameters.put("limit", li);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData
										.getJSONArray("listComment");
								getListAllComments().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									CommentsModel md = new CommentsModel();
									md.setData(jSonOb);
									getListAllComments().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
						// HotdealUtilities.writeObject(ConstantValue.GET_LIST_SIMILAR,
						// result);
					}
				});
	}

	public void postComment(final NotifyDataListener notifi,
			final boolean showProcees, String producID, String cm,
			String parentID, String rating, String userID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ALL_COMMENT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("object_id'", producID);
		parameters.put("comment_message", cm);
		parameters.put("parent_id", parentID);
		parameters.put("rating_value", rating);
		parameters.put("user_id", userID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								// JSONObject resultData =
								// resultJson.getJSONObject(KEY_DATA);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private DetailsModel preOrder;

	public void getSKU(final NotifyDataListener notifi,
			final boolean showProcees, String producID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_SKU);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("productId", producID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject obProduct = resultData
										.getJSONObject("product");
								setPreOrder(new DetailsModel());
								getPreOrder().setDataSKU(obProduct);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void changePass(final NotifyDataListener notifi,
			final boolean showProcees, String user_login, String pass,
			String newpass) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.CHANGEPASS);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("user_login", user_login);
		parameters.put("pass_login", pass);
		parameters.put("pass_new", newpass);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								// JSONObject resultData =
								// resultJson.getJSONObject(KEY_DATA);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void forgotPass(final NotifyDataListener notifi,
			final boolean showProcees, String email) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.FORGOT_PASS);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("user_email", email);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								// JSONObject resultData =
								// resultJson.getJSONObject(KEY_DATA);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<DeliveryAddressModel> listDeliveryAddress = new ArrayList<DeliveryAddressModel>();

	public void getDeliveryAddList(final NotifyDataListener notifi,
			final boolean showProcees, String user) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API,
				ConstantValue.GET_LIST_DELIVERY_ADDRESS);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData.getJSONArray("product");
								getListDeliveryAddress().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									DeliveryAddressModel md = new DeliveryAddressModel();
									md.setData(jSonOb);
									getListDeliveryAddress().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<StateModel> listState = new ArrayList<StateModel>();

	public void getState(final NotifyDataListener notifi,
			final boolean showProcees) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_STATE);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData.getJSONArray("listState");
								getListState().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									StateModel md = new StateModel();
									md.setData(jSonOb);
									getListState().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<StateModel> listDistrict = new ArrayList<StateModel>();

	public void getDis(final NotifyDataListener notifi,
			final boolean showProcees, final String stateID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_DISTRICT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("stateId", stateID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData.getJSONArray("listState");
								getListDistrict().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									StateModel md = new StateModel();
									md.setDataDis(jSonOb);
									getListDistrict().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<StateModel> listWard = new ArrayList<StateModel>();

	public void getWard(final NotifyDataListener notifi,
			final boolean showProcees, final String stateID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_WARD);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("districtId", stateID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData.getJSONArray("listState");
								getListWard().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									StateModel md = new StateModel();
									md.setDataWard(jSonOb);
									getListWard().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void submitAddress(final NotifyDataListener notifi,
			final boolean showProcees, HashMap<String, String> params) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_SUBMIT_ADDRESS);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", params.get("userId"));
		parameters.put("userProfileId", params.get("userProfileId"));
		parameters.put("user_action", params.get("user_action"));
		parameters.put("name", params.get("name"));
		parameters.put("telephone", params.get("telephone"));
		parameters.put("address", params.get("address"));
		parameters.put("street", params.get("street"));
		parameters.put("ward_name", params.get("ward_name"));
		parameters.put("ward_id", params.get("ward"));
		parameters.put("district_name", params.get("district_name"));
		parameters.put("district_id", params.get("district"));
		parameters.put("city_name", params.get("city_name"));
		parameters.put("city_id", params.get("city"));
		parameters.put("floor", params.get("floor"));
		parameters.put("address_type", params.get("address_type"));

		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								// if(isUpdate){
								// getListDeliveryAddress().clear();
								// }
								// DeliveryAddressModel md = new
								// DeliveryAddressModel();
								// md.setData(resultData);
								// getListDeliveryAddress().add(md);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<OrderByUserModel> listOrderByUser = new ArrayList<OrderByUserModel>();

	public void getOrderByUser(final NotifyDataListener notifi,
			final boolean showProcees, String user) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_ORDER_BY_USER);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultData1 = resultData
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData1.getJSONArray("product");
								getListOrderByUser().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									OrderByUserModel md = new OrderByUserModel();
									md.setData(jSonOb);
									getListOrderByUser().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<String> listProductInvalid = new ArrayList<String>();

	private CheckOutModel checkoutModel;

	public void checkOut(final NotifyDataListener notifi,
			final boolean showProcees, String user, JSONArray jsonArray,
			String addressID, String delySat, String note) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.CHECK_OUT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		parameters.put("listProduct", jsonArray.toString());
		parameters.put("address_id", addressID);
		parameters.put("deliverySaturday", delySat);
		parameters.put("note", note);
		// HotdealUtilities.showALog(parameters.toString());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultOrder = resultData
										.getJSONObject("order");
								setCheckoutModel(new CheckOutModel());
								getCheckoutModel().setData(resultOrder);

								JSONObject resultShipping = null;
								JSONArray listInvalid = null;
								try {
									resultShipping = resultData
											.getJSONObject("shipping");
								} catch (Exception e) {
									notifiUI(notifi, 2);
									return;
								}
								try {
									listInvalid = resultShipping
											.getJSONArray("invalid_products");
									for (int i = 0; i < listInvalid.length(); i++) {
										JSONObject jSonOb = new JSONObject();
										jSonOb = listInvalid.getJSONObject(i);
										listProductInvalid.add(HotdealUtilities
												.getDataString(jSonOb,
														"product_id"));
									}
								} catch (Exception e) {
									// TODO: handle exception
								}

								JSONArray listJson = resultShipping
										.getJSONArray("payment_methods");
								JSONArray listJsonshipping_methods = resultShipping
										.getJSONArray("shipping_methods");
								getCheckoutModel().setDataPayment(listJson);
								getCheckoutModel().setDataShipping(
										listJsonshipping_methods);

								if (null != listInvalid
										&& listInvalid.length() > 0) {
									message = "Giỏ hàng có sản phẩm chỉ giao tại TP HCM. Bạn vui lòng điều chỉnh giỏ hàng hoặc địa chỉ giao hàng";
									notifiUI(notifi, 2);
									return;
								}
								if (null != listJsonshipping_methods
										&& listJsonshipping_methods.length() == 0) {
									message = "Deal này không hỗ trợ khu vực của bạn. Vui lòng điều chỉnh địa chỉ hoặc chọn deal khác";
									notifiUI(notifi, 3);
									return;
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<OrderByUserChildModel> listEvoucher = new ArrayList<OrderByUserChildModel>();

	public void getEvoucher(final NotifyDataListener notifi,
			final boolean showProcees, String user) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_EVOUCHER);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject resultData1 = resultData
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData1.getJSONArray("voucher");
								getListEvoucher().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									OrderByUserChildModel md = new OrderByUserChildModel();
									md.setDataEV(jSonOb);
									getListEvoucher().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private ArrayList<RewardModel> listReward = new ArrayList<RewardModel>();
	private int sumReward = 0;

	public void getRewardPoint(final NotifyDataListener notifi,
			final boolean showProcees, String user) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.GET_REWARD_POINT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONArray listJson;
								listJson = resultData.getJSONArray("user");
								try {
									setSumReward(Integer
											.parseInt(HotdealUtilities
													.getDataString(resultData,
															"userTotalPoint")));
								} catch (Exception e) {
									e.printStackTrace();
								}
								getListReward().clear();
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									RewardModel md = new RewardModel();
									md.setData(jSonOb);
									getListReward().add(md);
								}
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	// private ArrayList<OrderByUserChildModel> listEvoucher = new
	// ArrayList<OrderByUserChildModel>();
	private String payment_action;
	private String payment_url;
	private String orderIDReturn;

	public void payment(final NotifyDataListener notifi,
			final boolean showProcees, String user, String orderID,
			String paymentID, String shippingID, String rewward, String coupon) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.PAYMENT);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		parameters.put("orderId", orderID);
		parameters.put("paymentId", paymentID);
		parameters.put("shippingId", shippingID);
		parameters.put("rewardPoint", rewward);
		parameters.put("promotionCode", coupon);
		// HotdealUtilities.showALog(parameters.toString());
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								JSONObject order = resultData
										.getJSONObject("order");
								setPayment_action(HotdealUtilities
										.getDataString(resultData,
												"payment_action"));
								setOrderIDReturn(HotdealUtilities
										.getDataString(order, "order_id"));
								setPayment_url(HotdealUtilities.getDataString(
										resultData, "payment_url"));
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	// private ArrayList<RewardModel> listReward = new ArrayList<RewardModel>();

	public void getCancelOrder(final NotifyDataListener notifi,
			final boolean showProcees, String userID, String orderID) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.CANCEL_ORDER);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", userID);
		parameters.put("orderId", orderID);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	private OrderByUserModel orderByUserDetail = new OrderByUserModel();

	public void getOrderByUserDetail(final NotifyDataListener notifi,
			final boolean showProcees, String user, String order) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API,
				ConstantValue.GET_ORDER_BY_USER_DETAIL);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("userId", user);
		parameters.put("orderId", order);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								JSONObject resultData = resultJson
										.getJSONObject(KEY_DATA);
								getOrderByUserDetail()
										.setDataDetail(resultData);
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void checkVoucher(final NotifyDataListener notifi,
			final boolean showProcees, String orderId, String promotionCode) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.CHECKVOUCHER);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("orderId", orderId);
		parameters.put("promotionCode", promotionCode);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public void addDevice(final NotifyDataListener notifi,
			final boolean showProcees, String device_uid, String device_token,
			String device_name, String device_model, String device_version,
			String status, String created, String modified, String userId,
			String state_id) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, ConstantValue.ADD_DEVICE);
		parameters.put(ConstantValue.TS, getTimesTamp() + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
				parameters.get(ConstantValue.API),
				parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters
				.put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		parameters.put("device_uid", device_uid);
		parameters.put("device_token", device_token);
		parameters.put("device_name", device_name);
		parameters.put("device_model", device_model);
		parameters.put("device_version", device_version);
		parameters.put("device_type", "1");
		parameters.put("status", status);
		parameters.put("created", created);
		parameters.put("modified", modified);
		parameters.put("userId", userId);
		parameters.put("state_id", state_id);
		JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
				showProcees, new JSONCallBack() {

					@Override
					public void callResult(Context activity, String result,
							long time) {
						try {
							JSONObject resultJson = new JSONObject(result);
							message = HotdealUtilities.getDataString(
									resultJson, KEY_MESSAGE);
							if (Integer.parseInt(HotdealUtilities
									.getDataString(resultJson, KEY_ERROR)) == 0) {
								notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
							} else {
								notifiUI(notifi,
										NotifyDataListener.NOTIFY_FAILED);
							}

						} catch (Exception e) {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							e.printStackTrace();
						}
					}
				});
	}

	public ArrayList<CateSildeModel> getListCateSlide() {
		return listCateSlide;
	}

	public void setListCateSlide(ArrayList<CateSildeModel> listCateSlide) {
		this.listCateSlide = listCateSlide;
	}

	public ArrayList<BannerHomeModel> getListBannerHome() {
		return listBannerHome;
	}

	public void setListBannerHome(ArrayList<BannerHomeModel> listBannerHome) {
		this.listBannerHome = listBannerHome;
	}

	public ArrayList<DealHomeModel> getListDealHome() {
		return listDealHome;
	}

	public void setListDealHome(ArrayList<DealHomeModel> listDealHome) {
		this.listDealHome = listDealHome;
	}


	public ArrayList<LocationModel> getListLocation() {
		return listLocation;
	}

	/**
	 * @param listLocation
	 *            the listLocation to set
	 
	public void setListLocation(ArrayList<LocationModel> listLocation) {
		this.listLocation = listLocation;
	}

	*//**
	 * @return the listCategory
	 *//*
	public ArrayList<DetailsModel> getListCategory() {
		return listCategory;
	}

	*//**
	 * @param listCategory
	 *            the listCategory to set
	 *//*
	public void setListCategory(ArrayList<DetailsModel> listCategory) {
		this.listCategory = listCategory;
	}

	*//**
	 * @return the detailProduct
	 *//*
	public DetailsModel getDetailProduct() {
		return detailProduct;
	}

	*//**
	 * @param detailProduct
	 *            the detailProduct to set
	 *//*
	public void setDetailProduct(DetailsModel detailProduct) {
		this.detailProduct = detailProduct;
	}

	*//**
	 * @return the listSimilar
	 *//*
	public ArrayList<DetailsModel> getListSimilar() {
		return listSimilar;
	}

	*//**
	 * @param listSimilar
	 *            the listSimilar to set
	 *//*
	public void setListSimilar(ArrayList<DetailsModel> listSimilar) {
		this.listSimilar = listSimilar;
	}

	*//**
	 * @return the listSearch
	 *//*
	public ArrayList<DetailsModel> getListSearch() {
		return listSearch;
	}

	*//**
	 * @param listSearch
	 *            the listSearch to set
	 *//*
	public void setListSearch(ArrayList<DetailsModel> listSearch) {
		this.listSearch = listSearch;
	}

	*//**
	 * @return the userModel
	 *//*
	public UsersModel getUserModel() {
		return userModel;
	}

	*//**
	 * @param userModel
	 *            the userModel to set
	 *//*
	public void setUserModel(UsersModel userModel) {
		this.userModel = userModel;
	}

	*//**
	 * @return the listcommentModelObject
	 *//*
	public ArrayList<CommentsModel> getListcommentModelObject() {
		return listcommentModelObject;
	}

	*//**
	 * @param listcommentModelObject
	 *            the listcommentModelObject to set
	 *//*
	public void setListcommentModelObject(
			ArrayList<CommentsModel> listcommentModelObject) {
		this.listcommentModelObject = listcommentModelObject;
	}

	*//**
	 * @return the listAllComments
	 *//*
	public ArrayList<CommentsModel> getListAllComments() {
		return listAllComments;
	}

	*//**
	 * @param listAllComments
	 *            the listAllComments to set
	 *//*
	public void setListAllComments(ArrayList<CommentsModel> listAllComments) {
		this.listAllComments = listAllComments;
	}

	*//**
	 * @return the preOrder
	 *//*
	public DetailsModel getPreOrder() {
		return preOrder;
	}

	*//**
	 * @param preOrder
	 *            the preOrder to set
	 *//*
	public void setPreOrder(DetailsModel preOrder) {
		this.preOrder = preOrder;
	}

	*//**
	 * @return the listDeliveryAddress
	 *//*
	public ArrayList<DeliveryAddressModel> getListDeliveryAddress() {
		return listDeliveryAddress;
	}

	*//**
	 * @param listDeliveryAddress
	 *            the listDeliveryAddress to set
	 *//*
	public void setListDeliveryAddress(
			ArrayList<DeliveryAddressModel> listDeliveryAddress) {
		this.listDeliveryAddress = listDeliveryAddress;
	}

	*//**
	 * @return the listState
	 *//*
	public ArrayList<StateModel> getListState() {
		return listState;
	}

	public void setListState(ArrayList<StateModel> listState) {
		this.listState = listState;
	}

	public ArrayList<StateModel> getListDistrict() {
		return listDistrict;
	}

	public void setListDistrict(ArrayList<StateModel> listDistrict) {
		this.listDistrict = listDistrict;
	}

	public ArrayList<StateModel> getListWard() {
		return listWard;
	}

	public void setListWard(ArrayList<StateModel> listWard) {
		this.listWard = listWard;
	}

	public ArrayList<OrderByUserModel> getListOrderByUser() {
		return listOrderByUser;
	}

	public void setListOrderByUser(ArrayList<OrderByUserModel> listOrderByUser) {
		this.listOrderByUser = listOrderByUser;
	}

	public ArrayList<OrderByUserChildModel> getListEvoucher() {
		return listEvoucher;
	}

	public void setListEvoucher(ArrayList<OrderByUserChildModel> listEvoucher) {
		this.listEvoucher = listEvoucher;
	}

	public ArrayList<RewardModel> getListReward() {
		return listReward;
	}

	public void setListReward(ArrayList<RewardModel> listReward) {
		this.listReward = listReward;
	}

	public int getSumReward() {
		return sumReward;
	}

	public void setSumReward(int sumReward) {
		this.sumReward = sumReward;
	}

	public CheckOutModel getCheckoutModel() {
		return checkoutModel;
	}

	public void setCheckoutModel(CheckOutModel checkoutModel) {
		this.checkoutModel = checkoutModel;
	}

	public String getPayment_action() {
		return payment_action;
	}

	public void setPayment_action(String payment_action) {
		this.payment_action = payment_action;
	}

	public String getPayment_url() {
		return payment_url;
	}

	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}

	public ArrayList<StateModel> getListAboutRoot() {
		return listAboutRoot;
	}

	public void setListAboutRoot(ArrayList<StateModel> listAboutRoot) {
		this.listAboutRoot = listAboutRoot;
	}

	public ArrayList<StateModel> getListAbout2() {
		return listAbout2;
	}

	public void setListAbout2(ArrayList<StateModel> listAbout2) {
		this.listAbout2 = listAbout2;
	}

	public ArrayList<StateModel> getListAbout3() {
		return listAbout3;
	}

	public void setListAbout3(ArrayList<StateModel> listAbout3) {
		this.listAbout3 = listAbout3;
	}

	public OrderByUserModel getOrderByUserDetail() {
		return orderByUserDetail;
	}

	public void setOrderByUserDetail(OrderByUserModel orderByUserDetail) {
		this.orderByUserDetail = orderByUserDetail;
	}

	public String getOrderIDReturn() {
		return orderIDReturn;
	}

	public void setOrderIDReturn(String orderIDReturn) {
		this.orderIDReturn = orderIDReturn;
	}
	*/
//*/}
