package com.yoobikwiti.sandwich;


import java.util.HashMap;

import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.MainThread;

public class LoginSessionManager {
	// Shared Preferences

		SharedPreferences pref;

		// Editor for Shared preferences
		Editor editor;

		// Context
		Context _context;

		// Shared pref mode
		int PRIVATE_MODE = 0;
		private static final String PREF_NAME = "sandwichlogin";

		// All Shared Preferences Keys
		private static final String IS_LOGIN = "IsLoggedIn";
		// All Shared Preferences Keys
		private static final String IS_SalesGirl = "IsSalesGirlLoggedIn";
		
		private static final String IS_InPremisis = "IsSalesGirlLoggedIn";

		// User name (make variable public to access from outside)
		public static final String KEY_NAME = "username";

		// Email address (make variable public to access from outside)
		public static final String KEY_PASSWORD = "password";
		//userdetails 
		public static final String KEY_CUSTOMER_ID = "customerid";
		public static final String KEY_CUSTOMER_NAME = "customername";
		public static final String KEY_CELLPHONE_NUMBER = "cellphonenumber";
		public static final String KEY_EMAIL_ID = "email_id";
		public static final String KEY_COMPANY = "company";

		public static final String KEY_LOGIN_ID = "userid";
		public static final String KEY_USER_TYPE = "usertype";
		public static final String KEY_LOGIN_TERITORY = "keyterritory";
		public static final String KEY_LOGIN_INPREMISIS_COMPANY = "keyinpremisiscompany";

		public static final String KEY_API_TOKEN = "userapitoken";
		
		public static final String KEY_ROLE = "userrole";
		public LoginSessionManager(Context context){
			this._context = context;
			pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
			editor = pref.edit();
		}
		public void createSalesGirlsLoginSession(String name, String password , String LoginId,
				String territory,String role, String usertype){
			// Storing login value as TRUE
			editor.putBoolean(IS_LOGIN, true);

			editor.putBoolean(IS_SalesGirl, true);
			// Storing name in pref
			editor.putString(KEY_NAME, name);

			// Storing email in pref
			editor.putString(KEY_PASSWORD, password);

			editor.putString(KEY_LOGIN_ID, LoginId);

			editor.putString(KEY_LOGIN_TERITORY, territory);
			
			/*editor.putString(KEY_ROLE, role);*/

			editor.putString(KEY_USER_TYPE, usertype);

			// commit changes
			editor.commit();
		} 
		public void createInpremisisLoginSession(String name, String password , String LoginId,
				String inpremisiscompany,String role, String usertype){
			// Storing login value as TRUE
			editor.putBoolean(IS_LOGIN, true);

			editor.putBoolean(IS_InPremisis, true);
			// Storing name in pref
			editor.putString(KEY_NAME, name);

			// Storing email in pref
			editor.putString(KEY_PASSWORD, password);

			editor.putString(KEY_LOGIN_ID, LoginId);

			editor.putString(KEY_LOGIN_INPREMISIS_COMPANY, inpremisiscompany);
			
			/*editor.putString(KEY_ROLE, role);*/

			editor.putString(KEY_USER_TYPE, usertype);

			// commit changes
			editor.commit();
		} 
		public void checkLogin(){
			// Check login status
			if(!this.isLoggedIn()){
				// user is not logged in redirect him to Login Activity
				Intent i = new Intent(_context, LoginMain.class);
				// Closing all the Activities
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				// Add new Flag to start new Activity
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				// Staring Login Activity
				_context.startActivity(i);
			}else{
				Intent i = new Intent(_context, SalesGirlsSearch.class);
				_context.startActivity(i);
			}

		}
		// Get Login State
		public boolean isLoggedIn(){
			return pref.getBoolean(IS_LOGIN, false);


		}
		public boolean isSalesGirlsLoggedIn(){
			return pref.getBoolean(IS_SalesGirl, false);


		}
		public boolean isInPremisisLOggedIn(){
			return pref.getBoolean(IS_InPremisis, false);


		}
		public void logoutUser(){
			// Clearing all data from Shared Preferences
			editor.clear();
			editor.commit();

			// After logout redirect user to Loing Activity
			Intent i = new Intent(_context, LoginMain.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}
		
		public HashMap<String, String> getInpremisisLoginDetails(){
			HashMap<String, String> user = new HashMap<String, String>();
			// user name
			user.put(KEY_NAME, pref.getString(KEY_NAME, null));

			user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

			user.put(KEY_LOGIN_ID, pref.getString(KEY_LOGIN_ID, null));

			user.put(KEY_LOGIN_INPREMISIS_COMPANY, pref.getString(KEY_LOGIN_INPREMISIS_COMPANY, null));

			user.put(KEY_USER_TYPE, pref.getString(KEY_USER_TYPE, null));

			
			// return user
			return user;
		}
		public HashMap<String, String> getSalesGirlsLoginDetails(){
			HashMap<String, String> user = new HashMap<String, String>();
			// user name
			user.put(KEY_NAME, pref.getString(KEY_NAME, null));

			user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

			user.put(KEY_LOGIN_ID, pref.getString(KEY_LOGIN_ID, null));

			user.put(KEY_LOGIN_TERITORY, pref.getString(KEY_LOGIN_TERITORY, null));

			user.put(KEY_USER_TYPE, pref.getString(KEY_USER_TYPE, null));

			
			// return user
			return user;
		}
		
}
