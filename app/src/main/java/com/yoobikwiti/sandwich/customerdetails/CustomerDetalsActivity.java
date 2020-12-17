package com.yoobikwiti.sandwich.customerdetails;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.authenticate.Authenticate;
import com.yoobikwiti.sandwich.datahelper.DataHelper;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;
import com.yoobikwiti.sandwich.synchdata.SynchData;
import com.yoobikwiti.sandwich.takeorder.TakeOrder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CustomerDetalsActivity extends FragmentActivity implements OnClickListener {
	
	private AutoCompleteTextView autoComplete_textview;
	private ArrayAdapter<String> adapter;
	ImageButton button_image_search_left, cross_clear;
	FrameLayout control_frame_layout;
	boolean condotion = false;
	EditText _firstname_et, _lastname_et, _telephonenumber_et, _cellphone_et,
			_company_et, _emailid_et, _address_et, _streetnumber_et,
			_buildingnumber_et, _doornumber_et, _floornumber_et, _landmark_et,
			_deliverydescription_et;
	String s_firstname_et, s_lastname_et, s_telephonenumber_et, s_cellphone_et,
			s_company_et, s_emailid_et, s_address_et, s_streetnumber_et,
			s_buildingnumber_et, s_doornumber_et, s_floornumber_et,
			s_landmark_et, s_deliverydescription_et,s_name;
	static double territorynumber = 00000;
	String territoryString = "ttt";

	private DataHelper dh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_details);

		String[] colors = getResources().getStringArray(R.array.Vegetable_sandwich);

		/*adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, colors);*/

		/*autoComplete_textview = (AutoCompleteTextView) findViewById(R.id.autoComplete);*/
		
		button_image_search_left = (ImageButton) findViewById(R.id.image_search_left);
		
		control_frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
		
		/*findViewById(R.id.calc_clear_txt_Prise).setOnClickListener(this);*/
		
		/*button_image_search_left.setOnClickListener(this);*/
		/*autoComplete_textview.setAdapter(adapter);
		autoComplete_textview.setThreshold(1);*/
		
		
		
		_firstname_et = (EditText) findViewById(R.id.firstname_et);
		_lastname_et = (EditText) findViewById(R.id.lastname_et);
		_telephonenumber_et = (EditText) findViewById(R.id.telephonenumber_et);
		_cellphone_et = (EditText) findViewById(R.id.cellphone_et);
		_company_et = (EditText) findViewById(R.id.company_et);
		_emailid_et = (EditText) findViewById(R.id.emailid_et);
		_address_et = (EditText) findViewById(R.id.address_et);
		_streetnumber_et = (EditText) findViewById(R.id.streetnumber_et);
		_buildingnumber_et = (EditText) findViewById(R.id.buildingnumber_et);
		_doornumber_et = (EditText) findViewById(R.id.doornumber_et);
		_floornumber_et = (EditText) findViewById(R.id.floornumber_et);
		_landmark_et = (EditText) findViewById(R.id.landmark_et);
		_deliverydescription_et = (EditText) findViewById(R.id.deliverydescription_et);

		findViewById(R.id.button_synch).setOnClickListener(this);
		findViewById(R.id.button_takeorder).setOnClickListener(this);
		findViewById(R.id.button_search).setOnClickListener(this);
		/*autoComplete_textview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(
						getBaseContext(),""+ arg0.getItemAtPosition(arg2),Toast.LENGTH_LONG).show();
				
				 control_frame_layout.setVisibility(View.GONE);
			

			}
		});
		autoComplete_textview.post(new Runnable() {
			public void run() {
				autoComplete_textview.dismissDropDown();

			}
		});*/
	}

	public static boolean isEmailAddressValid(String email) {
		boolean isEmailValid = false;

		String strExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern objPattern = Pattern.compile(strExpression,
				Pattern.CASE_INSENSITIVE);
		Matcher objMatcher = objPattern.matcher(inputStr);
		if (objMatcher.matches()) {
			isEmailValid = true;
		}
		return isEmailValid;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_search:
	       Log.i("sample", "cliked");
	       startActivity(new Intent(this, SalesGirlsSearch.class));
	      // startActivity(new Intent(this, SearchViewActivity.class));
	   //	onSearchRequested();
	    /*   if(!condotion){
	       control_frame_layout.setVisibility(View.VISIBLE);
	       condotion=true;
	       }else {
	    	   control_frame_layout.setVisibility(View.GONE);
		       condotion=false;
		}*/
			break;
	
		case R.id.calc_clear_txt_Prise:
			//autoComplete_textview.setText("");
			break;
		case R.id.button_synch:

			startActivity(new Intent(this, Authenticate.class));
			finish();
			break;
		case R.id.button_takeorder:
			if (_firstname_et.length() <= 0) {
				_firstname_et.setError(null);

				_firstname_et.setError("enter first name");
			} else if (_lastname_et.length() <= 0) {
				_firstname_et.setError(null);
				_lastname_et.setError("enter last name");
			} else

			if (_telephonenumber_et.length() <= 0) {
				_lastname_et.setError(null);
				_telephonenumber_et.setError("enter telephone number");
			} else

			if (_cellphone_et.length() <= 0) {
				_telephonenumber_et.setError(null);
				_cellphone_et.setError("enter cell phone number");
			} else

			if (_company_et.length() <= 0) {
				_cellphone_et.setError(null);
				_company_et.setError("enter company");
			} else

			if (_emailid_et.length() <= 0) {
				_company_et.setError(null);
				_emailid_et.setError("enter email-id");
			} else

			if (_address_et.length() <= 0) {
				_emailid_et.setError(null);
				_address_et.setError(" enter address");
			} else

			if (_streetnumber_et.length() <= 0) {
				_address_et.setError(null);
				_streetnumber_et.setError("enter street number");
			} else

			if (_buildingnumber_et.length() <= 0) {
				_streetnumber_et.setError(null);
				_buildingnumber_et.setError("enter building number");
			} else

			if (_doornumber_et.length() <= 0) {
				_buildingnumber_et.setError(null);
				_doornumber_et.setError("enter Door number");
			} else

			if (_floornumber_et.length() <= 0) {
				_doornumber_et.setError(null);
				_floornumber_et.setError("enter floor number");
			} else if (_landmark_et.length() <= 0) {
				_floornumber_et.setError(null);
				_landmark_et.setError("enter landmark");
			} else if (_deliverydescription_et.length() <= 0) {
				_landmark_et.setError(null);
				_deliverydescription_et.setError("enter delivery description");
			} else {
				String S__emailid_et = _emailid_et.getText().toString();
				if (!isEmailAddressValid(S__emailid_et)) {
					_company_et.setError(null);
					_emailid_et.setError("enter valid email-id");

				} else {

					s_firstname_et = _firstname_et.getText().toString();
					s_lastname_et = _lastname_et.getText().toString();
					s_telephonenumber_et = _telephonenumber_et.getText()
							.toString();
					s_cellphone_et = _cellphone_et.getText().toString();
					s_company_et = _company_et.getText().toString();
					s_emailid_et = _emailid_et.getText().toString();
					s_address_et = _address_et.getText().toString();
					s_streetnumber_et = _streetnumber_et.getText().toString();
					s_buildingnumber_et = _buildingnumber_et.getText()
							.toString();
					s_doornumber_et = _doornumber_et.getText().toString();
					s_floornumber_et = _floornumber_et.getText().toString();
					s_landmark_et = _landmark_et.getText().toString();
					s_deliverydescription_et = _deliverydescription_et
							.getText().toString();
							s_name=s_firstname_et+" "+s_lastname_et;

					dh = new DataHelper(CustomerDetalsActivity.this);

					dh.open();
					territorynumber = territorynumber + 1;
					String territorykey = territoryString + territorynumber;
					long insertcheck = dh.addCustomerDetails(territorykey,
							s_firstname_et, s_lastname_et,s_name,
							s_telephonenumber_et, s_cellphone_et, s_company_et,
							s_emailid_et, s_address_et, s_streetnumber_et,
							s_buildingnumber_et, s_doornumber_et,
							s_floornumber_et, s_landmark_et,
							s_deliverydescription_et);
					dh.close();
					if (insertcheck != -1) {
						Toast.makeText(getApplicationContext(),
								"Customer Detail is Added Success Fully...",
								Toast.LENGTH_LONG).show();
						finish();

					} else if (insertcheck == -1) {

						Toast.makeText(getApplicationContext(),
								"Sorry your Detail is Not  Added...",
								Toast.LENGTH_LONG).show();

					}
					_firstname_et.setText("");
					_lastname_et.setText("");
					_telephonenumber_et.setText("");
					_cellphone_et.setText("");
					_company_et.setText("");
					_emailid_et.setText("");
					_address_et.setText("");
					_streetnumber_et.setText("");
					_buildingnumber_et.setText("");
					_doornumber_et.setText("");
					_floornumber_et.setText("");
					_landmark_et.setText("");
					_deliverydescription_et.setText("");

					startActivity(new Intent(this, TakeOrder.class));
				}

			}

			break;

		}

	}

}
