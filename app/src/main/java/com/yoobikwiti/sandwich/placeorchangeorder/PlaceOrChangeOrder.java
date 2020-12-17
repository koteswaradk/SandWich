package com.yoobikwiti.sandwich.placeorchangeorder;

import java.util.ArrayList;
import java.util.Calendar;

import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;
import com.yoobikwiti.sandwich.smssend.receiver.SmsDeliveredReceiver;
import com.yoobikwiti.sandwich.smssend.receiver.SmsSentReceiver;
import com.yoobikwiti.sandwich.takeorder.TakeOrder;
import com.yoobikwiti.sandwich.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PlaceOrChangeOrder extends Activity implements OnClickListener{
	EditText _date,_time,_questioneries;
	static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;
    private int year, month, day;
    private TextView dateDisplay;
    private TextView timeDisplay;
    private int hours, min;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placeor_confirm_order);
		findViewById(R.id.button_home).setOnClickListener(this);
		findViewById(R.id.button_change_order).setOnClickListener(this);
		findViewById(R.id.button_confirm_order).setOnClickListener(this);
		_date=(EditText) findViewById(R.id.date);
		_time=(EditText) findViewById(R.id.time);
		_date.setFocusable(false);
		_time.setFocusable(false);
		_questioneries=(EditText) findViewById(R.id.questioneries_et);
		_date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        updateDate();
		_time.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
		});
		 hours = cal.get(Calendar.HOUR);
	        min = cal.get(Calendar.MINUTE);

	        updateTime();
		
	}

	private void updateTime() {
		// TODO Auto-generated method stub
		_time.setText(new StringBuilder().append(hours).append(':')
	                .append(min));
	}

	private void updateDate() {
		// TODO Auto-generated method stub
		_date.setText(new StringBuilder().append(day).append('-')
	                .append(month + 1).append('-').append(year));
	}
	private DatePickerDialog.OnDateSetListener dateListener = 
	        new DatePickerDialog.OnDateSetListener() {

	            @Override
	            public void onDateSet(DatePicker view, int yr, int monthOfYear,
	                    int dayOfMonth) {
	                year = yr;
	                month = monthOfYear;
	                day = dayOfMonth;
	                updateDate();
	            }
	    };

	    private TimePickerDialog.OnTimeSetListener timeListener = 
	        new TimePickerDialog.OnTimeSetListener() {

	            @Override
	            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	                hours = hourOfDay;
	                min = minute;
	                updateTime();
	            }

	    };
	    protected Dialog onCreateDialog(int id){
	        switch(id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, R.style.DialogUplatform,dateListener, year, month, day);
	        case TIME_DIALOG_ID:
	            return new TimePickerDialog(this, R.style.DialogUplatform,timeListener, hours, min, false);
	        }
	        return null;

	    }
	    private void sendSMS(String phoneNumber, String message) {
	        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
	        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
	        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	                new Intent(this, SmsSentReceiver.class), 0);
	        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	                new Intent(this, SmsDeliveredReceiver.class), 0);
	        try {
	            SmsManager sms = SmsManager.getDefault();
	            ArrayList<String> mSMSMessage = sms.divideMessage(message);
	            for (int i = 0; i < mSMSMessage.size(); i++) {
	                sentPendingIntents.add(i, sentPI);
	                deliveredPendingIntents.add(i, deliveredPI);
	            }
	            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
	                    sentPendingIntents, deliveredPendingIntents);

	        } catch (Exception e) {

	            e.printStackTrace();
	            Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
	        }

	    }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_home:
			startActivity(new Intent(this, SalesGirlsSearch.class));
			
			break;
		case R.id.button_change_order:
			startActivity(new Intent(this, TakeOrder.class));
		
			break;
		case R.id.button_confirm_order:
			/*if (_date.length() <= 0) {
				_date.setError(null);
				_date.setError("Enter the date");

		} else if (_time.length() <= 0) {
			_date.setError(null);
			_time.setError("Enter the time");

		}else if (_questioneries.length() <= 0) {
			_time.setError(null);
			
			_questioneries.setError("Enter the questioneries");
			
		} else {*/
			LayoutInflater inflater = getLayoutInflater();
			 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));
			 layout.setBackgroundColor(R.color.red);
			 TextView text = (TextView) layout.findViewById(R.id.text);
			 text.setText("your order is confirmed");
			 text.setTextSize(16);
			 Toast toast = new Toast(getApplicationContext());
			 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			 toast.setDuration(Toast.LENGTH_LONG);
			 toast.setView(layout);
			 toast.show();
			// sendSMS("9242128623", "smstest");
			startActivity(new Intent(this, SalesGirlsSearch.class));
		
		//}
			break;

		
		}
		
	}

}
