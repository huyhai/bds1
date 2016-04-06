package com.hotdeal.report;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

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
import com.hotdeal.libs.HotdealUtilities;
import com.hotdealvn.hotdealapp.DataManager2;

public class ACRAPostSender implements ReportSender {
	private static String BASE_URL = "http://vanhoavietbooks.com.vn/tranhuyhai/anhgif/hotdeal_crash_report.php?email=huyhai.tran@hotdeal.vn";
	private final static String SHARED_SECRET = "g8";
	private Map<String, String> custom_data = null;
	private String mess;

	ACRAPostSender() {
	}

	public ACRAPostSender(HashMap<String, String> custom_data) {
		this.custom_data = custom_data;
	}

	public ACRAPostSender(HashMap<String, String> custom_data, String url, String me, final Activity ac) {
		this.custom_data = custom_data;
		BASE_URL = "http://tranhuyhai.zz.mu/hotdeal_feedback.php?email=" + url;
		mess = me;
		final CrashReportData a = new CrashReportData();
		a.put(ReportField.BRAND, HotdealUtilities.getDeviceName());
		a.put(ReportField.PHONE_MODEL, HotdealUtilities.getDeviceModel());
		a.put(ReportField.APP_VERSION_CODE, HotdealUtilities.getBuildVertion(ac));
		try {
			a.put(ReportField.USER_EMAIL, DataManager2.getInstance().getUserModel().getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
		a.put(ReportField.INSTALLATION_ID, HotdealUtilities.check3GorWifi(ac));

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					send(ac, a);
				} catch (ReportSenderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		thread.start();

	}

	private String getUrl() {
		String token = getToken();
		String key = getKey(token);
		HotdealUtilities.showALog(String.format("%s&token=%s&key=%s&", BASE_URL, token, key));
		return String.format("%s&token=%s&key=%s&", BASE_URL, token, key);
	}

	private String getKey(String token) {
		return md5(String.format("%s+%s", SHARED_SECRET, token));
	}

	private String getToken() {
		return md5(UUID.randomUUID().toString());
	}

	public static String md5(String s) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(s.getBytes(), 0, s.length());
		String hash = new BigInteger(1, m.digest()).toString(16);
		return hash;
	}

	@Override
	public void send(Context arg0, CrashReportData report) throws ReportSenderException {
		String url = getUrl();
		// String url1=String.format("%s&log=%s&", url,
		// report.get(ReportField.STACK_TRACE));
		// Log.e("xenim", url);

		try {

			makeSampleHttpRequest(arg0, report.get(ReportField.STACK_TRACE));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> parameters = new ArrayList<NameValuePair>();

			if (custom_data != null) {
				for (Map.Entry<String, String> entry : custom_data.entrySet()) {
					parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			parameters.add(new BasicNameValuePair("DATE", new Date().toString()));
			parameters.add(new BasicNameValuePair("REPORT_ID", report.get(ReportField.REPORT_ID)));
			parameters.add(new BasicNameValuePair("APP_VERSION_CODE", report.get(ReportField.APP_VERSION_CODE)));
			parameters.add(new BasicNameValuePair("APP_VERSION_NAME", report.get(ReportField.APP_VERSION_NAME)));
			parameters.add(new BasicNameValuePair("PACKAGE_NAME", report.get(ReportField.PACKAGE_NAME)));
			// parameters.add(new BasicNameValuePair("FILE_PATH",
			// report.get(ReportField.FILE_PATH)));
			parameters.add(new BasicNameValuePair("PHONE_MODEL", report.get(ReportField.PHONE_MODEL)));
			parameters.add(new BasicNameValuePair("ANDROID_VERSION", report.get(ReportField.ANDROID_VERSION)));
			// parameters.add(new BasicNameValuePair("BUILD",
			// report.get(ReportField.BUILD)));
			parameters.add(new BasicNameValuePair("BRAND", report.get(ReportField.BRAND)));
			parameters.add(new BasicNameValuePair("PRODUCT", report.get(ReportField.PRODUCT)));
			parameters.add(new BasicNameValuePair("TOTAL_MEM_SIZE", report.get(ReportField.TOTAL_MEM_SIZE)));
			parameters.add(new BasicNameValuePair("AVAILABLE_MEM_SIZE", report.get(ReportField.AVAILABLE_MEM_SIZE)));
			parameters.add(new BasicNameValuePair("CUSTOM_DATA", report.get(ReportField.CUSTOM_DATA)));
			parameters.add(new BasicNameValuePair("STACK_TRACE", report.get(ReportField.STACK_TRACE)));
			// parameters.add(new BasicNameValuePair("INITIAL_CONFIGURATION",
			// report
			// .get(ReportField.INITIAL_CONFIGURATION)));
			// parameters.add(new BasicNameValuePair("CRASH_CONFIGURATION",
			// report
			// .get(ReportField.CRASH_CONFIGURATION)));
			// parameters.add(new BasicNameValuePair("DISPLAY",
			// report.get(ReportField.DISPLAY)));
			parameters.add(new BasicNameValuePair("USER_COMMENT", report.get(ReportField.USER_COMMENT)));
			parameters.add(new BasicNameValuePair("USER_APP_START_DATE", report.get(ReportField.USER_APP_START_DATE)));
			parameters.add(new BasicNameValuePair("USER_CRASH_DATE", report.get(ReportField.USER_CRASH_DATE)));
			// parameters.add(new BasicNameValuePair("DUMPSYS_MEMINFO", report
			// .get(ReportField.DUMPSYS_MEMINFO)));
			parameters.add(new BasicNameValuePair("DROPBOX", report.get(ReportField.DROPBOX)));
			parameters.add(new BasicNameValuePair("LOGCAT", report.get(ReportField.LOGCAT)));
			parameters.add(new BasicNameValuePair("EVENTSLOG", report.get(ReportField.EVENTSLOG)));
			parameters.add(new BasicNameValuePair("RADIOLOG", report.get(ReportField.RADIOLOG)));
			parameters.add(new BasicNameValuePair("IS_SILENT", report.get(ReportField.IS_SILENT)));
			parameters.add(new BasicNameValuePair("DEVICE_ID", report.get(ReportField.DEVICE_ID)));
			parameters.add(new BasicNameValuePair("INSTALLATION_ID", report.get(ReportField.INSTALLATION_ID)));
			// parameters
			// .add(new BasicNameValuePair("USER_EMAIL",
			// report.get(ReportField.USER_EMAIL)));
			parameters.add(new BasicNameValuePair("USER_EMAIL", HotdealUtilities.email));
			parameters.add(new BasicNameValuePair("DEVICE_FEATURES", report.get(ReportField.DEVICE_FEATURES)));
			// parameters.add(new BasicNameValuePair("ENVIRONMENT", report
			// .get(ReportField.ENVIRONMENT)));
			// parameters.add(new BasicNameValuePair("SETTINGS_SYSTEM", report
			// .get(ReportField.SETTINGS_SYSTEM)));
			// parameters.add(new BasicNameValuePair("SETTINGS_SECURE", report
			// .get(ReportField.SETTINGS_SECURE)));
			// parameters.add(new BasicNameValuePair("SHARED_PREFERENCES",
			// report
			// .get(ReportField.SHARED_PREFERENCES)));
			parameters.add(new BasicNameValuePair("APPLICATION_LOG", report.get(ReportField.APPLICATION_LOG)));
			parameters.add(new BasicNameValuePair("MEDIA_CODEC_LIST", report.get(ReportField.MEDIA_CODEC_LIST)));
			parameters.add(new BasicNameValuePair("THREAD_DETAILS", report.get(ReportField.THREAD_DETAILS)));
			parameters.add(new BasicNameValuePair("MESSAGE", mess));

			httpPost.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
			httpClient.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private RequestQueue mVolleyQueue;
	JsonObjectRequest jsonObjRequest;
	
	private void makeSampleHttpRequest(Context c, String log) {
		mVolleyQueue = Volley.newRequestQueue(c);
		String url = "http://vanhoavietbooks.com.vn/tranhuyhai/anhgif";
		Uri.Builder builder = Uri.parse(url).buildUpon();
		builder.appendQueryParameter("tag", "addCrash");
		builder.appendQueryParameter("log", log);
		
		
		jsonObjRequest = new JsonObjectRequest(Request.Method.GET, builder.toString(), null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if( error instanceof NetworkError) {
				} else if( error instanceof ClientError) { 
				} else if( error instanceof ServerError) {
				} else if( error instanceof AuthFailureError) {
				} else if( error instanceof ParseError) {
				} else if( error instanceof NoConnectionError) {
				} else if( error instanceof TimeoutError) {
				}

			}
		});
		
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("ccccc");	
		mVolleyQueue.add(jsonObjRequest);
	}

}
