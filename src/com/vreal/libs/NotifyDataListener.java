package com.vreal.libs;

public interface NotifyDataListener {
	
	public static final int NOTIFY_OK = 0;
	public static final int NOTIFY_FAILED = 1;
	
	public void onNotify(String api,int id);

}
