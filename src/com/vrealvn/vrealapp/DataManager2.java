package com.vrealvn.vrealapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.vreal.connection.JsonObjectInterface;
import com.vreal.connection.VolleyRequestCustom;
import com.vreal.db.DatabaseHandler;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.libs.VrealUtilities;
import com.vreal.model.DealHomeModel;
import com.vreal.model.DetailsModel;
import com.vreal.model.VrealModel;

/**
 * Created by G8 on 1/25/2016.
 */
public class DataManager2 {
	VolleyRequestCustom jsonObjRequest;
	private final String TAG_REQUEST = "fuck_bitch";
	private RequestQueue mVolleyQueue;
	private static DataManager2 ourInstance = new DataManager2();
	// private ProgressDialog mProgress;
	private final String KEY_MESSAGE = "Status";
	private final String KEY_ERROR = "Code";
	private final String KEY_DATA = "data";
	private DatabaseHandler db;
	private SessionManager sm;
	private ProgressDialog pg;
	private DetailsModel md = new DetailsModel();
	private VrealModel vrealModel=new VrealModel();

	HashMap<String, String> parameters;
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
		if (null == sm) {
			sm = new SessionManager(VrealApp.getContext());
		}
	}

	public void showProgress(Activity activity) {
		isShowPro = true;
		try {
			pg = ProgressDialog.show(activity, "Loading", "please wait....", true);
			// showDialog(activity);
		} catch (Exception e) {
		}

	}

	private Dialog dialogSelectImage;


	public void stopProgress() {
		try {
			pg.dismiss();
			dialogSelectImage.dismiss();
		} catch (Exception e) {
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

	public void callServer(final Activity activity, HashMap<String, String> parameters, final boolean showPro, boolean isPost, final JsonObjectInterface jsonObjectInterface,
			final boolean isGetJsonArray) {
		VrealUtilities.showALog(parameters.toString());
		if (showPro && !isShowPro) {
			showProgress(activity);

		}
		String urlServer;
		int method;
		if (isPost) {
			method = Method.POST;
			urlServer = ConstantValue.URL_SERVER;
		} else {
			method = Method.GET;
			urlServer = makeUrl(parameters, ConstantValue.URL_SERVER + "/" + parameters.remove(ConstantValue.API));
		}
		jsonObjRequest = new VolleyRequestCustom(method, urlServer, parameters, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				VrealUtilities.showALog(response);
				if (isGetJsonArray) {
					JSONArray jsonResponse = null;
					try {
						jsonResponse = new JSONArray(response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					jsonObjectInterface.callResultJAr(activity, jsonResponse);
				} else {
					JSONObject jsonResponse = null;
					try {
						jsonResponse = new JSONObject(response);
						setMessage(jsonResponse.getString(KEY_MESSAGE).toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					jsonObjectInterface.callResultJOb(activity, jsonResponse);
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
				VrealUtilities.showALog(error.getMessage());

				jsonObjectInterface.callResultJOb(activity, null);
				if (showPro)
					stopProgress();
			}
		});

		// Set a retry policy in case of SocketTimeout & ConnectionTimeout
		// Exceptions. Volley does retry for you if you have specified the
		// policy.

		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(12000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag(TAG_REQUEST);
		getVolleyQueue(activity).add(jsonObjRequest);
	}

	private String makeUrl(HashMap<String, String> parameters, String urrl) {
		Uri.Builder builder = Uri.parse(urrl).buildUpon();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			builder.appendQueryParameter(key, value);
		}
		return builder.toString();

	}

	public HashMap<String, String> getDefauldParams(String API) {
		return parameters;
	}

	private void notifiUI(NotifyDataListener no, int value, String api) {
		if (no != null) {
			no.onNotify(api, value);
		}
	}

	private ArrayList<VrealModel> listProvices = new ArrayList<>();

	public void getProvice(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_CITY);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				handleProvice(result, notifyDataListener);

			}
		}, true);
	}

	public void handleProvice(JSONArray result, final NotifyDataListener notifyDataListener) {
		try {
			if (null != result) {
				getListProvices().clear();
				VrealModel md1 = new VrealModel();
				md1.setProvinceName("Không xác định");
				getListProvices().add(md1);
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataProvince(jSonOb);
					getListProvices().add(md);
				}
				sm.setProviceJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_CITY);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_CITY);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_CITY);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listDistrict = new ArrayList<>();

	public void getDistrict(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String proUD) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_DIS);
		builder.put("proID", proUD);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				handleDistrict(result, notifyDataListener);

			}
		}, true);
	}

	public void handleDistrict(JSONArray result, final NotifyDataListener notifyDataListener) {
		try {
			getListDistrict().clear();
			if (null != result) {
				VrealModel md1 = new VrealModel();
				md1.setProvinceName("Không xác định");
				getListDistrict().add(md1);
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataDistrict(jSonOb);
					getListDistrict().add(md);
				}
				sm.setDisJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_DIS);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DIS);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DIS);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listWard = new ArrayList<>();

	public void getWard(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_WARD);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				handleWard(result, notifyDataListener);

			}
		}, true);
	}

	public void handleWard(JSONArray result, final NotifyDataListener notifyDataListener) {
		try {
			getListWard().clear();
			if (null != result) {
				VrealModel md1 = new VrealModel();
				md1.setProvinceName("Không xác định");
				getListWard().add(md1);
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataWard(jSonOb);
					getListWard().add(md);
				}
				sm.setWardJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_WARD);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_WARD);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_WARD);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listStreet = new ArrayList<>();

	public void getStreet(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_STREET);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				handleStreet(result, notifyDataListener);

			}
		}, true);
	}

	public void handleStreet(JSONArray result, final NotifyDataListener notifyDataListener) {
		try {
			getListStreet().clear();
			if (null != result) {
				VrealModel md1 = new VrealModel();
				md1.setProvinceName("Không xác định");
				getListStreet().add(md1);
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataStreet(jSonOb);
					getListStreet().add(md);
				}
				sm.setStreetJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_STREET);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_STREET);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_STREET);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listKhuvuc = new ArrayList<>();

	public void getKhuvuc(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_KHUVUC);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				handleKV(result, notifyDataListener);

			}
		}, true);
	}

	public void handleKV(JSONArray result, final NotifyDataListener notifyDataListener) {
		try {
			getListKhuvuc().clear();
			if (null != result) {
				VrealModel md1 = new VrealModel();
				md1.setProvinceName("Không xác định");
				getListKhuvuc().add(md1);
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataAREA(jSonOb);
					getListKhuvuc().add(md);
				}
				sm.setKhuVucJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_KHUVUC);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_KHUVUC);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_KHUVUC);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listTypeProperty = new ArrayList<>();

	public void getTypeProperty(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_TYPE);
		// builder.put("proID", proUD);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListTypeProperty().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("RealNewsTypeList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setTypeProperty(jSonOb);
							getListTypeProperty().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_TYPE);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_TYPE);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_TYPE);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<VrealModel> listLoainhadat = new ArrayList<>();

	public void getLoaiNhaDat(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String idType) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_LOAINHADAT);
		builder.put("typeID", idType);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListLoainhadat().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("RealNewsCateList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setLoaiNhaDat(jSonOb);
							getListLoainhadat().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_LOAINHADAT);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_LOAINHADAT);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_LOAINHADAT);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<VrealModel> listHuong = new ArrayList<>();

	public void getHuong(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_HUONG);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListHuong().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("DirectionList");
						VrealModel md1 = new VrealModel();
						md1.setProvinceName("Không xác định");
						getListHuong().add(md1);
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setHuong(jSonOb);
							getListHuong().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_HUONG);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_HUONG);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_HUONG);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<VrealModel> listDuAn = new ArrayList<>();

	public void getDuan(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_DUAN);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListDuAn().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("ProjectTypeList");
						VrealModel md1 = new VrealModel();
						md1.setProvinceName("Không xác định");
						getListDuAn().add(md1);
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setDuan(jSonOb);
							getListDuAn().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_DUAN);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DUAN);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DUAN);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	public void getGia(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_GIA);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					listGia.clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("UnitList");
						VrealModel md1 = new VrealModel();
						md1.setId("-1");
						md1.setProvinceName("Không xác định");
						listGia.add(md1);
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setGia(jSonOb);
							listGia.add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_GIA);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_GIA);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_GIA);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	public void getDienTich(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_DIENTICH);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					listDientich.clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("AcreageList");
						VrealModel md1 = new VrealModel();
						md1.setId("-1");
						md1.setProvinceName("Không xác định");
						listDientich.add(md1);
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setDIENTICH(jSonOb);
							listDientich.add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_DIENTICH);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DIENTICH);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_DIENTICH);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<VrealModel> listGia = new ArrayList<>();

	private ArrayList<VrealModel> listDientich = new ArrayList<>();

	private ArrayList<VrealModel> listSearch = new ArrayList<>();

	public void seach(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String typeID, String cateID, String province, String districtId, String ward,
			String street, String homeDirection, String project, String dientichID, String NoOfRoom, String NoOfRestRoom, String giaID, int pageIndex, int pageSize) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.SEARCG);
		builder.put("typeID", typeID);
		builder.put("cateID", cateID);
		builder.put("province", province);
		builder.put("districtId", districtId);
		builder.put("ward", ward);
		builder.put("street", street);
		builder.put("homeDirection", homeDirection);
		builder.put("project", project);
		builder.put("dientichID", dientichID);
		builder.put("AcreageEnd", "");
		builder.put("AcreageStart", "");
		builder.put("NoOfRoom", NoOfRoom);
		builder.put("NoOfRestRoom", NoOfRestRoom);
		builder.put("priceID", giaID);
		// builder.put("PriceTo", PriceTo);
		builder.put("pageIndex", pageIndex + "");
		builder.put("pageSize", pageSize + "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListSearch().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("RealsNewsList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setSeachData(jSonOb);
							getListSearch().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.SEARCG);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCG);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCG);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

