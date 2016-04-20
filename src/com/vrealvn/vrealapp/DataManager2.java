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

	}

	public void showProgress(Activity activity) {
		isShowPro = true;
		try {
			showDialog(activity);
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

	public void callServer(final Activity activity, HashMap<String, String> parameters, final boolean showPro, boolean isPost, final JsonObjectInterface jsonObjectInterface) {
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
			urlServer = makeUrl(parameters, ConstantValue.URL_SERVER + "/" + parameters.remove(ConstantValue.API));
		}
		jsonObjRequest = new VolleyRequestCustom(method, urlServer, parameters, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					try {
						setMessage(response.getString(KEY_MESSAGE).toString());
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

	private void notifiUI(NotifyDataListener no, int value) {
		if (no != null) {
			no.onNotify(value);
		}
	}

	private ArrayList<VrealModel> listProvices = new ArrayList<>();

	public void getProvice(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, "V_Province_GetAll");
		builder.put("search", "");
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("ProvinceList");
						getListProvices().clear();
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setDataProvince(jSonOb);
							getListProvices().add(md);
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

	private ArrayList<VrealModel> listDistrict = new ArrayList<>();

	public void getDistrict(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener, String proUD) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, "V_District_GetByProvinceID");
		builder.put("proID", proUD);
		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {

			@Override
			public void callResultJOb(Context activity, JSONObject result) {
				try {
					getListDistrict().clear();
					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
						JSONArray listJson;
						listJson = result.getJSONArray("DistrictList");
						for (int i = 0; i < listJson.length(); i++) {
							JSONObject jSonOb = new JSONObject();
							jSonOb = listJson.getJSONObject(i);
							VrealModel md = new VrealModel();
							md.setDataDistrict(jSonOb);
							getListDistrict().add(md);
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

	private ArrayList<VrealModel> listTypeProperty = new ArrayList<>();

	public void getTypeProperty(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
		HashMap<String, String> builder = new HashMap<>();
		builder.put(ConstantValue.API, "V_RealNewsType_GetAll");
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

//	private ArrayList<VrealModel> listTypeProperty = new ArrayList<>();
//
//	public void getTypeProperty(Activity activity, boolean showPro, boolean isPost, final NotifyDataListener notifyDataListener) {
//		HashMap<String, String> builder = new HashMap<>();
//		builder.put(ConstantValue.API, "V_RealNewsType_GetAll");
//		// builder.put("proID", proUD);
//		callServer(activity, builder, showPro, isPost, new JsonObjectInterface() {
//
//			@Override
//			public void callResultJOb(Context activity, JSONObject result) {
//				try {
//					getListTypeProperty().clear();
//					if (result.getInt(KEY_ERROR) == ConstantValue.SUCCESS) {
//						JSONArray listJson;
//						listJson = result.getJSONArray("RealNewsTypeList");
//						for (int i = 0; i < listJson.length(); i++) {
//							JSONObject jSonOb = new JSONObject();
//							jSonOb = listJson.getJSONObject(i);
//							VrealModel md = new VrealModel();
//							md.setTypeProperty(jSonOb);
//							getListTypeProperty().add(md);
//						}
//						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_OK);
//					} else {
//						notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
//					}
//
//				} catch (Exception e) {
//					notifiUI(notifyDataListener, NotifyDataListener.NOTIFY_FAILED);
//					e.printStackTrace();
//				}
//
//			}
//		});
//	}

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
}
