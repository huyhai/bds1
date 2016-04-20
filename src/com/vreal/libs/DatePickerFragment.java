package com.vreal.libs;

import java.util.Calendar;
import java.util.Date;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {
	private NotifySomesDataListener notifi;

	public DatePickerFragment() {
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		DatePickerDialog da;
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE, -1);
		da=new DatePickerDialog(getActivity(), this, year, (month), day);
		da.getDatePicker().setMaxDate(c.getTimeInMillis());
		// Create a new instance of DatePickerDialog and return it
		return da;
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		if (notifi != null) {
			notifi.onReturnDataString(String.valueOf(HotdealUtilities.getLongFromDate(day + "-" + (month+1) + "-" + year, "dd-MM-yyyy")));
		}
	}
}