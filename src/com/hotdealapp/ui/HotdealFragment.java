package com.hotdealapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.hotdeal.db.DatabaseHandler;
import com.hotdeal.libs.SessionManager;

public class HotdealFragment extends Fragment {
	protected SessionManager sm;
	protected DatabaseHandler db;
	private FragmentActivity myAc;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		try {
			sm = new SessionManager(getActivity1());
			db = new DatabaseHandler(getActivity1());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		myAc=(FragmentActivity) activity;
	}
	public FragmentActivity getActivity1(){
		return myAc;
	}

}
