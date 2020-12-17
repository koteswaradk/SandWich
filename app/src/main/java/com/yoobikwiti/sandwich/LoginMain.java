package com.yoobikwiti.sandwich;

import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LoginMain extends Activity implements OnClickListener{
	LoginSessionManager session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginmain);
		 session=new LoginSessionManager(getApplicationContext());
		
		findViewById(R.id.button_inpremisis_login).setOnClickListener(this);
		findViewById(R.id.button_salesgirls_login).setOnClickListener(this);
		if (session.isLoggedIn()) {
			startActivity(new Intent(this, SalesGirlsSearch.class));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_salesgirls_login:
			startActivity(new Intent(this, SalesGirlsLogin.class));
			break;

		case R.id.button_inpremisis_login:
			startActivity(new Intent(this, InPremisisLogin.class));
			break;
		}
	}

}
