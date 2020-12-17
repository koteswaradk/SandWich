package com.yoobikwiti.sandwich;

import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

public class SalesGirlsLogin extends Activity implements OnClickListener,
		OnItemSelectedListener {

	EditText _username_et, _password_et;
	Spinner _spiner_territory, _spiner_company;
	String s_username, s_password, samplename = "sand",
			samplepassword = "wich",samplerole="sales",s_usertype="salesGirls";
	public static String  s_territory;
	
	LoginSessionManager loginsession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salesgirls_login);

		_username_et = (EditText) findViewById(R.id.login_username_et);
		_password_et = (EditText) findViewById(R.id.login_password_et);

		_spiner_territory = (Spinner) findViewById(R.id.login_spiner_territory);

		findViewById(R.id.login_button).setOnClickListener(this);

		_spiner_territory.setOnItemSelectedListener(this);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.teritory, R.layout.spinner_layout);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		// Apply the adapter to the spinner
		_spiner_territory.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.login_button:
			
			  if (_username_et.length() <= 0) { _username_et.setError(null);
			  _username_et.setError("Enter the name");
			  
			  } else if (_password_et.length() <= 0) {
			  _username_et.setError(null);
			  _password_et.setError("Enter the passsword");
			  
			  } else {
			  
			  s_username = _username_et.getText().toString(); s_password =
			  _password_et.getText().toString(); if
			  (!s_username.equalsIgnoreCase(samplename)) {
			  _username_et.setError(null);
			  _username_et.setError("please check UserName");
			  
			  } else if (!s_password.equalsIgnoreCase(samplepassword)) {
			  _username_et.setError(null);
			  _password_et.setError("please check the password");
			  
			  } if (s_territory.equalsIgnoreCase("Please Select The Teritory"))
			  { ((TextView)_spiner_territory.getChildAt(0)).setError(
			  "check the spinner");
			  
			  LayoutInflater inflater = getLayoutInflater(); View layout =
			  inflater.inflate(R.layout.customtoast,(ViewGroup)
			  findViewById(R.id.toast_layout_root));
			  layout.setBackgroundColor(R.color.red); TextView text =
			  (TextView) layout.findViewById(R.id.text);
			  text.setText("select the teritory"); text.setTextSize(16); Toast
			  toast = new Toast(getApplicationContext());
			  toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			  toast.setDuration(Toast.LENGTH_LONG); toast.setView(layout);
			  toast.show();
			  
			  } else {
			  
			  if ((s_username.equalsIgnoreCase(samplename)) &&(s_password.equalsIgnoreCase(samplepassword))) {
			 
				  loginsession=new LoginSessionManager(getApplicationContext());
				  loginsession.createSalesGirlsLoginSession(s_username, s_password, null, s_territory, samplerole, s_usertype);
				  startActivity(new Intent(this, SalesGirlsSearch.class));
			  
			  } } }
			
			
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
