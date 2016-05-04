package com.vreal.ui2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.android.vrealapp.R;
import com.vreal.adapter.ListImagesAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifyDataListener;
import com.vreal.libs.TwoWayView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PostPro extends Fragment implements OnClickListener {
	private TextView tvAddIMG;
	private TwoWayView lvIMG;
	private ArrayList<Uri> listUri = new ArrayList<>();
	ListImagesAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.post, container, false);
		initView(rootView);
		initData();
		return rootView;
	}
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.post);
//		initView();
//		initData();
//	}

	private void initData() {
		adapter = new ListImagesAdapter(getActivity(), listUri, no);
		lvIMG.setAdapter(adapter);
		lvIMG.setItemMargin(10);
	}

	NotifyDataListener no = new NotifyDataListener() {

		@Override
		public void onNotify(String api, int id) {
			try {
				listUri.remove(id);
			} catch (Exception e) {
			}
			adapter.notifyDataSetChanged();

		}
	};

	private void initView(View rootView) {
		tvAddIMG = (TextView)rootView. findViewById(R.id.tvAddIMG);
		lvIMG = (TwoWayView) rootView.findViewById(R.id.lvIMG);
		tvAddIMG.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == tvAddIMG) {
			HotdealUtilities.getImageFromGalery(getActivity());
		}

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case HotdealUtilities.SELECT_PHOTO:
			if (resultCode == Activity.RESULT_OK) {
				Uri selectedImage = data.getData();
				listUri.add(Uri.parse("file://" + HotdealUtilities.getPath(selectedImage, getActivity())));
				adapter.notifyDataSetChanged();
				// try {
				// Bitmap yourSelectedImage =
				// HotdealUtilities.decodeUri(selectedImage, PostPro.this);
				// ImageView img = (ImageView) findViewById(R.id.aaa);
				// img.setImageBitmap(yourSelectedImage);
				// } catch (FileNotFoundException e) {
				// e.printStackTrace();
				// }
			}
		}
	}

}
