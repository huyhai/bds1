package com.vreal.libs;

import com.vreal.model.VrealModel;

public interface NotifyVreal {
	
	public static final int NOTIFY_OK = 0;
	public static final int NOTIFY_FAILED = 1;
	
	public void onNotify(VrealModel md);

}
