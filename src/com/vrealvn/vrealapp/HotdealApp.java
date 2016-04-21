package com.vrealvn.vrealapp;

import java.util.HashMap;

import org.acra.annotation.ReportsCrashes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import org.acra.*;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.vreal.libs.HotdealUtilities;
import com.vreal.report.ACRAPostSender;
import com.android.vrealapp.R;

@ReportsCrashes()
public class HotdealApp extends Application {
	public static int device_width = 0;
	public static int device_height = 0;
	public static String cateChoosen;
	// public static boolean isLoadLocationFail = false;
	public static boolean isRunningApp;
	public static boolean isStartHome = false;
	public static boolean isShowDialog = false;
	public static boolean isLoadFilterDulich;
	// public static boolean isLoadFilter ;
	public static int height1 = 0;
	public static int height2 = 0;
	public static int height1ColCalendar = 0;
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
	private static Tracker mTracker;

	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
		HashMap<String, String> ACRAData = new HashMap<String, String>();
		ACRAData.put("App Name", getString(R.string.app_name));
		setContext(this);
		// ACRAData.put("EMAIL", HotdealUtilities.email);
//		ACRA.getErrorReporter().setReportSender(new ACRAPostSender(ACRAData));

//		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//
//			@Override
//			public void uncaughtException(Thread thread, Throwable ex) {
//				System.out.print("crash cmnr");
//				
//			}
//		});
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		HotdealApp.context = context;
	}

	/**
	 * Gets the default {@link Tracker} for this {@link Application}.
	 * 
	 * @return tracker
	 */
//	synchronized public static Tracker getDefaultTracker() {
//		if (null == mTracker) {
//			GoogleAnalytics analytics = GoogleAnalytics.getInstance(c);
//			// To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//			mTracker = analytics.newTracker(R.xml.tracker_info);
//		}
//		return mTracker;
//	}


}
