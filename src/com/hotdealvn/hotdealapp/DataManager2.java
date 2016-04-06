package com.hotdealvn.hotdealapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hotdeal.connection.JsonObjectInterface;
import com.hotdeal.connection.VolleyRequestCustom;
import com.hotdeal.db.DatabaseHandler;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.libs.NotifyDataListener;
import com.hotdeal.libs.SessionManager;
import com.hotdeal.model.BannerHomeModel;
import com.hotdeal.model.CateSildeModel;
import com.hotdeal.model.CheckOutModel;
import com.hotdeal.model.CommentsModel;
import com.hotdeal.model.DeliveryAddressModel;
import com.hotdeal.model.DetailsModel;
import com.hotdeal.model.FilterModel;
import com.hotdeal.model.LocationModel;
import com.hotdeal.model.OrderByUserChildModel;
import com.hotdeal.model.OrderByUserModel;
import com.hotdeal.model.PaymentOrderModel;
import com.hotdeal.model.RewardModel;
import com.hotdeal.model.StateModel;
import com.hotdeal.model.UsersModel;
import com.android.vrealapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by G8 on 1/25/2016.
 */
public class DataManager2 {
	VolleyRequestCustom jsonObjRequest;
	// private final String TAG_REQUEST = "fuck_bitch";
	private RequestQueue mVolleyQueue;
	private static DataManager2 ourInstance = new DataManager2();
	// private ProgressDialog mProgress;
	private final String KEY_MESSAGE = "message";
	private final String KEY_ERROR = "error";
	private final String KEY_DATA = "data";
	private DatabaseHandler db;
	private SessionManager sm;

	HashMap<String, String> parameters;
	private String locationID;
	private ArrayList<CommentsModel> listcommentModelObject = new ArrayList<CommentsModel>();
	private String errorMess = "Lỗi trong quá trình tải dữ liệu, Vui lòng thử lại";

