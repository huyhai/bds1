package com.hotdealapp.ui2;

import com.hotdeal.libs.SessionManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class VrealFragment extends Fragment {
	protected SessionManager sm;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sm=new SessionManager(getActivity());
	}

}
