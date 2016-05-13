package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginA extends Activity implements OnClickListener {
	private EditText edUser;
	private EditText edPass;
	private Button btnDangNhap;
	private TextView tvDangKi;
	
	private RelativeLayout rlContent1;
	private RelativeLayout rlContent2;
	private RelativeLayout rlContent3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		initData();
	}

	private void initView() {
		HotdealUtilities.setWH(this);
		edUser = (EditText) findViewById(R.id.edUser);
		edPass = (EditText) findViewById(R.id.edPass);
		btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
		tvDangKi = (TextView) findViewById(R.id.tvDangKi);
		rlContent1= (RelativeLayout) findViewById(R.id.rlContent1);
		rlContent2= (RelativeLayout) findViewById(R.id.rlContent2);
		rlContent3= (RelativeLayout) findViewById(R.id.rlContent3);
		
		btnDangNhap.setOnClickListener(this);
		double w= 1.5;
		double h= 12;
		HotdealUtilities.setWidthHeight(rlContent1, w, h);
		HotdealUtilities.setWidthHeight(rlContent2, w, h);
		HotdealUtilities.setWidthHeight(rlContent3, w, h);

	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		if(v==btnDangNhap){
			HotdealUtilities.startActivityClearStack(this, Main.class, "");
		}
		
	}

}
