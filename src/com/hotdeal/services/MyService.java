package com.hotdeal.services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private static final String TAG = "HAI";
	Timer time = new Timer();
	private static Context cont;
	Handler handler;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		cont = MyService.this;
		handler = new Handler();

		new MyAsyncTask().execute("");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		// because we do not want to stop the service unless we explicitly say
		// so.

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		// time.cancel();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Log.d(TAG, "onStart");

	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

		@Override
		protected Double doInBackground(String... params) {
			time.scheduleAtFixedRate(task, 10000, 40000);
			return null;
		}

		protected void onPostExecute(Double result) {
		}

		protected void onProgressUpdate(Integer... progress) {
		}

	}

	TimerTask task = new TimerTask() {

		@Override
		public void run() {

		}

	};

	private void runOnUiThread(Runnable runnable) {
		handler.post(runnable);
	}
}