	public String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		DataManager2.message = message;
	}

	private static String message = "";

	public static DataManager2 getInstance() {
		return ourInstance;
	}

	private DataManager2() {

	}

	public void showProgress(Activity activity) {
		isShowPro = true;
		// mProgress = ProgressDialog.show(activity, "", "Loading...");
		// int v = R.layout.chochay;
		// mProgress.setContentView(v);
		// mProgress = ProgressDialog.show(activity, "", "", true, false);

		try {
			showDialog(activity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private Dialog dialogSelectImage;

	public void showDialog(Activity ac) {
		if (!ac.isFinishing()) {
			dialogSelectImage = new Dialog(ac);
			dialogSelectImage.getWindow();
			dialogSelectImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialogSelectImage.setContentView(R.layout.chochay);
			dialogSelectImage.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			ImageView img = (ImageView) dialogSelectImage.findViewById(R.id.webView1);
			img.setBackgroundResource(R.drawable.cho_chay);
			AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
			frameAnimation.start();
			dialogSelectImage.setCancelable(false);
			dialogSelectImage.setCanceledOnTouchOutside(false);
			dialogSelectImage.show();
		}

	}

	public void stopProgress() {
		try {
			dialogSelectImage.dismiss();
			// mProgress.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}

		isShowPro = false;
	}

	private RequestQueue getVolleyQueue(Activity ac) {
		if (null == mVolleyQueue) {
			return mVolleyQueue = Volley.newRequestQueue(ac);
		}
		return mVolleyQueue;
	}

	private boolean isShowPro = false;

	public void callServer(final Activity activity, HashMap<String, String> parameters, final boolean showPro, boolean isPost, final JsonObjectInterface jsonObjectInterface) {
		HotdealUtilities.showALog(parameters.toString());
		if (showPro && !isShowPro) {
			showProgress(activity);

		}
		jsonObjRequest = new VolleyRequestCustom(Request.Method.POST, ConstantValue.URL_SERVER, parameters, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					try {
						setMessage(response.getString("message").toString());
					} catch (Exception e) {
						// TODO: handle exception
					}

					HotdealUtilities.showALog(response.toString());
					jsonObjectInterface.callResultJOb(activity, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (showPro)
					stopProgress();

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// Handle your error types accordingly.For Timeout & No
				// connection error, you can show 'retry' button.
				// For AuthFailure, you can re login with user
				// credentials.
				// For ClientError, 400 & 401, Errors happening on
				// client side when sending api request.
				// In this case you can check how client is forming the
				// api and debug accordingly.
				// For ServerError 5xx, you can do retry or handle
				// accordingly.
				if (error instanceof NetworkError) {
					setMessage(errorMess + " (Network Error)");
				} else if (error instanceof ClientError) {
					setMessage(errorMess + " (Client Error)");
				} else if (error instanceof ServerError) {
					setMessage(errorMess + " (Server Error)");
				} else if (error instanceof AuthFailureError) {
					setMessage(errorMess + " (AuthFailure Error)");
				} else if (error instanceof ParseError) {
					setMessage(errorMess + " (Parse Error)");
				} else if (error instanceof NoConnectionError) {
					setMessage(errorMess + " (No Connection Error)");
				} else if (error instanceof TimeoutError) {
					setMessage(errorMess + " (Timeout Error)");
				}
				// HotdealUtilities.showALog(error.getMessage());

				jsonObjectInterface.callResultJOb(activity, null);
				if (showPro)
					stopProgress();
			}
		});

		// Set a retry policy in case of SocketTimeout & ConnectionTimeout
		// Exceptions. Volley does retry for you if you have specified the
		// policy.

		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(120000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag(activity.getClass().getSimpleName());
		getVolleyQueue(activity).add(jsonObjRequest);
	}

	private void notifiUI(NotifyDataListener no, int value) {
		if (no != null) {
			no.onNotify(value);
		}
	}

//	long timeStart = 0;
//	private long server_time = 0;

//	public long getTimesTamp(Activity ac) {
//		if (sm != null) {
//		} else {
//			sm = new SessionManager(ac);
//		}
//		long timetap = 0;
//		final long cu = HotdealUtilities.getCurrentTime();
//		if (sm.getTimeStart() == 0) {
//			sm.setTimeStart(cu);
//			timetap = sm.getSVTime();
//		} else {
//			timetap = sm.getSVTime() + (cu - sm.getTimeStart());
//		}
//
//		return timetap;
//	}
	public long getTimesTamp(Context ac) {
		if (sm != null) {
		} else {
			sm = new SessionManager(ac);
		}
		long timetap = 0;
		final long cu = HotdealUtilities.getCurrentTime();
		if (sm.getTimeStart() == 0) {
			sm.setTimeStart(cu);
			timetap = sm.getSVTime();
		} else {
			timetap = sm.getSVTime() + (cu - sm.getTimeStart());
		}

		return timetap;
	}

	public HashMap<String, String> getDefauldParams(String API, Activity ac) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, API);
		parameters.put(ConstantValue.TS, getTimesTamp(ac) + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(parameters.get(ConstantValue.API), parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters.put(ConstantValue.APPVER, ConstantValue.APPVERVALUE);
		return parameters;
	}
	public HashMap<String, String> getDefauldParams(String API, Context ac) {
		parameters = new HashMap<String, String>();
		parameters.put(ConstantValue.API, API);
		parameters.put(ConstantValue.TS, getTimesTamp(ac) + "");
		parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(parameters.get(ConstantValue.API), parameters.get(ConstantValue.TS)));
		parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		parameters.put(ConstantValue.APPVER, ConstantValue.APPVERVALUE);
		return parameters;
	}

	private void handleInfo(Context activity, JSONObject result, NotifyDataListener notifyDataListener) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONObject resultData = result.getJSONObject(KEY_DATA);
//				server_time = resultData.getLong("timestamp");
				sm = new SessionManager(activity);
				sm.setSVTime(resultData.getLong("timestamp"));
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
				db = new DatabaseHandler(activity);
				db.deleteApi(ConstantValue.GET_INFO);
				db.addNewCache(ConstantValue.GET_INFO, result.toString());
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getInfo(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_INFO, activity);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleInfo(activity, result, notifyDataListener);
			}
		});
	}

	public void getListBanner(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String type, String cateID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_BANNER, activity);
		builder.put("type", type);
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		builder.put("categoryId", cateID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListBanner(result, notifyDataListener, (Activity) activity);
			}
		});
	}

	public ArrayList<BannerHomeModel> getListBannerHome() {
		return listBannerHome;
	}

	public void setListBannerHome(ArrayList<BannerHomeModel> listBannerHome) {
		this.listBannerHome = listBannerHome;
	}

	public ArrayList<BannerHomeModel> getListBannerHome2() {
		return listBannerHome2;
	}

	private ArrayList<BannerHomeModel> listBannerHome = new ArrayList<>();
	private ArrayList<BannerHomeModel> listBannerHome2 = new ArrayList<>();
	private ArrayList<BannerHomeModel> listBannerCate = new ArrayList<>();

	public void handleListBanner(JSONObject result, final NotifyDataListener notifi, Activity activity) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONObject resultData = result.getJSONObject(KEY_DATA);
				JSONArray listJson = null;
				try {
					listJson = resultData.getJSONArray("home_mobile");
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (listJson != null) {
					getListBannerHome().clear();
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jSonOb;
						jSonOb = listJson.getJSONObject(i);
						BannerHomeModel md = new BannerHomeModel();
						md.setData(jSonOb);
						getListBannerHome().add(md);
					}
				}

				try {
					listJson = resultData.getJSONArray("home_side_mobile");
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (listJson != null) {
					getListBannerHome2().clear();
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jSonOb;
						jSonOb = listJson.getJSONObject(i);
						BannerHomeModel md = new BannerHomeModel();
						md.setData(jSonOb);
						getListBannerHome2().add(md);
					}

				}
				try {
					listJson = resultData.getJSONArray("category_mobile");
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (listJson != null) {
					getListBannerCate().clear();
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jSonOb;
						jSonOb = listJson.getJSONObject(i);
						BannerHomeModel md = new BannerHomeModel();
						md.setData(jSonOb);
						getListBannerCate().add(md);
					}

				}
				// db = new DatabaseHandler(activity);
				// db.deleteApi(ConstantValue.GET_BANNER);
				// db.addNewCache(ConstantValue.GET_BANNER, result.toString());
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getListTabs(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final int pos) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_TABS, activity);
		builder.put("limit", "10");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListTabs(result, notifyDataListener, (Activity) activity, pos);
			}
		});
	}

	public ArrayList<StateModel> getListTabs() {
		return listTabs;
	}

	private ArrayList<StateModel> listTabs = new ArrayList<>();

	public void handleListTabs(JSONObject result, final NotifyDataListener notifi, Activity activity, int poss) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONObject resultData = result.getJSONObject(KEY_DATA);
				JSONArray listJson = resultData.getJSONArray("listTabs");
				getListTabs().clear();
				for (int i = 0; i < listJson.length(); i++) {
					JSONObject jSonOb;
					jSonOb = listJson.getJSONObject(i);
					StateModel md = new StateModel();
					if (i == poss) {
						md.setCheck(true);
					} else {
						md.setCheck(false);
					}
					md.setDataTabs(jSonOb);
					getListTabs().add(md);
				}
				db = new DatabaseHandler(activity);
				db.deleteApi(ConstantValue.GET_LIST_TABS);
				db.addNewCache(ConstantValue.GET_LIST_TABS, result.toString());
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getListDealHome(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String tabName, String off, final boolean clear, double lat, double lng) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_DEAL_HOME_V2, activity);
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		builder.put("tabName", tabName);
		builder.put("offset", off);
		builder.put("limit", ConstantValue.OFFSET + "");
		builder.put("latitude", String.valueOf(lat));
		builder.put("longitude", String.valueOf(lng));
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListDEALhome(result, notifyDataListener, clear, (Activity) activity);
			}
		});

		// HashMap<String, String> parameters = new HashMap<String, String>();
		// parameters.put(ConstantValue.API, ConstantValue.GET_DEAL_HOME_V2);
		// parameters.put(ConstantValue.TS, getTimesTamp() + "");
		// parameters.put(ConstantValue.SIG, HotdealUtilities.getSign(
		// parameters.get(ConstantValue.API),
		// parameters.get(ConstantValue.TS)));
		// parameters.put(ConstantValue.DEVICE, ConstantValue.ANDROID);
		// parameters
		// .put(ConstantValue.APPVER, HotdealUtilities.getBuildVertion());
		//
		// parameters.put("stateId", sm.getLocationID());
		// parameters.put("tabName", tabName);
		// parameters.put("offset", "off");
		// parameters.put("limit", "1");
		// JSONMethod json = new JSONMethod(HotdealApp.context, parameters,
		// false,
		// new JSONCallBack() {
		//
		// @Override
		// public void callResult(Context activity, String result,
		// long time) {
		//
		// }
		// });
	}

	public ArrayList<DetailsModel> getListDealHome() {
		return listDealHome;
	}

	public ArrayList<DetailsModel> getListDealHomeLe() {
		return listDealHomeLe;
	}

	private ArrayList<DetailsModel> listDealHome = new ArrayList<>();
	private ArrayList<DetailsModel> listDealHomeLe = new ArrayList<>();
	private int totalItemHome;

	public void handleListDEALhome(JSONObject result, final NotifyDataListener notifi, boolean clear, Activity activity) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONObject resultData = result.getJSONObject(KEY_DATA);
				JSONArray listJson = resultData.getJSONArray("listProduct");
				if (clear) {
					getListDealHome().clear();
					DetailsModel md1 = new DetailsModel();
					md1.setType(DetailsModel.SECTION);
					getListDealHome().add(md1);
				}
				setTotalItemHome(Integer.parseInt(resultData.getString("total")));
				if (listJson.length() <= 0) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					return;
				}

				for (int i = 0; i < listJson.length(); i++) {
					JSONObject jSonOb = listJson.getJSONObject(i);
					DetailsModel md = new DetailsModel();
					md.setType(DetailsModel.ITEM);
					md.setData(jSonOb);
					getListDealHome().add(md);
				}
				db = new DatabaseHandler(activity);
				db.deleteApi(ConstantValue.GET_DEAL_HOME_V2);
				db.addNewCache(ConstantValue.GET_DEAL_HOME_V2, result.toString());
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private ArrayList<FilterModel> listFilter = new ArrayList<>();

	public void getListFilter(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String cateID, String subCate, String locationID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_CATE_SLIDE, activity);
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		builder.put("categoryId", cateID);
		builder.put("subCategoryId", subCate);
		builder.put("locationId", locationID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONArray listData = result.getJSONArray(KEY_DATA);
						getListFilter().clear();
						for (int i = 0; i < listData.length(); i++) {
							JSONObject resultData = listData.getJSONObject(i);
							FilterModel md = new FilterModel();
							md.setData(resultData);
							getListFilter().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}
			}
		});
	}

	public void getListFilterDL(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String cateID, String subCate, String locationID, final int pos) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_CATE_SLIDE, activity);
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		builder.put("categoryId", cateID);
		builder.put("subCategoryId", subCate);
		builder.put("locationId", locationID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONArray listData = result.getJSONArray(KEY_DATA);
						// getListFilter().clear();
						for (int i = 0; i < listData.length(); i++) {
							JSONObject resultData = listData.getJSONObject(i);
							FilterModel md = new FilterModel();
							md.setData(resultData);
							if (getListFilter().size() < listData.length()) {
								getListFilter().add(i, md);
							} else {
								if (pos == 0) {
									if (0 != i) {
										getListFilter().set(i, md);
									}
								} else {
									if (0 != i && i != 1) {
										getListFilter().set(i, md);
									}
								}

							}

						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}
			}
		});
	}

	public void getListCateSlide(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_CATE_SLIDE, activity);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListCateSlide(result, notifyDataListener, (Activity) activity);
			}
		});
	}

	public ArrayList<CateSildeModel> getListCateSlide() {
		return listCateSlide;
	}

	private ArrayList<CateSildeModel> listCateSlide = new ArrayList<>();

	public void handleListCateSlide(JSONObject result, final NotifyDataListener notifi, Activity activity) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONArray listData = result.getJSONArray(KEY_DATA);
				for (int j = 0; j < listData.length(); j++) {
					JSONObject resultData = listData.getJSONObject(j);
					JSONArray listJson;
					listJson = resultData.getJSONArray("listCategory");
					getListCateSlide().clear();
					CateSildeModel md1 = new CateSildeModel();
					md1.setName("Trang chủ");
					md1.setThem(true);
					md1.setImage(R.drawable.ic_home);
					md1.setBackground(R.color.white);
					getListCateSlide().add(md1);
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jSonOb = listJson.getJSONObject(i);
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

						getListCateSlide().add(md);
					}
				}

				db = new DatabaseHandler(activity);
				db.deleteApi(ConstantValue.GET_LIST_CATE_SLIDE);
				db.addNewCache(ConstantValue.GET_LIST_CATE_SLIDE, result.toString());
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getListAbout(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ABOUT, activity);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListAbout(result, notifyDataListener);
			}
		});
	}

	public ArrayList<StateModel> getListAboutRoot() {
		return listAboutRoot;
	}

	private ArrayList<StateModel> listAboutRoot = new ArrayList<>();

	public void handleListAbout(JSONObject result, NotifyDataListener notifi) {
		try {
			JSONObject resultData = result.getJSONObject(KEY_DATA);
			JSONArray listJson = resultData.getJSONArray("list_data");
			getListAboutRoot().clear();
			for (int j = 0; j < listJson.length(); j++) {
				JSONObject result1 = listJson.getJSONObject(j);
				StateModel md = new StateModel();
				md.setDataAboutRoot(result1);
				if (!md.getStateName().toUpperCase().equals("HỢP TÁC") || !md.getStateID().equals("19")) {
					getListAboutRoot().add(md);
				}

			}
			notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	public void getListLocation(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_LOCATION, activity);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				handleListLocation(result, notifyDataListener, (Activity) activity);
			}
		});
	}

	public ArrayList<LocationModel> getListLocation() {
		return listLocation;
	}

	private ArrayList<LocationModel> listLocation = new ArrayList<>();

	public void handleListLocation(JSONObject result, NotifyDataListener notifi, Activity activity) {
		try {
			if (result.getInt(KEY_ERROR) == 0) {
				JSONObject resultData = result.getJSONObject(KEY_DATA);
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
				db = new DatabaseHandler(activity);
				db.deleteApi(ConstantValue.GET_LIST_LOCATION);
				db.addNewCache(ConstantValue.GET_LIST_LOCATION, result.toString());
				notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
			} else {
				notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			}

		} catch (Exception e) {
			notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
			e.printStackTrace();
		}
	}

	private DetailsModel detailProduct;

	public void getDetailsProduct(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String proID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_DETAILS_PRODUCT, activity);
		builder.put("productId", proID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultPro = resultData.getJSONObject("product");
						setDetailProduct(new DetailsModel());
						getDetailProduct().setData(resultPro);
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}
			}
		});
	}

	public DetailsModel getDetailProduct() {
		return detailProduct;
	}

	public void setDetailProduct(DetailsModel detailProduct) {
		this.detailProduct = detailProduct;
	}

	private ArrayList<DetailsModel> listSimilar = new ArrayList<DetailsModel>();

	public void getListSmilar(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String producID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_SIMILAR, activity);
		builder.put("productId", producID);
		sm = new SessionManager(activity);
		builder.put(ConstantValue.STATEID, sm.getLocationID());
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						setListSimilar(new ArrayList<DetailsModel>());
						JSONArray listJson;
						listJson = resultData.getJSONArray("listProduct");
						getListSimilar().clear();
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							DetailsModel md = new DetailsModel();
							md.setData(jSonOb);
							getListSimilar().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<DetailsModel> getListSimilar() {
		return listSimilar;
	}

	public void setListSimilar(ArrayList<DetailsModel> listSimilar) {
		this.listSimilar = listSimilar;
	}

	public ArrayList<CommentsModel> getListcommentModelObject() {
		return listcommentModelObject;
	}

	public void setListcommentModelObject(ArrayList<CommentsModel> listcommentModelObject) {
		this.listcommentModelObject = listcommentModelObject;
	}

	private ArrayList<CommentsModel> listAllComments = new ArrayList<CommentsModel>();

	public void getAllComments(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String producID, String off, String li) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ALL_COMMENT, activity);
		builder.put("productId", producID);
		builder.put("offset", off);
		builder.put("limit", li);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONArray listJson;
						listJson = resultData.getJSONArray("listComment");
						getListAllComments().clear();
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							CommentsModel md = new CommentsModel();
							md.setData(jSonOb);
							getListAllComments().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<CommentsModel> getListAllComments() {
		return listAllComments;
	}

	public void setListAllComments(ArrayList<CommentsModel> listAllComments) {
		this.listAllComments = listAllComments;
	}

	private ArrayList<DetailsModel> listCategory = new ArrayList<DetailsModel>();
	private int totalCate;

	public void getListCategory(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String cateID, final int offset, final int limit, String field,
			String sort, String subID, final boolean clear, String locationId, String districtId, String provinceId, String starId, String min_price, String max_price) {
		HashMap<String, String> builder = getDefauldParams("product.getList", activity);
		builder.put("categoryId", cateID);
		builder.put("offset", String.valueOf(offset));
		builder.put("limit", String.valueOf(limit));
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		builder.put("field", field);
		builder.put("sort", sort);
		builder.put("subCategoryId", subID);
		builder.put("locationId", locationId);
		builder.put("districtId", districtId);
		builder.put("provinceId", provinceId);
		builder.put("starId", starId);
		builder.put("min_price", min_price);
		builder.put("max_price", max_price);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONArray listJson;
						listJson = resultData.getJSONArray("listProduct");
						if (clear) {
							listCategory.clear();
						}
						if (listJson.length() <= 0) {
							notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
							return;
						}

						setTotalCate(Integer.parseInt(resultData.getString("total")));
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							DetailsModel md = new DetailsModel();
							md.setData(jSonOb);
							listCategory.add(md);
						}
						if (listJson.length() == 0) {
							notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
						} else {
							notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
						}

					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<DetailsModel> getListCategory() {
		return listCategory;
	}

	public void setListCategory(ArrayList<DetailsModel> listCategory) {
		this.listCategory = listCategory;
	}

	private UsersModel userModel = new UsersModel();;

	public void loginFaceGG(final Activity activity1, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String email, final String gender, final String birthDay) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.LOGIN_FACE_GG, activity1);
		builder.put("email", email);
		builder.put("gender", gender);
		builder.put("birthday", birthDay);
		callServer(activity1, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultUser = resultData.getJSONObject("user");
						userModel.setData(resultUser);
						db = new DatabaseHandler(activity);
						db.addNewUser(userModel);
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);

					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public void login(final Activity activity1, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String username, final String pass) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.LOGIN, activity1);
		builder.put("username", username);
		builder.put("email", username);
		builder.put("password", pass);
		callServer(activity1, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultUser = resultData.getJSONObject("user");
						userModel.setData(resultUser);
						db = new DatabaseHandler(activity);
						db.addNewUser(userModel);
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);

					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});

	}

	public UsersModel getUserModel() {
		// db = new DatabaseHandler(ac);
		// userModel = db.getUser();
		return userModel;

	}

	public void setUserModel(UsersModel userModel) {
		this.userModel = userModel;
	}

	public void register(final Activity activity1, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String username, final String pass, String fullname,
			String email, String gender, String birthday) {
		HashMap<String, String> builder = getDefauldParams("user.register", activity1);
		builder.put("username", username);
		builder.put("email", email);
		builder.put("password", pass);
		builder.put("gender", gender);
		builder.put("birthday", birthday);
		callServer(activity1, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultUser = resultData.getJSONObject("user");
						userModel = new UsersModel();
						userModel.setData(resultUser);
						db = new DatabaseHandler(activity);
						db.addNewUser(userModel);
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});

	}

	public void updateUserInfo(final Activity activity1, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, final String username, final String id, String phone,
			String gender, String birthday) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.UPDATE_USER_INFO, activity1);
		builder.put("full_name", username);
		builder.put("user_id", id);
		builder.put("phone", phone);
		builder.put("gender", gender);
		builder.put("birthday", birthday);
		callServer(activity1, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						if (userModel == null) {
							userModel = new UsersModel();
						}
						userModel.setDataUpdate(resultData);
						db = new DatabaseHandler(activity);
						db.addNewUser(userModel);
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});

	}

	public void changePass(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String user_login, String pass, String newpass) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.CHANGEPASS, activity);
		builder.put("user_login", user_login);
		builder.put("pass_login", pass);
		builder.put("pass_new", newpass);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});

	}

	private ArrayList<DeliveryAddressModel> listDeliveryAddress = new ArrayList<DeliveryAddressModel>();

	public void getDeliveryAddList(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_LIST_DELIVERY_ADDRESS, activity);
		builder.put("userId", user);

		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<DeliveryAddressModel> getListDeliveryAddress() {
		return listDeliveryAddress;
	}

	public void setListDeliveryAddress(ArrayList<DeliveryAddressModel> listDeliveryAddress) {
		this.listDeliveryAddress = listDeliveryAddress;
	}

	public void submitAddress(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, HashMap<String, String> params) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_SUBMIT_ADDRESS, activity);
		builder.put("userId", params.get("userId"));
		builder.put("userProfileId", params.get("userProfileId"));
		builder.put("user_action", params.get("user_action"));
		builder.put("name", params.get("name"));
		builder.put("telephone", params.get("telephone"));
		builder.put("address", params.get("address"));
		builder.put("street", params.get("street"));
		builder.put("ward_name", params.get("ward_name"));
		builder.put("ward_id", params.get("ward"));
		builder.put("district_name", params.get("district_name"));
		builder.put("district_id", params.get("district"));
		builder.put("city_name", params.get("city_name"));
		builder.put("city_id", params.get("city"));
		builder.put("floor", params.get("floor"));
		builder.put("address_type", params.get("address_type"));
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						// if(isUpdate){
						// getListDeliveryAddress().clear();
						// }
						// DeliveryAddressModel md = new
						// DeliveryAddressModel();
						// md.setData(resultData);
						// getListDeliveryAddress().add(md);
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});

	}

	private ArrayList<StateModel> listState = new ArrayList<StateModel>();

	public void getState(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_STATE, activity);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<StateModel> getListState() {
		return listState;
	}

	public void setListState(ArrayList<StateModel> listState) {
		this.listState = listState;
	}

	private ArrayList<StateModel> listDistrict = new ArrayList<StateModel>();

	public void getDis(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, final String stateID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_DISTRICT, activity);
		builder.put("stateId", stateID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<StateModel> getListDistrict() {
		return listDistrict;
	}

	public void setListDistrict(ArrayList<StateModel> listDistrict) {
		this.listDistrict = listDistrict;
	}

	private ArrayList<StateModel> listWard = new ArrayList<StateModel>();

	public void getWard(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, final String stateID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_WARD, activity);
		builder.put("districtId", stateID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<StateModel> getListWard() {
		return listWard;
	}

	public void setListWard(ArrayList<StateModel> listWard) {
		this.listWard = listWard;
	}

	private ArrayList<OrderByUserModel> listOrderByUser = new ArrayList<OrderByUserModel>();

	public void getOrderByUser(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ORDER_BY_USER, activity);
		builder.put("userId", user);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultData1 = resultData.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<OrderByUserModel> getListOrderByUser() {
		return listOrderByUser;
	}

	public void setListOrderByUser(ArrayList<OrderByUserModel> listOrderByUser) {
		this.listOrderByUser = listOrderByUser;
	}

	public void getCancelOrder(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String userID, String orderID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.CANCEL_ORDER, activity);
		builder.put("userId", userID);
		builder.put("orderId", orderID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	private OrderByUserModel orderByUserDetail = new OrderByUserModel();

	public void getOrderByUserDetail(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user, String order) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ORDER_BY_USER_DETAIL, activity);
		builder.put("userId", user);
		builder.put("orderId", order);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						getOrderByUserDetail().setDataDetail(resultData);
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public OrderByUserModel getOrderByUserDetail() {
		return orderByUserDetail;
	}

	public void setOrderByUserDetail(OrderByUserModel orderByUserDetail) {
		this.orderByUserDetail = orderByUserDetail;
	}

	private ArrayList<OrderByUserChildModel> listEvoucher = new ArrayList<OrderByUserChildModel>();

	public void getEvoucher(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_EVOUCHER, activity);
		builder.put("userId", user);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultData1 = resultData.getJSONObject(KEY_DATA);
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<OrderByUserChildModel> getListEvoucher() {
		return listEvoucher;
	}

	public void setListEvoucher(ArrayList<OrderByUserChildModel> listEvoucher) {
		this.listEvoucher = listEvoucher;
	}

	private ArrayList<RewardModel> listReward = new ArrayList<RewardModel>();
	private double sumReward = 0;

	public void getRewardPoint(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_REWARD_POINT, activity);
		builder.put("userId", user);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONArray listJson;
						listJson = resultData.getJSONArray("user");
						try {
							setSumReward(Integer.parseInt(HotdealUtilities.getDataString(resultData, "userTotalPoint")));
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<RewardModel> getListReward() {
		return listReward;
	}

	public void setListReward(ArrayList<RewardModel> listReward) {
		this.listReward = listReward;
	}

	public double getSumReward() {
		return sumReward;
	}

	public void setSumReward(int sumReward) {
		this.sumReward = sumReward;
	}

	private ArrayList<StateModel> listSort = new ArrayList<StateModel>();

	public void getSort(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_SORT, activity);
		sm = new SessionManager(activity);
		builder.put("stateId", sm.getLocationID());
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONArray listJson;
						listJson = resultData.getJSONArray("listSort");
						getListSort().clear();
						StateModel md1 = new StateModel();
						md1.setStateName("Mặc định");
						md1.setSort("");
						md1.setCheck(true);
						md1.setStateID("");
						getListSort().add(md1);
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							StateModel md = new StateModel();
							md.setDataSort(jSonOb);
							getListSort().add(md);
						}
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<StateModel> getListSort() {
		return listSort;
	}

	public void setListSort(ArrayList<StateModel> listSort) {
		this.listSort = listSort;
	}

	private DetailsModel preOrder;

	public void getSKU(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String producID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_SKU, activity);
		builder.put("productId", producID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject obProduct = resultData.getJSONObject("product");
						setPreOrder(new DetailsModel());
						getPreOrder().setDataSKU(obProduct);
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public DetailsModel getPreOrder() {
		return preOrder;
	}

	public void setPreOrder(DetailsModel preOrder) {
		this.preOrder = preOrder;
	}

	private ArrayList<String> listProductInvalid = new ArrayList<String>();

	private CheckOutModel checkoutModel;

	public void checkOut(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user, JSONArray jsonArray, String addressID, String delySat, String note) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.CHECK_OUT, activity);
		builder.put("userId", user);
		builder.put("listProduct", jsonArray.toString());
		builder.put("address_id", addressID);
		builder.put("deliverySaturday", delySat);
		builder.put("note", note);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONObject resultOrder = resultData.getJSONObject("order");
						setCheckoutModel(new CheckOutModel());
						getCheckoutModel().setData(resultOrder);

						JSONObject resultShipping = null;
						JSONArray listInvalid = null;
						try {
							resultShipping = resultData.getJSONObject("shipping");
						} catch (Exception e) {
							message = activity.getString(R.string.str_suco);
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
							return;
						}
						try {
							listInvalid = resultShipping.getJSONArray("invalid_products");
							for (int i = 0; i < listInvalid.length(); i++) {
								JSONObject jSonOb = new JSONObject();
								jSonOb = listInvalid.getJSONObject(i);
								getListProductInvalid().add(HotdealUtilities.getDataString(jSonOb, "product_id"));
							}
						} catch (Exception e) {
							// TODO: handle exception
						}

						JSONArray listJson = resultShipping.getJSONArray("payment_methods");
						JSONArray listJsonshipping_methods = resultShipping.getJSONArray("shipping_methods");
						getCheckoutModel().setDataPayment(listJson);
						getCheckoutModel().setDataShipping(listJsonshipping_methods);

						if (null != listInvalid && listInvalid.length() > 0) {
							message = "Bạn hiện có " + listInvalid.length() + " sản phẩm không hợp lệ. Bạn không thể tiếp tục thanh toán.";
							notifiUI(notifi, 2);
							return;
						}
						if (null != listJsonshipping_methods && listJsonshipping_methods.length() == 0) {
							message = "Deal này không hỗ trợ khu vực của bạn, vui lòng điều chỉnh địa chỉ hoặc chọn deal khác.";
							notifiUI(notifi, 3);
							return;
						}
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public CheckOutModel getCheckoutModel() {
		return checkoutModel;
	}

	public void setCheckoutModel(CheckOutModel checkoutModel) {
		this.checkoutModel = checkoutModel;
	}

	public ArrayList<String> getListProductInvalid() {
		return listProductInvalid;
	}

	public void setListProductInvalid(ArrayList<String> listProductInvalid) {
		this.listProductInvalid = listProductInvalid;
	}

	private String payment_action;
	private String payment_url;
	private String orderIDReturn;
	private PaymentOrderModel paymentOrderModel;

	public void payment(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String user, String orderID, String paymentID, String shippingID, String rewward,
			String coupon) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.PAYMENT, activity);
		builder.put("userId", user);
		builder.put("orderId", orderID);
		builder.put("paymentId", paymentID);
		builder.put("shippingId", shippingID);
		builder.put("rewardPoint", rewward);
		builder.put("promotionCode", coupon);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (null != result) {
						if (result.getInt(KEY_ERROR) == 0) {
							JSONObject resultData = result.getJSONObject(KEY_DATA);
							JSONObject order = resultData.getJSONObject("order");
							paymentOrderModel = new PaymentOrderModel();
							paymentOrderModel.setData(order);
							setPayment_action(HotdealUtilities.getDataString(resultData, "payment_action"));
							setOrderIDReturn(HotdealUtilities.getDataString(order, "order_id"));
							setPayment_url(HotdealUtilities.getDataString(resultData, "payment_url"));
							notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
						} else if (result.getInt(KEY_ERROR) == 4005) {
							// ko dung payment iD hoac shipping id
							notifiUI(notifi, 4005);
						} else if (result.getInt(KEY_ERROR) == 4003) {
							// sai param
							notifiUI(notifi, 4003);
						} else if (result.getInt(KEY_ERROR) == 4006) {
							// da thanh toan (already paid)
							notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
						} else {
							notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
						}
					} else {
						notifiUI(notifi, 2);
					}

				} catch (Exception e) {
					notifiUI(notifi, 2);
					e.printStackTrace();
				}

			}
		});
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

	public String getOrderIDReturn() {
		return orderIDReturn;
	}

	public void setOrderIDReturn(String orderIDReturn) {
		this.orderIDReturn = orderIDReturn;
	}

	public void checkVoucher(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String orderId, String promotionCode) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.CHECKVOUCHER, activity);
		builder.put("orderId", orderId);
		builder.put("promotionCode", promotionCode);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public void addDevice(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String device_uid, String device_token, String device_name, String device_model,
			String device_version, String status, String created, String modified, String userId, String state_id) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.ADD_DEVICE, activity);
		builder.put("device_uid", device_uid);
		builder.put("device_token", device_token);
		builder.put("device_name", device_name);
		builder.put("device_model", device_model);
		builder.put("device_version", device_version);
		builder.put("device_type", "1");
		builder.put("status", status);
		builder.put("created", created);
		builder.put("modified", modified);
		builder.put("userId", userId);
		builder.put("state_id", state_id);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	private ArrayList<StateModel> listAbout3 = new ArrayList<StateModel>();

	public void getListAbout3(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String pageID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ABOUT3, activity);
		builder.put("pageId", pageID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					// if
					// (Integer.parseInt(HotdealUtilities.getDataString(resultJson,
					// KEY_ERROR)) == 0) {
					JSONObject resultData = result.getJSONObject(KEY_DATA);
					JSONArray listJson = resultData.getJSONArray("list_data");
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

	public ArrayList<StateModel> getListAbout3() {
		return listAbout3;
	}

	public void setListAbout3(ArrayList<StateModel> listAbout3) {
		this.listAbout3 = listAbout3;
	}

	public void postComment(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String producID, String cm, String parentID, String rating, String userID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.COMMENT, activity);
		builder.put("object_id", producID);
		builder.put("comment_message", cm);
		builder.put("parent_id", parentID);
		builder.put("rating_value", rating);
		builder.put("user_id", userID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						// JSONObject resultData =
						// resultJson.getJSONObject(KEY_DATA);
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	private ArrayList<DetailsModel> listSearch = new ArrayList<DetailsModel>();

	public void getListSearch(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, final String key, final int offset, final int limit) {
		HashMap<String, String> builder = getDefauldParams("product.search", activity);
		builder.put("term", key);
		builder.put("offset", String.valueOf(offset));
		builder.put("limit", String.valueOf(limit));
		sm = new SessionManager(activity);
		builder.put(ConstantValue.STATEID, sm.getLocationID());
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						JSONObject resultData = result.getJSONObject(KEY_DATA);
						JSONArray listJson;
						listJson = resultData.getJSONArray("listProduct");
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
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<DetailsModel> getListSearch() {
		return listSearch;
	}

	public void setListSearch(ArrayList<DetailsModel> listSearch) {
		this.listSearch = listSearch;
	}

	public int getTotalItemHome() {
		return totalItemHome;
	}

	public void setTotalItemHome(int totalItemHome) {
		this.totalItemHome = totalItemHome;
	}

	public int getTotalCate() {
		return totalCate;
	}

	public void setTotalCate(int totalCate) {
		this.totalCate = totalCate;
	}

	public void forgotPass(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String email) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.FORGOT_PASS, activity);
		builder.put("user_email", email);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == 0) {
						// JSONObject resultData =
						// resultJson.getJSONObject(KEY_DATA);
						notifiUI(notifi, NotifyDataListener.NOTIFY_OK);
					} else {
						notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					}

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	private ArrayList<StateModel> listAbout2 = new ArrayList<StateModel>();

	public void getListAbout2(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, final String pageID) {
		HashMap<String, String> builder = getDefauldParams(ConstantValue.GET_ABOUT2, activity);
		builder.put("pageId", pageID);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					JSONObject resultData = result.getJSONObject(KEY_DATA);
					JSONArray listJson = resultData.getJSONArray("list_data");
					listAbout2.clear();
					for (int j = 0; j < listJson.length(); j++) {
						JSONObject result1 = listJson.getJSONObject(j);
						StateModel md = new StateModel();
						md.setDataAboutRoot(result1);
						listAbout2.add(md);
					}
					if (pageID.equals("47")) {
						StateModel md = new StateModel();
						md.setStateID("-1");
						md.setStateName("Gửi phản hồi");
						listAbout2.add(md);
					}

					notifiUI(notifi, NotifyDataListener.NOTIFY_OK);

				} catch (Exception e) {
					notifiUI(notifi, NotifyDataListener.NOTIFY_FAILED);
					e.printStackTrace();
				}

			}
		});
	}

	public ArrayList<StateModel> getListAbout2() {
		return listAbout2;
	}

	public void setListAbout2(ArrayList<StateModel> listAbout2) {
		this.listAbout2 = listAbout2;
	}

	public PaymentOrderModel getPaymentOrderModel() {
		return paymentOrderModel;
	}

	public void setPaymentOrderModel(PaymentOrderModel paymentOrderModel) {
		this.paymentOrderModel = paymentOrderModel;
	}

	public ArrayList<FilterModel> getListFilter() {
		return listFilter;
	}

	public void setListFilter(ArrayList<FilterModel> listFilter) {
		this.listFilter = listFilter;
	}

	public ArrayList<BannerHomeModel> getListBannerCate() {
		return listBannerCate;
	}

	public void setListBannerCate(ArrayList<BannerHomeModel> listBannerCate) {
		this.listBannerCate = listBannerCate;
	}

}
