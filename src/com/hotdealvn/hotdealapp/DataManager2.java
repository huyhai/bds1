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
		return parameters;
	}
	
}
