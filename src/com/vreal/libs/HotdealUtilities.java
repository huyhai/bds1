package com.vreal.libs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.maps.model.LatLng;
import com.android.vrealapp.R;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vreal.adapter.PopupAdapter;
import com.vreal.db.DatabaseHandler;
import com.vreal.model.CateSildeModel;
import com.vreal.model.VrealModel;
import com.vreal.ui2.Main;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;
import com.vrealvn.vrealapp.HotdealApp;

public class HotdealUtilities {
	private static SessionManager sm;
	private static DatabaseHandler db;
	public static String FORMAT_DATE = "dd/MM/yyyy";

	public static void addLogOut() {
	}

	// public static void setActionDeeplink(Activity ac, String url) {
	// String action = "";
	// String value = "";
	// String subvalue = "";
	// try {
	// Uri uri = Uri.parse(url);
	// action = uri.getQueryParameter("action");
	// value = uri.getQueryParameter("value");
	// subvalue = uri.getQueryParameter("subvalue");
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	//
	// try {
	//
	// if (action.equals(ConstantValue.MOTRANGCHU)) {
	// if ("newdeal".equals(value)) {
	// HotdealUtilities.startActivityClearStack(ac, MainV2.class, "0");
	// } else if ("topdeal".equals(value)) {
	// HotdealUtilities.startActivityClearStack(ac, MainV2.class, "1");
	// } else {
	// HotdealUtilities.startActivityClearStack(ac, MainV2.class, "0");
	// }
	//
	// } else if (action.equals(ConstantValue.MODETAIL)) {
	// HotdealUtilities.startActivity(ac, DetailProductActivity.class, value);
	// } else if (action.equals(ConstantValue.MOCATE)) {
	// if (null == subvalue || subvalue.equals("") || "null".equals(subvalue)) {
	// HotdealUtilities.startActivity(ac, CategoryActivity.class, value + "-" +
	// "0");
	// } else {
	// HotdealUtilities.startActivity(ac, CategoryActivity.class, value + "-" +
	// subvalue);
	// }
	//
	// } else if (action.equals(ConstantValue.MOEVOUCHER)) {
	// HotdealUtilities.startActivity(ac, EvoucherListActivity.class, "");
	//
	// } else if (action.equals(ConstantValue.MOPROFILE)) {
	// HotdealUtilities.startActivity(ac, ProfilesFragment.class, "");
	//
	// } else if (action.equals(ConstantValue.MODIEMTICHLUY)) {
	// HotdealUtilities.startActivity(ac, RewardPointActivity.class, "");
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static String getFacebookImage(JSONObject ob) {
		JSONObject data = null;
		String url = "";
		try {
			data = ob.getJSONObject("data");
			url = data.getString("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;

	}

	public static String getDataFragment(Fragment f) {
		String data = "";
		try {
			data = f.getArguments().getString(ConstantValue.DATA_FRAGMENT);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}

	public static LatLng getCurrentLocation(Activity ac) {
		// check if GPS enabled
		GPSTracker gps = new GPSTracker(ac);
		LatLng srcGeoPoint;
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			srcGeoPoint = new LatLng(latitude, longitude);
		} else {
			// gps.showSettingsAlert();
			double latitude = -1;
			double longitude = -1;
			srcGeoPoint = new LatLng(latitude, longitude);
		}
		return srcGeoPoint;
	}

	public static boolean checkGPSEnable(Activity ac) {
		GPSTracker gps = new GPSTracker(ac);
		if (gps.canGetLocation()) {
			return true;
		} else {
			gps.showSettingsAlert();
			return false;
		}
	}

	public static void moveToolbarTop(float toTranslationY, final View view, final View sc, final Activity ac) {
		if (ViewHelper.getTranslationY(view) == toTranslationY) {
			return;
		}
		ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(view), toTranslationY).setDuration(200);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float translationY = (float) animation.getAnimatedValue();

				ViewHelper.setTranslationY(view, translationY);
				// ViewHelper.setTranslationY((View) sc, -translationY);

				RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ((View) sc).getLayoutParams();
				// HotdealUtilities.showALog(Math.abs(translationY)+"-"+getScreenHeight(ac)+"-"+lp.topMargin);
				// lp.height = getScreenHeight(ac) ;
				// lp.height = (int) (Math.abs(translationY))+
				// getScreenHeight(ac) - lp.topMargin;
				lp.height = (int) -translationY + getScreenHeight(ac) - lp.topMargin;
				((View) sc).requestLayout();
			}
		});
		animator.start();
	}

	public static void moveToolbarTopLL(float toTranslationY, final View view, final View sc, final Activity ac) {
		if (ViewHelper.getTranslationY(view) == toTranslationY) {
			return;
		}
		ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(view), toTranslationY).setDuration(200);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float translationY = (float) animation.getAnimatedValue();
				ViewHelper.setTranslationY(view, translationY);
				ViewHelper.setTranslationY((View) sc, 0);
				FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) ((View) sc).getLayoutParams();
				// HotdealUtilities.showALog(Math.abs(translationY)+"-"+getScreenHeight(ac)+"-"+lp.topMargin);
				// lp.height = getScreenHeight(ac) ;
				// lp.height = (int) (Math.abs(translationY))+
				// getScreenHeight(ac) - lp.topMargin;
				lp.height = (int) -translationY + getScreenHeight(ac) - lp.topMargin;
				((View) sc).requestLayout();
			}
		});
		animator.start();
	}

	protected static int getScreenHeight(Activity ac) {
		return ac.findViewById(android.R.id.content).getHeight();
	}

	public static void hideKb(Activity ac, View v) {
		InputMethodManager imm = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
	}

	public static AlertDialog showDialogConfirm(Activity ac, final String message, final NotifySomesDataListener notify, String okbutton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ac);
		builder.setTitle(ac.getString(R.string.app_name));
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage(message);
		builder.setPositiveButton(okbutton, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int id) {
				if (notify != null) {
					notify.onReturnData(0);
				}
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				if (notify != null) {

				}
				dialog.dismiss();
			}
		});

		AlertDialog alert = builder.create();
		return alert;
	}

	public static void loadImage(String url, ImageView img, Activity ac) {
		if (Main.imageLoader != null) {
		} else {
			Main.imageLoader = ImageLoader.getInstance();
			Main.imageLoader.init(ImageLoaderConfiguration.createDefault(ac));
			// options = new DisplayImageOptions.Builder()
			// .showImageForEmptyUrl(placeholder)
			// .showStubImage(placeholder).cacheOnDisc()
			// .decodingType(DecodingType.MEMORY_SAVING).build();
			Main.options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.img_thumb).showImageForEmptyUri(R.drawable.img_thumb).showImageOnFail(R.drawable.img_thumb)
					.cacheInMemory(true).cacheOnDisc().considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		}
		Main.imageLoader.displayImage(url, img, Main.options);
		// HotDealFragmentActivity.mImageLoader.get(url, new
		// com.hotdeal.libs.FadeInImageListener(img, ac));
	}

	public static Boolean checkSDT(String num) {
		try {
			String start = num.substring(0, 2);
			if (start.equals("09") || start.equals("01")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static String getStatusDH(String stt) {
		if (stt.equals(ConstantValue.THANHTOANTHATBAI)) {
			return "Thanh toán thất bại";
		} else if (stt.equals(ConstantValue.HOANTAT)) {
			return "Hoàn tất";
		} else if (stt.equals(ConstantValue.MOIDAT)) {
			return "Mới đặt";
		} else if (stt.equals(ConstantValue.DAXACNHAN)) {
			return "Đã xác nhận";
		} else if (stt.equals(ConstantValue.DANGGIAHANG)) {
			return "Đang giao hàng";
		} else if (stt.equals(ConstantValue.HUY)) {
			return "Hủy";
		} else if (stt.equals(ConstantValue.GOILAN1)) {
			return "Gọi lần 1";
		} else if (stt.equals("E")) {
			return "Gọi lần 2";
		} else if (stt.equals("G")) {
			return "Gọi lần 3";
		} else if (stt.equals(ConstantValue.CHOXACNHAN)) {
			return "Chờ xác nhận giao dịch";
		} else if (stt.equals(ConstantValue.MOIDAT_DATHANHTOAN)) {
			return "Mới đặt (đã thanh toán)";
		} else if (stt.equals("J")) {
			return "Hẹn";
		} else if (stt.equals("H")) {
			return "Nhận tại văn phòng";
		} else if (stt.equals("M")) {
			return "Chuyển tiếp";
		} else if (stt.equals("T")) {
			return "Chờ kế toán xác nhận";
		} else if (stt.equals("Q")) {
			return "Chờ hủy";
		} else if (stt.equals("Y")) {
			return "Gửi lại mã kích hoạt";
		} else if (stt.equals("N")) {
			return "Trả hàng";
		} else if (stt.equals("Z")) {
			return "Kích hoạt";
		} else if (stt.equals("P")) {
			return "Đã thanh toán";
		} else if (stt.equals("D")) {
			return "Từ chối bởi công ty";
		} else if (stt.equals("B")) {
			return "Backordered";
		} else {
			return stt;
		}
	}

	public static String getQuery(HashMap<String, String> parameters) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		// result.append("?token=" + this.token);
		for (String key : parameters.keySet()) {

			if (first) {
				first = false;
				result.append("?");
			}

			result.append("&");

			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(parameters.get(key), "UTF-8"));
		}
		HotdealUtilities.showLog(result.toString());
		return result.toString();
	}

	public static void sendMessage(Context cont, String Action, String idCate) {
		Intent intent = new Intent(Action);
		intent.putExtra(ConstantValue.IS_SUCCESS, idCate);
		cont.sendBroadcast(intent);
	}

	public static void showDialogDownloadGGPlay(final String message, final Activity ac) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ac);
		builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intentMarket = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstantValue.LINK_GOOGLE_PLAY_SERVICE));
				ac.startActivity(intentMarket);
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				ac.finish();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@SuppressWarnings("deprecation")
	public static void setWH(Activity ac) {
		Display display = ac.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		HotdealApp.device_height = height;
		HotdealApp.device_width = width;
	}

	public static void setHeight(View paramView, double paramDouble) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.height = (int) (HotdealApp.device_height / paramDouble);
		paramView.setLayoutParams(localLayoutParams);
	}

	public static void setHeightHardCode(View paramView, double paramDouble) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.height = (int) paramDouble;
		paramView.setLayoutParams(localLayoutParams);
		paramView.requestLayout();
	}

	public static void setWidth(View paramView, double paramDouble) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.width = (int) (HotdealApp.device_width / paramDouble);
		paramView.setLayoutParams(localLayoutParams);
	}

	public static void setWidthHardCode(View paramView, double paramDouble) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.width = (int) paramDouble;
		paramView.setLayoutParams(localLayoutParams);
	}

	public static void setWidthHeight(View paramView, double paramDouble1, double paramDouble2) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.width = (int) (HotdealApp.device_width / paramDouble1);
		localLayoutParams.height = (int) (HotdealApp.device_height / paramDouble2);
		paramView.setLayoutParams(localLayoutParams);
	}

	public static void setWidthHeightHardCode(View paramView, double paramDouble1, double paramDouble2) {
		ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.width = (int) paramDouble1;
		localLayoutParams.height = (int) paramDouble2;
		paramView.setLayoutParams(localLayoutParams);
	}

	public static Bitmap drawTextToBitmap(Context mContext, int hinh, String mText) {
		try {
			Resources resources = mContext.getResources();
			float scale = resources.getDisplayMetrics().density;
			Bitmap bitmap = BitmapFactory.decodeResource(resources, hinh);
			android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
			// set default bitmap config if none
			if (bitmapConfig == null) {
				bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
			}
			// resource bitmaps are imutable,
			// so we need to convert it to mutable one
			bitmap = bitmap.copy(bitmapConfig, true);
			Canvas canvas = new Canvas(bitmap);
			// new antialised Paint
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			// text color - #3D3D3D
			paint.setColor(Color.WHITE);
			// text size in pixels
			paint.setTextSize((int) (12 * scale));
			// text shadow
			paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
			// draw text to the Canvas center
			Rect bounds = new Rect();
			paint.getTextBounds(mText, 0, mText.length(), bounds);
			int x = (bitmap.getWidth() - bounds.width()) / 6;
			int y = (bitmap.getHeight() + bounds.height()) / 5;
			canvas.drawText(mText, x * scale, y * scale, paint);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static void getTotalHeightofListView(ListView listView) {

		ListAdapter mAdapter = listView.getAdapter();

		int totalHeight = 0;

		for (int i = 0; i < mAdapter.getCount(); i++) {
			View mView = mAdapter.getView(i, null, listView);

			mView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),

			MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

			totalHeight += mView.getMeasuredHeight();
			Log.w("HEIGHT" + i, String.valueOf(totalHeight));

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();

	}

	public static void setListViewHeightBasedOnChildren(GridView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		int tt = totalHeight / 2;
		params.height = tt + (listView.getVerticalSpacing() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public static void setListViewHeight(ExpandableListView listView) {
		ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
		int totalHeight = 0;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.EXACTLY);
		for (int i = 0; i < listAdapter.getGroupCount(); i++) {
			View groupItem = listAdapter.getGroupView(i, false, null, listView);
			groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += groupItem.getMeasuredHeight();
			for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
				View listItem = listAdapter.getChildView(i, j, false, null, listView);
				listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

				totalHeight += listItem.getMeasuredHeight();

			}
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
		if (height < 10)
			height = 200;
		params.height = height;
		listView.setLayoutParams(params);
		listView.requestLayout();

	}

	public static void setExpandableListViewHeightOriginal(ExpandableListView listView, int group) {
		ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
		int totalHeight = 0;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.EXACTLY);
		for (int i = 0; i < listAdapter.getGroupCount(); i++) {
			View groupItem = listAdapter.getGroupView(i, false, null, listView);
			groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

			totalHeight += groupItem.getMeasuredHeight();

			if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
				for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
					View listItem = listAdapter.getChildView(i, j, false, null, listView);
					listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

					totalHeight += listItem.getMeasuredHeight();

				}
			}
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
		if (height < 10)
			height = 200;
		params.height = height;
		listView.setLayoutParams(params);
		listView.requestLayout();

	}

	public static void showLog(Object mess) {
		try {
			Log.v("HAI", "HAILOG: " + mess);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showALog(Object mess) {
		try {
			Log.e("HAIA", "HAILOG: " + mess);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showALog(Object mess, Exception e1) {
		try {
			Log.e("HAIA", "HAILOG: " + mess, e1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String formatMoney(Object money) {
		String d = new DecimalFormat("##,##0 đ").format(money);
		String a = d.replaceAll(",", ".");
		// Locale locale = new Locale("vi_VN");
		// NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		// return format.format(money);
		return a;
	}

	public static String formatMoney(String money) {
		double money1 = Double.parseDouble(money);
		String d = new DecimalFormat("##,##0 đ").format(money1);
		String a = d.replaceAll(",", ".");

		// Locale locale = new Locale("vi_VN","");
		// NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		// return format.format(money1);
		return a;
	}

	public static Currency getCurrency() {
		// Locale l = new Locale("vi_VN", "");
		return Currency.getInstance("vi_VN");

	}

	public static boolean checkInternetConnection(final Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ((cm.getActiveNetworkInfo() != null) && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	public static String getDataString(JSONObject jSonOb, String key) {
		String result = "";
		try {
			if (!jSonOb.getString(key).equals(JSONObject.NULL)) {
				result = jSonOb.getString(key);
			}
		} catch (JSONException e) {
			// showALog("getDataStrign loi");
			// e.printStackTrace();
		}
		return result;
	}

	public static boolean getDataBool(JSONObject jSonOb, String key) {
		boolean result = false;
		try {
			result = jSonOb.getBoolean(key);
		} catch (JSONException e) {
			// showALog("getDataStrign loi");
			// e.printStackTrace();
		}
		return result;
	}

	public static String check3GorWifi(Context c) {
		ConnectivityManager manager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		// For 3G check
		boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		// For WiFi Check
		boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		if (is3g) {
			return "3G";
		} else if (isWifi) {
			return "WIFI";
		} else {
			return "UNKNOW";
		}

	}

	public static long getCurrentTime() {
		long da = 0;
		da = System.currentTimeMillis() / 1000;
		return da;
	}

	public static String md5(final String s) {
		final String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getBuildVertion(Activity ac) {
		PackageInfo pInfo;
		String version = "-1";
		try {
			pInfo = ac.getPackageManager().getPackageInfo(ac.getPackageName(), 0);
			version = pInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}

	public static String getSign(String aip, String ts) {
		try {
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void createEffectTouch(View v, final int inActiveDrawble, final int activeDrawble) {
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(activeDrawble);
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE || event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(inActiveDrawble);
				return false;
			}
		});
	}

	public static void createEffectTouchBack(View v) {
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.color.darkgrey);
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE || event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE)
					v.setBackgroundResource(R.drawable.transparent);
				return false;
			}
		});
	}

	public static void setClickAnim(final View v) {
		try {
			v.setBackgroundResource(R.color.darkgrey);
			v.post(new Runnable() {

				@Override
				public void run() {
					v.setBackgroundResource(R.drawable.transparent);

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void setClickAnimButon(final View v, final int click, final int affterclick) {
		try {
			v.setBackgroundResource(click);
			v.post(new Runnable() {

				@Override
				public void run() {
					v.setBackgroundColor(affterclick);

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void setClickAnimButon(final View v, final String color) {
		try {
			v.setBackgroundResource(R.color.darkgreylight);
			v.post(new Runnable() {

				@Override
				public void run() {
					v.setBackgroundColor(Color.parseColor(color));

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void showDialog(final String message, final Activity ac) {
		ac.runOnUiThread(new Runnable() {
			public void run() {
				showDialogOk(message, ac).show();
			}
		});
	}

	public static void showDialogCustomListView(final Context c, final ArrayList<VrealModel> listData, final NotifySomesDataListener notifi) {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(c);
		// builderSingle.setIcon(R.drawable.ic_launcher);
		// builderSingle.setTitle("Select One Name:-");

		// PopupAdapter arrayAdapter=new PopupAdapter(c, listData);
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_activated_1);
		if (null != listData) {
			for (VrealModel name : listData) {
				arrayAdapter.add(name.getProvinceName());
			}
		}

		builderSingle.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		// builderSingle.setAdapter(arrayAdapter,null);
		builderSingle.setAdapter(arrayAdapter, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				for (int i = 0; i < listData.size(); i++) {
					listData.get(i).setChoosen(false);
				}
				listData.get(which).setChoosen(true);
				// String strName =
				// listData.get(which).getProvinceName();
				// showToast(strName, Toast.LENGTH_SHORT, c);
				if (null != notifi) {
					notifi.onReturnData(which);
				}
				// AlertDialog.Builder builderInner = new
				// AlertDialog.Builder(
				// c);
				// builderInner.setMessage(strName);
				// builderInner.setTitle("Your Selected Item is");
				// builderInner.setPositiveButton("Ok",
				// new DialogInterface.OnClickListener() {
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// dialog.dismiss();
				// }
				// });
				// builderInner.show();
			}
		});
		builderSingle.show();
	}

	public static AlertDialog showDialogOk(final String message, final Activity ac) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ac);
		builder.setMessage(message).setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int id) {
				// Do nothing.
			}
		});

		AlertDialog alert = builder.create();
		builder.setTitle(ac.getString(R.string.app_name));
		builder.setIcon(R.drawable.ic_launcher);
		alert.setCancelable(false);
		alert.setCanceledOnTouchOutside(false);
		return alert;
	}

	public static void showToast(final String message, final int duration, final Activity ac) {
		ac.runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(ac, message, duration);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		});
	}

	public static void showToast(final String message, final int duration, final Context ac) {
		((Activity) ac).runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(ac, message, duration);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		});
	}

	public static void showToastThieuThongTin(final Activity ac) {
		ac.runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(ac, "Thiếu thông tin", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		});
	}

	public static void showToastNoInternet(final Activity ac) {
		ac.runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(ac, "Không có kết nối mạng, vui lòng kiểm tra lại kết nối tới internet của bạn", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		});
	}

	public static void showToastSuco(final Activity ac) {
		try {
			ac.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(ac, ac.getString(R.string.str_suco), Toast.LENGTH_SHORT).show();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void callPhone(String num, final Activity ac) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + num));
		ac.startActivity(intent);
	}

	public static String nameAc;

	public static void startActivity(Activity ac, Class<?> class1, String data) {
		if (HotdealUtilities.checkInternetConnection(ac)) {
			// mTracker = HotdealApp.getDefaultTracker(ac);
			// mTracker.setScreenName(class1.getName());
			// mTracker.send(new HitBuilders.ScreenViewBuilder().build());
			nameAc = class1.getSimpleName();
			Intent intent = new Intent(ac, class1);
			intent.putExtra(ConstantValue.DATA_ACTIVITY, data);
			ac.startActivity(intent);
		} else {
			showToast("Không có kết nối tới Internet", Toast.LENGTH_SHORT, ac);
			// HotdealUtilities.sendMessage(ac, ConstantValue.ACTION_NOINTERNET,
			// "");
		}

	}

	public static void startActivityClearStack(Activity ac, Class<?> class1, String data) {
		Intent intent = new Intent(ac, class1);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.putExtra(ConstantValue.DATA_ACTIVITY, data);
		ac.startActivity(intent);
	}

	public static void startActivityClearStack(Activity ac, Class<?> class1) {
		Intent intent = new Intent(ac, class1);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		ac.startActivity(intent);
	}

	public static void startActivityForResult(Activity ac, Class<?> class1, int request, String data) {
		Intent intent = new Intent(ac, class1);
		intent.putExtra(ConstantValue.DATA_ACTIVITY, data);
		ac.startActivityForResult(intent, request);
	}

	public static String getDataBundle(Activity ac) {
		String value = "";
		Bundle extras = ac.getIntent().getExtras();
		if (extras != null) {
			value = extras.getString(ConstantValue.DATA_ACTIVITY);
		}
		return value;
	}

	public static long getLongFromDate(String date, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date oldDate = null;
		try {
			oldDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long oldMillis = (oldDate.getTime()) / 1000;
		return oldMillis;
	}

	public static boolean isValidEmail(CharSequence target) {
		return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}

	public static String getDate(long milliSeconds, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds * 1000);
		return formatter.format(calendar.getTime());
	}

	public static String getDate1(long milliSeconds, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String dateString = formatter.format(new Date(milliSeconds * 1000));
		return dateString;
	}

	public static String getDateFromDate(Date milliSeconds, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String dateString = formatter.format(milliSeconds);
		return dateString;
	}

	// public static int dpToPx(int dp) {
	// DisplayMetrics displayMetrics =
	// HotdealApp.context.getResources().getDisplayMetrics();
	// int px = Math.round(dp * (displayMetrics.xdpi /
	// DisplayMetrics.DENSITY_DEFAULT));
	// return px;
	// }
	//
	// public static int pxToDp(int px) {
	// DisplayMetrics displayMetrics =
	// HotdealApp.context.getResources().getDisplayMetrics();
	// int dp = Math.round(px / (displayMetrics.xdpi /
	// DisplayMetrics.DENSITY_DEFAULT));
	// return dp;
	// }

	public static String email;

	public static void getEmail(Activity ac) {
		AccountManager accountManager = AccountManager.get(ac);
		Account account = getAccount(accountManager);

		if (account == null) {
			email = null;
		} else {
			email = account.name;
		}
	}

	private static Account getAccount(AccountManager accountManager) {
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0) {
			account = accounts[0];
		} else {
			account = null;
		}
		return account;
	}

	public static void wakeUpScreen(final Activity ac) {
		PowerManager pm = (PowerManager) ac.getApplicationContext().getSystemService(Context.POWER_SERVICE);
		WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
		wakeLock.acquire();
	}

	public static String getKeyHashFacebook(Activity ac) {
		String keyhash = "";
		try {
			PackageInfo info = ac.getPackageManager().getPackageInfo(ac.getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				keyhash = Base64.encodeToString(md.digest(), Base64.DEFAULT);

			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyhash;
	}

	public static String getANDROID_ID(final Context ac) {
		return Secure.getString(ac.getContentResolver(), Secure.ANDROID_ID);
	}

	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}

	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	public static String getDeviceModel() {
		return android.os.Build.MODEL;
	}

	public static int getDeviceVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	// public static void setEvent(String cate, String action, String label,
	// final Activity ac, String value1) {
	// long value = 0;
	// try {
	// value = Long.parseLong(value1);
	// } catch (Exception e) {
	// }
	// try {
	// // mTracker = HotdealApp.getDefaultTracker(ac);
	// HotdealApp.getDefaultTracker().send(
	// new HitBuilders.EventBuilder().setCategory(cate)
	// .setAction(action).setLabel(label).setValue(value)
	// .build());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// public static void setScreen(String name) {
	// try {
	// HotdealApp.getDefaultTracker().setScreenName(name);
	// HotdealApp.getDefaultTracker().send(
	// new HitBuilders.ScreenViewBuilder().build());
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	//
	// }
}
