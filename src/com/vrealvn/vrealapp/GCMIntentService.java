package com.vrealvn.vrealapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.android.vrealapp.R;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;
import com.vreal.libs.ConstantValue;
import com.vreal.libs.VrealUtilities;
import com.vreal.libs.SessionManager;
import com.vreal.ui2.Main;

public class GCMIntentService extends GCMBaseIntentService {

	public static final String SENDER_ID = "";
	private SessionManager sm;

	public GCMIntentService() {
		super(SENDER_ID);
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		VrealUtilities.showALog(registrationId);
		VrealApp.regId = registrationId;
		// SplashScreen.regId = registrationId;
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("tag", "adddevice"));
		// params.add(new BasicNameValuePair("regId", registrationId));
		// UserFunctions.getInstance().add(context, params, false);
		sm = new SessionManager(context);
		String userID = "0";

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
			VrealApp.countNotify = intent.getExtras().getString("badge");
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
