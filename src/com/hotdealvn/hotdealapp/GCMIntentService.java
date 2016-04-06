package com.hotdealvn.hotdealapp;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

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
import com.android.volley.toolbox.Volley;
import com.android.vrealapp.R;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;
import com.hotdeal.connection.JsonObjectInterface;
import com.hotdeal.connection.VolleyRequestCustom;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;
import com.hotdeal.libs.NotifyDataListener;
import com.hotdeal.libs.SessionManager;
import com.hotdealapp.ui2.Main;

public class GCMIntentService extends GCMBaseIntentService {

	public static final String SENDER_ID = "1019062855734";
	private SessionManager sm;

	public GCMIntentService() {
		super(SENDER_ID);
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		HotdealUtilities.showALog(registrationId);
		HotdealApp.regId = registrationId;
		// SplashScreen.regId = registrationId;
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("tag", "adddevice"));
		// params.add(new BasicNameValuePair("regId", registrationId));
		// UserFunctions.getInstance().add(context, params, false);
		sm = new SessionManager(context);
		String userID = "0";
		if (DataManager2.getInstance().getUserModel().getUserID() != null) {
			userID = DataManager2.getInstance().getUserModel().getUserID();
		}

		addDevice(context, false, true, new NotifyDataListener() {

			@Override
			public void onNotify(int id) {
				// if (id == NotifyDataListener.NOTIFY_OK) {
				// HotdealUtilities.showALog("ADD DEVICE OK");
				// } else {
				// HotdealUtilities.showALog("ADD DEVICE FAIL");
				// }

			}
		}, HotdealUtilities.getANDROID_ID(context), registrationId, HotdealUtilities.getDeviceName(), HotdealUtilities.getDeviceModel(), HotdealUtilities.getDeviceVersion() + "", "active",
				HotdealUtilities.getCurrentTime() + "", "0", userID, sm.getLocationID());

	}

	public void addDevice(Context activity, boolean showPro, boolean isPost, final NotifyDataListener notifi, String device_uid, String device_token, String device_name, String device_model,
			String device_version, String status, String created, String modified, String userId, String state_id) {
		HashMap<String, String> builder = DataManager2.getInstance().getDefauldParams(ConstantValue.ADD_DEVICE,activity);
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
				// try {
				// HotdealUtilities.showALog(result.toString());
				// } catch (Exception e) {
				// // TODO: handle exception
				// }

			}
		});
	}

	public void callServer(final Context activity, HashMap<String, String> parameters, final boolean showPro, boolean isPost, final JsonObjectInterface jsonObjectInterface) {
		VolleyRequestCustom jsonObjRequest = new VolleyRequestCustom(Request.Method.POST, ConstantValue.URL_SERVER, parameters, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					HotdealUtilities.showALog(response.toString());
				} catch (Exception e) {
					// TODO: handle exception
				}

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
				} else if (error instanceof ClientError) {
				} else if (error instanceof ServerError) {
				} else if (error instanceof AuthFailureError) {
				} else if (error instanceof ParseError) {
				} else if (error instanceof NoConnectionError) {
				} else if (error instanceof TimeoutError) {
				}
				// HotdealUtilities.showALog(error.getMessage());

			}
		});

		// Set a retry policy in case of SocketTimeout & ConnectionTimeout
		// Exceptions. Volley does retry for you if you have specified the
		// policy.
		RequestQueue mVolleyQueue = Volley.newRequestQueue(activity);
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(7000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("a");
		mVolleyQueue.add(jsonObjRequest);
	}

	/**
	 * 0 Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Device unregistered");
		GCMRegistrar.setRegisteredOnServer(context, false);
		// Log.v("HAI", getString(R.string.gcm_unregistered));
		// ServerUtilities.unregister(context, registrationId);
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.i(TAG, "Received message");
		String message = "";
		String url = "";
		String screen = "";
		String value = "";
		String subvalue = "";
		try {
			message = intent.getExtras().getString("message");
			url = intent.getExtras().getString("screen");
			HotdealApp.countNotify = intent.getExtras().getString("badge");
			Uri uri = Uri.parse(url);
			screen = uri.getQueryParameter("action");
			value = uri.getQueryParameter("value");
			subvalue = uri.getQueryParameter("subvalue");
			// HotdealUtilities.showALog(screen + "|" + value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		generateNotification(context, message + "", screen + "-" + value + "-" + subvalue);
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		Log.i(TAG, "Received deleted messages notification");
		// String message = getString(R.string.gcm_deleted, total);
		// displayMessage(context, message);
		// Log.v("HAI", message);
		// notifies user
		// generateNotification(context, "aaa");
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
		// displayMessage(context, getString(R.string.gcm_error, errorId));
		// Log.v("HAI", getString(R.string.gcm_error, errorId));
		// sendMessage(context, ServerUtilities.ADDDEVICE, false);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// log message
		Log.i(TAG, "Received recoverable error: " + errorId);
		// displayMessage(context, getString(R.string.gcm_recoverable_error,
		// errorId));
		return super.onRecoverableError(context, errorId);
	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	public static void generateNotification(Context context, String message, String data) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, Main.class);
		notificationIntent.putExtra(ConstantValue.DATA_ACTIVITY, data);
		// set intent so it does not start a new activity
		// notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
		// Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// notification.sound = Uri.parse("android.resource://" +
		// context.getPackageName() + "your_sound_file_name.mp3");

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);

	}

	public static void sendMessage(Context cont, String Action, Boolean isSuccess) {
		Intent intent = new Intent(Action);
		intent.putExtra(ConstantValue.IS_SUCCESS, isSuccess);
		cont.sendBroadcast(intent);
	}

}
