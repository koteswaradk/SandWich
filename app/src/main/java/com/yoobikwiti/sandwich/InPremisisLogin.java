package com.yoobikwiti.sandwich;

import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.search.InpremisisSearch;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class InPremisisLogin extends Activity implements OnClickListener, OnItemSelectedListener{
	EditText _username_et, _password_et;
	Spinner  _spiner_company;
	String s_username, s_password, samplename = "sand",
			samplepassword = "wich", s_territory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inpremisislogin);
		_username_et = (EditText) findViewById(R.id.inpremisis_login_username_et);
		_password_et = (EditText) findViewById(R.id.inpremisis_login_password_et);

		_spiner_company = (Spinner) findViewById(R.id.login_spiner_company);

		findViewById(R.id.inPremisis_login_button).setOnClickListener(this);

		_spiner_company.setOnItemSelectedListener(this);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.teritorry_values, R.layout.spinner_layout);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		// Apply the adapter to the spinner
		_spiner_company.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.inPremisis_login_button:
			/*
			 * if (_username_et.length() <= 0) { _username_et.setError(null);
			 * _username_et.setError("Enter the name");
			 * 
			 * } else if (_password_et.length() <= 0) {
			 * _username_et.setError(null);
			 * _password_et.setError("Enter the passsword");
			 * 
			 * } else {
			 * 
			 * s_username = _username_et.getText().toString(); s_password =
			 * _password_et.getText().toString(); if
			 * (!s_username.equalsIgnoreCase(samplename)) {
			 * _username_et.setError(null);
			 * _username_et.setError("please check UserName");
			 * 
			 * } else if (!s_password.equalsIgnoreCase(samplepassword)) {
			 * _username_et.setError(null);
			 * _password_et.setError("please check the password");
			 * 
			 * } if (s_territory.equalsIgnoreCase("Please Select The Company"))
			 * { ((TextView)_spiner_territory.getChildAt(0)).setError(
			 * "check the spinner");
			 * 
			 * LayoutInflater inflater = getLayoutInflater(); View layout =
			 * inflater.inflate(R.layout.customtoast,(ViewGroup)
			 * findViewById(R.id.toast_layout_root));
			 * layout.setBackgroundColor(R.color.red); TextView text =
			 * (TextView) layout.findViewById(R.id.text);
			 * text.setText("select the teritory"); text.setTextSize(16); Toast
			 * toast = new Toast(getApplicationContext());
			 * toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			 * toast.setDuration(Toast.LENGTH_LONG); toast.setView(layout);
			 * toast.show();
			 * 
			 * } else {
			 * 
			 * if ((s_username.equalsIgnoreCase(samplename)) &&
			 * (s_password.equalsIgnoreCase(samplepassword))) {
			 */

			/*
			 * 
			 * } } }
			 */
			Intent companydetails=new Intent(this, InpremisisSearch.class);
			
			companydetails.putExtra("inPremisis", true);
			startActivity((companydetails));
			break;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		s_territory = parent.getItemAtPosition(position).toString();

		Log.i(s_territory, "selected value");

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		// parent.setTextDirection(R.string.teritory);

	}

}
