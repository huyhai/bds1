package com.vreal.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		/*
		 * start service khi khoi dong app
		 */
		context.startService(new Intent(context, MyService.class));
//	      Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
		Log.v("HAI", "onReceiv111e");
//		 Intent myIntent = new Intent(context, MyService.class);
//		 context.startService(myIntent);
//		Log.v("HAI", "onReceive");

	}
}