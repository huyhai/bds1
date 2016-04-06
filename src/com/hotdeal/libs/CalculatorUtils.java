package com.hotdeal.libs;

import java.util.ArrayList;

import com.hotdeal.model.DetailsModel;

public class CalculatorUtils {
	private static int tongSp = 0;
	private static int tongTam = 0;
	private static int tong = 0;

	public static boolean preOderGetCalc(ArrayList<DetailsModel> list) {
		setTongSp(0);
		setTongTam(0);
		setTong(0);
		try {
			for (DetailsModel md : list) {
				setTongSp(getTongSp() + md.getQuantityUserChoosen());
				setTongTam(getTongTam() + Integer.parseInt(md.getPrice())
						* md.getQuantityUserChoosen());
				setTong(getTong() + Integer.parseInt(md.getPrice()) * md.getQuantityUserChoosen());
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//	public static boolean deliveryInfoGetCalc(DetailsModel md) {
//		setTongSp(0);
//		setTongTam(0);
//		setTong(0);
//		try {
//			setTongSp(getTongSp() + md.getQuantityUserChoosen());
//			setTongTam(getTongTam() + Integer.parseInt(md.getPrice()) * md.getQuantityUserChoosen());
//			setTong(getTong() + Integer.parseInt(md.getPrice()) * md.getQuantityUserChoosen());
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}

	/**
	 * @return the tongSp
	 */
	public static int getTongSp() {
		return tongSp;
	}

	/**
	 * @param tongSp
	 *            the tongSp to set
	 */
	public static void setTongSp(int tongSp) {
		CalculatorUtils.tongSp = tongSp;
	}

	/**
	 * @return the tongTam
	 */
	public static int getTongTam() {
		return tongTam;
	}

	/**
	 * @param tongTam
	 *            the tongTam to set
	 */
	public static void setTongTam(int tongTam) {
		CalculatorUtils.tongTam = tongTam;
	}

	/**
	 * @return the tong
	 */
	public static int getTong() {
		return tong;
	}

	/**
	 * @param tong
	 *            the tong to set
	 */
	public static void setTong(int tong) {
		CalculatorUtils.tong = tong;
	}
}
