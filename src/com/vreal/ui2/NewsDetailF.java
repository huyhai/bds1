package com.vreal.ui2;

import com.android.vrealapp.R;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.SessionManager;
import com.vreal.model.DetailsModel;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsDetailF extends HotDealFragmentActivity {
	private TextView tvName;
	private TextView tvDate;
	private ImageView imgPic;
	private TextView tvCondi;
	private WebView tvContent;
	private SessionManager sm;

	// private TextView tvName;

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.news_detail, container, false);
	// initView(rootView);
	// setData();
	// return rootView;
	// }
	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.news_detail);
		initView();
		setData();
	}

	private void setData() {
		try {
			sm = new SessionManager(this);
			DetailsModel md = DataManager2.getInstance().getMd();
			tvName.setText(md.getName());
			tvDate.setText(md.getIntroduce());
			tvCondi.setText(Html.fromHtml(md.getConditions()));
			// tvContent.setText(Html.fromHtml(md.getDescription()));
			HotdealUtilities.showALog(sm.getTextSize() + md.getDescription());
			// String formatHTML =
			// "<html><head><style type=\"text/css\">body {font-size: " +
			// (sm.getTextSize()) +
			// "px;} img{width: 100% !important; height: auto !important;}</style></head><body>"
			// + md.getDescription() + "</body></html>";
			// String des=md.getDescription().replaceAll("", replacement);
			// String sss="<style> h1 {font-size::21px;} </style>";
			// String av=md.getDescription().replaceAll("font-size", "");
			String formatHTML = "<html><head><style type=\"text/css\">body {font-size: "
					+ (sm.getTextSize())
					+ "px;} img{width: 100% !important; height: auto !important;}</style></head><body>"
					+ md.getDescription() + " </body></html>";
			String aa = formatHTML.replaceAll("&lt;", "<").replaceAll("&gt;",
					">");
			WebSettings webSettings = tvContent.getSettings();
			webSettings.setJavaScriptEnabled(true);
			// wv.setInitialScale(100);
			tvContent.loadDataWithBaseURL("", aa, "text/html", "UTF-8", "");
			tvContent.post(new Runnable() {

				@Override
				public void run() {
					tvContent.setInitialScale(100);
					tvContent.invalidate();

				}
			});
			// HotdealUtilities.loadImage(md.getImage(), imgPic, getActivity());
		} catch (Exception e) {
		}

	}

	private void initView() {
		tvName = (TextView) findViewById(R.id.tvName);
		tvDate = (TextView) findViewById(R.id.tvDate);
		imgPic = (ImageView) findViewById(R.id.imgPic);
		tvCondi = (TextView) findViewById(R.id.tvCondi);
		tvContent = (WebView) findViewById(R.id.tvContent);
		imgTooggle=(ImageView)findViewById(R.id.imgTooggle);
		tvContent.setInitialScale(100);
		llTopBar = (LinearLayout) findViewById(R.id.llTopBar);
		HotdealUtilities.setHeight(llTopBar, 11.5);
		HotdealUtilities.setWidthHeight(imgTooggle, 17, 35);
		findViewById(R.id.rlToogle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NewsDetailF.this.finish();

			}
		});
		// tvName = (TextView) rootView.findViewById(R.id.tvName);

	}
}
