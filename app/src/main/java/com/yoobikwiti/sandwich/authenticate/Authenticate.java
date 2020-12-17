package com.yoobikwiti.sandwich.authenticate;

import com.yoobikwiti.sandwich.LoginSessionManager;
import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;
import com.yoobikwiti.sandwich.synchdata.SynchData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Authenticate extends Activity implements OnClickListener{
	EditText _authenticate_et;
	String s_authenticate_et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_authenticate);
		findViewById(R.id.button_home).setOnClickListener(this);
		findViewById(R.id.button_autheticae).setOnClickListener(this);
		
		_authenticate_et=(EditText) findViewById(R.id.authenticate_et);
		
		
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
		case R.id.button_autheticae:
			if (_authenticate_et.length() <= 0) {
				_authenticate_et.setError(null);
				_authenticate_et.setError("Enter the autheticate code");
				
			}else{
			s_authenticate_et=_authenticate_et.getText().toString();
			LayoutInflater inflater = getLayoutInflater();
			 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));
			 layout.setBackgroundColor(R.color.red);
			 TextView text = (TextView) layout.findViewById(R.id.text);
			 text.setText("authenticating please wait");
			 text.setTextSize(16);
			 Toast toast = new Toast(getApplicationContext());
			 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			 toast.setDuration(Toast.LENGTH_LONG);
			 toast.setView(layout);
			 toast.show();
			 LoginSessionManager login_session_manager=new LoginSessionManager(getApplicationContext());
			 login_session_manager.logoutUser();
			 startActivity(new Intent(this, SynchData.class));
			break;

			}
		}
		
	
	}

}
