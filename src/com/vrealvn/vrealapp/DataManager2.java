package com.vrealvn.vrealapp;

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
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vreal.connection.JsonObjectInterface;
import com.vreal.connection.VolleyRequestCustom;
import com.vreal.db.DatabaseHandler;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.model.BannerHomeModel;
import com.vreal.model.CateSildeModel;
import com.vreal.model.CommentsModel;
import com.vreal.model.DeliveryAddressModel;
import com.vreal.model.DetailsModel;
import com.vreal.model.FilterModel;
import com.vreal.model.LocationModel;
import com.vreal.model.OrderByUserChildModel;
import com.vreal.model.OrderByUserModel;
import com.vreal.model.PaymentOrderModel;
import com.vreal.model.RewardModel;
import com.vreal.model.StateModel;
import com.vreal.model.UsersModel;
import com.vreal.model.VrealModel;
import com.android.vrealapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			sm = new SessionManager(HotdealApp.getContext());
		}
	}

	public void showProgress(Activity activity) {
		isShowPro = true;
		try {
			pg = ProgressDialog.show(activity, "Loading... ",
					"please wait....", true);
			// showDialog(activity);
		} catch (Exception e) {
		}

	}

	private Dialog dialogSelectImage;

	public void showDialog(Activity ac) {
		if (!ac.isFinishing()) {
			dialogSelectImage = new Dialog(ac);
			dialogSelectImage.getWindow();
			dialogSelectImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialogSelectImage.setContentView(R.layout.chochay);
			dialogSelectImage.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			ImageView img = (ImageView) dialogSelectImage
					.findViewById(R.id.webView1);
			img.setBackgroundResource(R.drawable.cho_chay);
			AnimationDrawable frameAnimation = (AnimationDrawable) img
					.getBackground();
			frameAnimation.start();
			dialogSelectImage.setCancelable(false);
			dialogSelectImage.setCanceledOnTouchOutside(false);
			dialogSelectImage.show();
		}

	}

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

	public void callServer(final Activity activity,
			HashMap<String, String> parameters, final boolean showPro,
			boolean isPost, final JsonObjectInterface jsonObjectInterface,
			final boolean isGetJsonArray) {
		HotdealUtilities.showALog(parameters.toString());
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
			urlServer = makeUrl(parameters, ConstantValue.URL_SERVER + "/"
					+ parameters.remove(ConstantValue.API));
		}
		jsonObjRequest = new VolleyRequestCustom(method, urlServer, parameters,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						HotdealUtilities.showALog(response);
						if (isGetJsonArray) {
							JSONArray jsonResponse = null;
							try {
								jsonResponse = new JSONArray(response);
							} catch (Exception e) {
								e.printStackTrace();
							}
							jsonObjectInterface.callResultJAr(activity,
									jsonResponse);
						} else {
							JSONObject jsonResponse = null;
							try {
								jsonResponse = new JSONObject(response);
								setMessage(jsonResponse.getString(KEY_MESSAGE)
										.toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							jsonObjectInterface.callResultJOb(activity,
									jsonResponse);
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
						HotdealUtilities.showALog(error.getMessage());

						jsonObjectInterface.callResultJOb(activity, null);
						if (showPro)
							stopProgress();
					}
				});

		// Set a retry policy in case of SocketTimeout & ConnectionTimeout
		// Exceptions. Volley does retry for you if you have specified the
		// policy.

		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(12000, 0,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

	public void getProvice(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_CITY);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {

					}

					@Override
					public void callResultJAr(Context activity, JSONArray result) {
						handleProvice(result, notifyDataListener);

					}
				}, true);
	}

	public void handleProvice(JSONArray result,
			final NotifyDataListener notifyDataListener) {
		try {
			if (null != result) {
				getListProvices().clear();
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataProvince(jSonOb);
					getListProvices().add(md);
				}
				sm.setProviceJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK,
						ConstantValue.GET_CITY);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
						ConstantValue.GET_CITY);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
					ConstantValue.GET_CITY);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listDistrict = new ArrayList<>();

	public void getDistrict(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener, String proUD) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_DIS);
		builder.put("proID", proUD);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {

					}

					@Override
					public void callResultJAr(Context activity, JSONArray result) {
						handleDistrict(result, notifyDataListener);

					}
				}, true);
	}

	public void handleDistrict(JSONArray result,
			final NotifyDataListener notifyDataListener) {
		try {
			getListDistrict().clear();
			if (null != result) {
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataDistrict(jSonOb);
					getListDistrict().add(md);
				}
				sm.setDisJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK,
						ConstantValue.GET_DIS);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
						ConstantValue.GET_DIS);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
					ConstantValue.GET_DIS);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listWard = new ArrayList<>();

	public void getWard(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_WARD);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {

					}

					@Override
					public void callResultJAr(Context activity, JSONArray result) {
						handleWard(result, notifyDataListener);

					}
				}, true);
	}

	public void handleWard(JSONArray result,
			final NotifyDataListener notifyDataListener) {
		try {
			getListWard().clear();
			if (null != result) {
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataWard(jSonOb);
					getListWard().add(md);
				}
				sm.setWardJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK,
						ConstantValue.GET_WARD);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
						ConstantValue.GET_WARD);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
					ConstantValue.GET_WARD);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listStreet = new ArrayList<>();

	public void getStreet(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_STREET);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {

					}

					@Override
					public void callResultJAr(Context activity, JSONArray result) {
						handleStreet(result, notifyDataListener);

					}
				}, true);
	}

	public void handleStreet(JSONArray result,
			final NotifyDataListener notifyDataListener) {
		try {
			getListStreet().clear();
			if (null != result) {
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataStreet(jSonOb);
					getListStreet().add(md);
				}
				sm.setStreetJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK,
						ConstantValue.GET_STREET);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
						ConstantValue.GET_STREET);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
					ConstantValue.GET_STREET);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listKhuvuc = new ArrayList<>();

	public void getKhuvuc(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_KHUVUC);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {

					}

					@Override
					public void callResultJAr(Context activity, JSONArray result) {
						handleKV(result, notifyDataListener);

					}
				}, true);
	}

	public void handleKV(JSONArray result,
			final NotifyDataListener notifyDataListener) {
		try {
			getListKhuvuc().clear();
			if (null != result) {
				for (int i = 0; i < result.length(); i++) {
					JSONObject jSonOb = new JSONObject();
					jSonOb = result.getJSONObject(i);
					VrealModel md = new VrealModel();
					md.setDataWard(jSonOb);
					getListKhuvuc().add(md);
				}
				sm.setKhuVucJson(result.toString());
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK,
						ConstantValue.GET_KHUVUC);
			} else {
				notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
						ConstantValue.GET_KHUVUC);
			}

		} catch (Exception e) {
			notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED,
					ConstantValue.GET_KHUVUC);
			e.printStackTrace();
		}
	}

	private ArrayList<VrealModel> listTypeProperty = new ArrayList<>();

	public void getTypeProperty(Activity activity, boolean showPro,
			boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_TYPE);
		// builder.put("proID", proUD);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {
						try {
							getListTypeProperty().clear();
							if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
								JSONArray listJson;
								listJson = result
										.getJSONArray("RealNewsTypeList");
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									VrealModel md = new VrealModel();
									md.setTypeProperty(jSonOb);
									getListTypeProperty().add(md);
								}
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_OK,
										ConstantValue.GET_TYPE);
							} else {
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_FAILED,
										ConstantValue.GET_TYPE);
							}

						} catch (Exception e) {
							notifiUI(notifyDataListener,
									NotifyDataListener.NOTIFY_FAILED,
									ConstantValue.GET_TYPE);
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

	public void getLoaiNhaDat(Activity activity, boolean showPro,
			boolean isPost, final NotifyDataListener notifyDataListener,
			String idType) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_LOAINHADAT);
		builder.put("typeID", idType);
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {
						try {
							getListLoainhadat().clear();
							if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
								JSONArray listJson;
								listJson = result
										.getJSONArray("RealNewsCateList");
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									VrealModel md = new VrealModel();
									md.setLoaiNhaDat(jSonOb);
									getListLoainhadat().add(md);
								}
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_OK,
										ConstantValue.GET_LOAINHADAT);
							} else {
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_FAILED,
										ConstantValue.GET_LOAINHADAT);
							}

						} catch (Exception e) {
							notifiUI(notifyDataListener,
									NotifyDataListener.NOTIFY_FAILED,
									ConstantValue.GET_LOAINHADAT);
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

	public void getHuong(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, ConstantValue.GET_HUONG);
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {
						try {
							getListHuong().clear();
							if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
								JSONArray listJson;
								listJson = result.getJSONArray("DirectionList");
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									VrealModel md = new VrealModel();
									md.setHuong(jSonOb);
									getListHuong().add(md);
								}
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_OK,
										ConstantValue.GET_HUONG);
							} else {
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_FAILED,
										ConstantValue.GET_HUONG);
							}

						} catch (Exception e) {
							notifiUI(notifyDataListener,
									NotifyDataListener.NOTIFY_FAILED,
									ConstantValue.GET_HUONG);
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

	public void seach(Activity activity, boolean showPro, boolean isPost,
			final NotifyDataListener notifyDataListener, String typeID,
			String cateID, String province, String districtId, String ward,
			String street, String homeDirection, String project,
			String AcreageStart, String AcreageEnd, String NoOfRoom,
			String NoOfRestRoom, String PriceFrom, String PriceTo,
			int pageIndex, int pageSize) {
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
		builder.put("AcreageStart", AcreageStart);
		builder.put("AcreageEnd", AcreageEnd);
		builder.put("NoOfRoom", NoOfRoom);
		builder.put("NoOfRestRoom", NoOfRestRoom);
		builder.put("PriceFrom", PriceFrom);
		builder.put("PriceTo", PriceTo);
		builder.put("pageIndex", pageIndex + "");
		builder.put("pageSize", pageSize + "");
		callServer(activity, builder, showPro, isPost,
				new JsonObjectInterface() {

					@Override
					public void callResultJOb(Context activity,
							JSONObject result) {
						try {
							getListSearch().clear();
							if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
								JSONArray listJson;
								listJson = result.getJSONArray("RealsNewsList");
								for (int i = 0; i < listJson.length(); i++) {
									JSONObject jSonOb = new JSONObject();
									jSonOb = listJson.getJSONObject(i);
									VrealModel md = new VrealModel();
									md.setHuong(jSonOb);
									getListSearch().add(md);
								}
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_OK,
										ConstantValue.SEARCG);
							} else {
								notifiUI(notifyDataListener,
										NotifyDataListener.NOTIFY_FAILED,
										ConstantValue.SEARCG);
							}

						} catch (Exception e) {
							notifiUI(notifyDataListener,
									NotifyDataListener.NOTIFY_FAILED,
									ConstantValue.SEARCG);
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
}