//	private ArrayList<VrealModel> listSearchProject = new ArrayList<>();

	public void seach_project(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String projectTypeID, String ProvinceId, String DistrictID,
			int pageIndex, int pageSize) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.SEARCH_PROJECT);
		builder.put("projectTypeID", projectTypeID);
		builder.put("ProvinceId", ProvinceId);
		builder.put("DistrictID", DistrictID);
		builder.put("pageIndex", pageIndex + "");
		builder.put("pageSize", pageSize + "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListSearch().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("ProjectList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setSeachProData(jSonOb);
							getListSearch().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.SEARCH_PROJECT);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCH_PROJECT);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCH_PROJECT);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	public void post(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String realTypeID, String realCateID, String realPriorityID, String projectID,
			String provinceID, String districtID, String wardID, String streetID, String unitID, String areaID, String acreage, String realName, String description, String MatTien, String DuongVao,
			String SoTang, String interior, String address, String price, String homeDirectionID, String balconyID, String noOfRoom, String noOfRest, String latitude, String longitude,
			String isVisible, String start, String end, String contactName, String contactAddress, String contactPhone, String contactEmail, String creatorID, String userID) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, "V_RealNews_Post");
		builder.put("realTypeID", realTypeID);
		builder.put("realCateID", realCateID);
		builder.put("realPriorityID", realPriorityID);
		builder.put("projectID", projectID);
		builder.put("provinceID", provinceID);
		builder.put("districtID", districtID);
		builder.put("wardID", wardID);
		builder.put("streetID", streetID);
		builder.put("unitID", unitID);
		builder.put("areaID", areaID);
		builder.put("acreage", acreage);
		builder.put("realName", realName);
		builder.put("description", description);
		builder.put("MatTien", MatTien);
		builder.put("DuongVao", DuongVao);
		builder.put("SoTang", SoTang);
		builder.put("interior", interior);
		builder.put("address", address);
		builder.put("price", price);
		builder.put("homeDirectionID", homeDirectionID);
		builder.put("balconyID", balconyID);
		builder.put("noOfRoom", noOfRoom);
		builder.put("noOfRest", noOfRest);
		builder.put("latitude", latitude);
		builder.put("longitude", longitude);
		builder.put("isVisible", isVisible);
		builder.put("start", start);
		builder.put("end", end);
		builder.put("contactName", contactName);
		builder.put("contactAddress", contactAddress);
		builder.put("contactPhone", contactPhone);
		builder.put("contactEmail", contactEmail);
		builder.put("creatorID", creatorID);
		builder.put("userID", userID);
		builder.put("FeaturesInHouseID", contactName);
		builder.put("photoList", contactAddress);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListSearch().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						// JSONArray listJson;
						// listJson = result.getJSONArray("RealsNewsList");
						// for (int i = 0; i < listJson.length(); i++) {
						// JSONObject jSonOb = new JSONObject();
						// jSonOb = listJson.getJSONObject(i);
						// VrealModel md = new VrealModel();
						// md.setSeachData(jSonOb);
						// getListSearch().add(md);
						// }
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.SEARCG);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCG);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.SEARCG);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<DealHomeModel> listTintuc = new ArrayList<>();

	public void getTintuc(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_NEWS);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListTintuc().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("NewsTypeList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							DealHomeModel md = new DealHomeModel();
							md.setData(jSonOb);
							getListTintuc().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_NEWS);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_NEWS);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_NEWS);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	private ArrayList<VrealModel> listLoaiDuAn = new ArrayList<>();

	public void getLoaiDuan(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_LOAIDUAN);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListLoaiDuAn().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("ProjectTypeList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setLoaiDuan(jSonOb);
							getListLoaiDuAn().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_LOAIDUAN);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_LOAIDUAN);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_LOAIDUAN);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}
	private ArrayList<VrealModel> listTienich= new ArrayList<>();

	public void getTienich(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_TIENICH);
