package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.libs.VrealUtilities;
import com.vrealvn.vrealapp.VrealFragmentActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MapActivity extends VrealFragmentActivity {

	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.map_ac);
		startFragment(new MapF(-1), "");
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		VrealUtilities.setHeight(llTopBar, 11.5);
		imgTooggle= (ImageView) this.findViewById(R.id.imgTooggle);
		VrealUtilities.setWidthHeight(imgTooggle, 17, 35);
		
		findViewById(R.id.rlToogle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapActivity.this.finish();

			}
		});
	}

}
