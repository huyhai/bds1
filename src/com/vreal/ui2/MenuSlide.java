package com.vreal.ui2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vrealapp.R;
import com.vreal.adapter.MenuAdapter;
import com.vreal.libs.HotdealUtilities;
import com.vreal.libs.NotifySomesDataListener;
import com.vreal.libs.SessionManager;
import com.vreal.model.StateModel;
import com.vreal.ui.NhabanF;
import com.vrealvn.vrealapp.DataManager2;
import com.vrealvn.vrealapp.HotDealFragmentActivity;

public class MenuSlide extends Fragment implements OnItemClickListener {
	private LinearLayout llTop;
	private ListView lvMenu;
	private SessionManager sm;
	private TextView tvUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.menuslide, container, false);
		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		sm=new SessionManager(getActivity());
		llTop = (LinearLayout) rootView.findViewById(R.id.llTop);
		lvMenu = (ListView) rootView.findViewById(R.id.lvMenu);
		tvUser= (TextView) rootView.findViewById(R.id.tvUser);

		HotdealUtilities.setHeight(llTop, 5);

		ArrayList<StateModel> listData = new ArrayList<>();
		StateModel md;

		md = new StateModel();
		md.setStateName("BĐS bán");
		md.setImage(R.drawable.ic_banthuelen);
		listData.add(md);

		md = new StateModel();
		md.setStateName("BĐS cho thuê");
		md.setImage(R.drawable.ic_banthue);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Chung cư cao cấp");
		md.setImage(R.drawable.ic_caocap);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Tin tức");
		md.setImage(R.drawable.ic_tintuc);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Tìm môi giới");
		md.setImage(R.drawable.ic_moigioi);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Feedback");
		md.setImage(R.drawable.ic_fb);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Trợ giúp");
		md.setImage(R.drawable.ic_trogiup);
		listData.add(md);

		md = new StateModel();
		md.setStateName("Đăng xuất");
		md.setImage(R.drawable.ic_dangxuat);
		listData.add(md);

		MenuAdapter adapter = new MenuAdapter(getActivity(), listData, null);
		lvMenu.setAdapter(adapter);
		lvMenu.setOnItemClickListener(this);
		tvUser.post(new Runnable() {

			@Override
			public void run() {
				try {
					sm.setTextSize(tvUser.getTextSize());
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		((Main) getActivity()).closeMenu();
		if (position == 0) {
			((HotDealFragmentActivity) getActivity())
					.startFragment(new NhabanF(),"");
		} else if (position == 1) {
			((HotDealFragmentActivity) getActivity())
					.startFragment(new NhabanF(),"");
		} else if (position == 2) {
			((HotDealFragmentActivity) getActivity())
					.startFragment(new DuAnMoiF(),"");
		} else if (position == 3) {
			((HotDealFragmentActivity) getActivity())
					.startFragment(new TintucF(),"");
		} else if (position == 4) {
			((HotDealFragmentActivity) getActivity())
					.startFragment(new TimMoiGioiF(),"");
		} else if (position == 5) {
			// ((HotDealFragmentActivity) getActivity())
			// .startFragment(new TimMoiGioiF());
		} else if (position == 6) {
			// ((HotDealFragmentActivity) getActivity())
			// .startFragment(new TimMoiGioiF());
		} else if (position == 7) {
			HotdealUtilities.showDialogConfirm(getActivity(),
					"Bạn muốn đăng xuất ?", new NotifySomesDataListener() {

						@Override
						public void onReturnDataString(String id) {

						}

						@Override
						public void onReturnData(int id) {
							if (id != -1) {
								HotdealUtilities.showToast(
										"Đăng xuất thành công",
										Toast.LENGTH_SHORT, getActivity());
							}

						}
					}, "OK").show();
		}

	}

}
