package com.hotdealapp.ui2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.vrealapp.R;
import com.hotdeal.adapter.ListAgentAdapter;
import com.hotdeal.libs.ConstantValue;
import com.hotdeal.libs.HotdealUtilities;

public class ListAgent extends Activity implements OnClickListener {
	private RelativeLayout rlHome;
	private LinearLayout rlToogle;
	private ListView lvEgent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_agent);
		initView();
	}

	private void initView() {
		rlHome = (RelativeLayout) this.findViewById(R.id.rlHome);
		rlToogle = (LinearLayout) this.findViewById(R.id.rlToogle);
		lvEgent = (ListView) this.findViewById(R.id.lvEgent);
		HotdealUtilities.setHeight(rlHome, ConstantValue.HEIGHT_TOP_BAR);
		HotdealUtilities.setWidth(rlToogle, ConstantValue.WIDTH_BACK);
		rlToogle.setOnClickListener(this);

		ListAgentAdapter adapter = new ListAgentAdapter(this, null, null);
		lvEgent.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		if (rlToogle == v) {
			finish();
		}

	}
}