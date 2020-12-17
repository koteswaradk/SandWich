package com.yoobikwiti.sandwich;

import java.util.HashMap;

import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CustomerOrderSessionManager {

			// Shared Preferences

			SharedPreferences pref;

			// Editor for Shared preferences
			Editor editor;

			// Context
			Context _context;

			// Shared pref mode
			int PRIVATE_MODE = 0;
			private static final String PREF_NAME = "sandwichcustomerdetails";

			// All Shared Preferences Keys
			private static final String IS_STORED = "IsStoredIn";

		
			//userdetails 
			public static final String KEY_CUSTOMER_ID = "customerid";
			public static final String KEY_CUSTOMER_NAME = "customername";
			public static final String KEY_CELLPHONE_NUMBER = "cellphonenumber";
			public static final String KEY_EMAIL_ID = "email_id";
			public static final String KEY_COMPANY = "company";

			public static final String KEY_LOGIN_TERITORY = "territorycustomer";
			public static final String KEY_LOGIN_INPREMISIS_COMPANY = "inpremisiscompanycustomer";

			public static final String KEY_API_TOKEN = "userapitoken";
			
			public static final String KEY_USER_TYPE = "usertype";
			public CustomerOrderSessionManager(Context context){
				this._context = context;
				pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
				editor = pref.edit();
			}
			public void createCustomerDetailsSession(String name,String cellnumber,
					String emailid,String territory, String usertype){
			
				editor.putBoolean(IS_STORED, true);
				// Storing name in pref
				editor.putString(KEY_CUSTOMER_NAME, name);
				// Storing cellphone in pref
				editor.putString(KEY_CELLPHONE_NUMBER, cellnumber);
				// Storing email in pref
				editor.putString(KEY_EMAIL_ID, emailid);
				
				// Storing usertype in pref
				editor.putString(KEY_USER_TYPE, usertype);

				// commit changes
				editor.commit();
			} 
			public HashMap<String, String> getCustomerDetails(){
				HashMap<String, String> user = new HashMap<String, String>();
				// user name
				user.put(KEY_CUSTOMER_NAME, pref.getString(KEY_CUSTOMER_NAME, null));

				user.put(KEY_CELLPHONE_NUMBER, pref.getString(KEY_CELLPHONE_NUMBER, null));

				user.put(KEY_EMAIL_ID, pref.getString(KEY_EMAIL_ID, null));

				user.put(KEY_USER_TYPE, pref.getString(KEY_USER_TYPE, null));

				// return user
				return user;
			}
			public void clearCustomerDetails(){
				// Clearing all data from Shared Preferences
				editor.clear();
				editor.commit();

				// After logout redirect user to Loing Activity
				Intent i = new Intent(_context, SalesGirlsSearch.class);
				// Closing all the Activities
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				// Add new Flag to start new Activity
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				// Staring Login Activity
				_context.startActivity(i);
			}
}