//		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListTienich().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("CityFacCateList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setTienich(jSonOb);
							getListTienich().add(md);
						}
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK, ConstantValue.GET_TIENICH);
					} else {
						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_TIENICH);
					}

				} catch (Exception e) {
					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED, ConstantValue.GET_TIENICH);
					e.printStackTrace();
				}

			}

			@Override
			public void callResultJAr(Context activity, JSONArray result) {
				// TODO Auto-generated method stub

			}
		}, false);
	}

	public ArrayList<VrealModel> getListProvices() {
		return listProvices;
	}

	public void setListProvices(ArrayList<VrealModel> listProvices) {
		this.listProvices = listProvices;
	}

	public ArrayList<VrealModel> getListDistrict() {
		return listDistrict;
	}

	public void setListDistrict(ArrayList<VrealModel> listDistrict) {
		this.listDistrict = listDistrict;
	}

	public ArrayList<VrealModel> getListTypeProperty() {
		return listTypeProperty;
	}

	public void setListTypeProperty(ArrayList<VrealModel> listTypeProperty) {
		this.listTypeProperty = listTypeProperty;
	}

	public ArrayList<VrealModel> getListWard() {
		return listWard;
	}

	public void setListWard(ArrayList<VrealModel> listWard) {
		this.listWard = listWard;
	}

	public ArrayList<VrealModel> getListStreet() {
		return listStreet;
	}

	public void setListStreet(ArrayList<VrealModel> listStreet) {
		this.listStreet = listStreet;
	}

	public ArrayList<VrealModel> getListKhuvuc() {
		return listKhuvuc;
	}

	public void setListKhuvuc(ArrayList<VrealModel> listKhuvuc) {
		this.listKhuvuc = listKhuvuc;
	}

	public ArrayList<VrealModel> getListLoainhadat() {
		return listLoainhadat;
	}

	public void setListLoainhadat(ArrayList<VrealModel> listLoainhadat) {
		this.listLoainhadat = listLoainhadat;
	}

	public ArrayList<VrealModel> getListGia() {
		return listGia;
	}

	public void setListGia(ArrayList<VrealModel> listGia) {
		this.listGia = listGia;
	}

	public ArrayList<VrealModel> getListDientich() {
		return listDientich;
	}

	public void setListDientich(ArrayList<VrealModel> listDientich) {
		this.listDientich = listDientich;
	}

	public ArrayList<VrealModel> getListHuong() {
		return listHuong;
	}

	public void setListHuong(ArrayList<VrealModel> listHuong) {
		this.listHuong = listHuong;
	}

	public ArrayList<VrealModel> getListSearch() {
		return listSearch;
	}

	public void setListSearch(ArrayList<VrealModel> listSearch) {
		this.listSearch = listSearch;
	}

	public ArrayList<VrealModel> getListDuAn() {
		return listDuAn;
	}

	public void setListDuAn(ArrayList<VrealModel> listDuAn) {
		this.listDuAn = listDuAn;
	}

	public ArrayList<DealHomeModel> getListTintuc() {
		return listTintuc;
	}

	public void setListTintuc(ArrayList<DealHomeModel> listTintuc) {
		this.listTintuc = listTintuc;
	}

	public DetailsModel getMd() {
		return md;
	}

	public void setMd(DetailsModel md) {
		this.md = md;
	}

	public ArrayList<VrealModel> getListLoaiDuAn() {
		return listLoaiDuAn;
	}

	public void setListLoaiDuAn(ArrayList<VrealModel> listLoaiDuAn) {
		this.listLoaiDuAn = listLoaiDuAn;
	}

	public VrealModel getVrealModel() {
		return vrealModel;
	}

	public void setVrealModel(VrealModel vrealModel) {
		this.vrealModel = vrealModel;
	}

	public ArrayList<VrealModel> getListTienich() {
		return listTienich;
	}

	public void setListTienich(ArrayList<VrealModel> listTienich) {
		this.listTienich = listTienich;
	}

}
