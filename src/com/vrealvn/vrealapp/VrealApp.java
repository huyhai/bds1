package com.vrealvn.vrealapp;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.Tracker;

@ReportsCrashes()
public class VrealApp extends Application {
	public static int device_width = 0;
	public static int device_height = 0;
	public static String cateChoosen;
	// public static boolean isLoadLocationFail = false;
	public static boolean isRunningApp;
	public static boolean isStartHome = false;
	public static boolean isShowDialog = false;
	// public static boolean isLoadFilter ;
	public static int height1 = 0;
	public static int height2 = 0;
	public static boolean isExpand=false;
	// public static float textsizeMediumPixel = 0;
	public static String titleAbout;
	public static String regId = "";
	public static boolean isClickPush = false;
	public static String countNotify;
	private static Context context;
	// public static String diaDiem="ĐỊA ĐIỂM";
	// public static String sapXep="SẮP XẾP";

	/**
	 * google analytics
	 */

	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
		setContext(this);
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		VrealApp.context = context;
	}

	/**
	 * Gets the default {@link Tracker} for this {@link Application}.
	 * 
	 * @return tracker
	 */


}
