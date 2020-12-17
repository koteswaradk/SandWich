package com.yoobikwiti.sandwich.search;

import java.util.ArrayList;


import com.yoobikwiti.sandwich.CustomerOrderSessionManager;
import com.yoobikwiti.sandwich.LoginMain;
import com.yoobikwiti.sandwich.LoginSessionManager;
import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.SalesGirlsLogin;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.datahelper.DataHelper;
import com.yoobikwiti.sandwich.synchdata.SynchData;
import com.yoobikwiti.sandwich.takeorder.TakeOrder;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SalesGirlsSearch extends Activity implements OnCheckedChangeListener,
		OnClickListener {
   
	private ProgressDialog progressDialog;
	RadioGroup radioGroup1;
	String s_request_from,login_type="salesgirls";
	ListView searchlistview;
	public static final String KEY_Name = "name";
	public static final String KEY_CellPhoneNumber = "cellphonenumber";
	public static final String KEY_CompanyName = "companyname";
	public static final String KEY_Email_id = "emailid";
	private ArrayList<CustomerSearchModel> customerSearch = new ArrayList<CustomerSearchModel>();
	public static boolean flag = false, flag_data = false;
	TextView _emtytDisplayext;
	EditText serch_text;
	RadioButton radio_name, radio_phone, radio_company;
	CustomerSearchAdapter searchAdapter;
	Cursor c = null;
	CustomerOrderSessionManager customer_order_session;
	LoginSessionManager login_session_manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		customerSearch.clear();

		findViewById(R.id.button_add).setOnClickListener(this);
		serch_text = (EditText) findViewById(R.id.searc_et);
		_emtytDisplayext = (TextView) findViewById(R.id.empty);

		radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		searchlistview = (ListView) findViewById(R.id.searchlist);
		flag = getIntent().getBooleanExtra("inPremisis", false);

		findViewById(R.id.button_remove_text).setOnClickListener(this);

		radioGroup1.setOnCheckedChangeListener(this);
		radioGroup1.clearCheck();

		searchAdapter = new CustomerSearchAdapter(SalesGirlsSearch.this, customerSearch);
		searchlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 String user_name = ((TextView) view.findViewById(R.id.user_name)).getText().toString();
				 String company_name = ((TextView) view.findViewById(R.id.company_name)).getText().toString();
				 String phone_number = ((TextView) view.findViewById(R.id.phone_number)).getText().toString();
				 String emailid_number = ((TextView) view.findViewById(R.id.emailid_number)).getText().toString();
				 
				 String territory=SalesGirlsLogin.s_territory;
			login_session_manager=new LoginSessionManager(getApplicationContext());
			customer_order_session=new CustomerOrderSessionManager(getApplicationContext());
			if (login_session_manager.isSalesGirlsLoggedIn()) {
				customer_order_session.createCustomerDetailsSession(user_name, phone_number, emailid_number, territory, login_type);
			}
			final Dialog dialog = new Dialog(SalesGirlsSearch.this);

            dialog.setContentView(R.layout.customalertdialog);
            dialog.setTitle("Please Selsect Your Option");

            Button btnupdate=(Button)dialog.findViewById(R.id.button_update_details);
            Button btntakeorder=(Button)dialog.findViewById(R.id.button_take_order);
            dialog.show();
            btnupdate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(SalesGirlsSearch.this, CustomerDetalsActivity.class));
				}
			});
            btntakeorder.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(SalesGirlsSearch.this, TakeOrder.class));
				}
			});
            
            
			
			Log.i("name", ""+user_name);
			Log.i("company_name", ""+company_name);
			Log.i("phone_number", ""+phone_number);
			Log.i("emailid_number", ""+emailid_number);
				
			}
		});

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if (flag) {
			findViewById(R.id.radiocompany).setEnabled(false);
			findViewById(R.id.radiocompany).setBackgroundColor(
					R.color.yellowlight);
			findViewById(R.id.radiocompany).setVisibility(View.GONE);

		}
		switch (checkedId) {
		case R.id.radioName:
			
			customerSearch.clear();
			if (serch_text.length() <= 0) {
				serch_text.setError(null);
				serch_text.setError("Enter The Detail To Search");
				radioGroup1.clearCheck();
			}else{
			
				new GetSearcByNameAsync(serch_text.getText().toString()).execute();	
				SynchData ss=new SynchData();
				ss.exportDB(serch_text.getText().toString());
			}

			break;
		case R.id.radioPhone:
			customerSearch.clear();
			if (serch_text.length() <= 0) {
				serch_text.setError(null);
				serch_text.setError("Enter The Detail To Search");
				radioGroup1.clearCheck();
			}else{
			
				new GetSearcByPhoneAsync(serch_text.getText().toString()).execute();				
			}

			break;
		case R.id.radiocompany:
			customerSearch.clear();
			
			if (serch_text.length() <= 0) {
				serch_text.setError(null);
				serch_text.setError("Enter The Detail To Search");
				radioGroup1.clearCheck();
			}else{
			
				new GetSearcByCompanyAsync(serch_text.getText().toString()).execute();				
			}
		
			break;

	
		}
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_add:
			startActivity(new Intent(this, CustomerDetalsActivity.class));
			finish();
			break;
		case R.id.button_remove_text:
			serch_text.setText("");
			customerSearch.clear();
			searchlistview.setAdapter(null);
			
			
			radioGroup1.clearCheck();
			

			break;

		}

	}
	public class GetSearcByPhoneAsync extends
	AsyncTask<Void, Void, ArrayList<CustomerSearchModel>>{

		String _cellPhoneNumber;
		public GetSearcByPhoneAsync(String cellPhoneNumber) {
			// TODO Auto-generated constructor stub
			_cellPhoneNumber=cellPhoneNumber;
			Log.i("Inside GetSearcByPhoneAsync Contructor", _cellPhoneNumber);
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(SalesGirlsSearch.this);
				progressDialog.setCancelable(false);
				progressDialog.setTitle("please wait...");
				progressDialog.show();
			} else {
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		}

		@Override
		protected ArrayList<CustomerSearchModel> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			DataHelper databasehelper = new DataHelper(SalesGirlsSearch.this);
			databasehelper.open();
			Cursor c= databasehelper.getAllCustomerDetailsByCellPhoneNumber(_cellPhoneNumber);
			
			Log.i("count of column", ""+c.getCount());
			
			
			if(c!=null && c.getCount()>0) {
			
				int nam = c.getColumnIndex(KEY_Name);
				int cellnumber = c.getColumnIndex(KEY_CellPhoneNumber);
				int company = c.getColumnIndex(KEY_CompanyName);
				int emailid = c.getColumnIndex(KEY_Email_id);
				flag_data = true;
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					CustomerSearchModel searchmodel = new CustomerSearchModel();
				searchmodel.setName(c.getString(nam));
				searchmodel.setCellphone(c.getString(cellnumber));
				searchmodel.setCompany(c.getString(company));
				searchmodel.setEmailid(c.getString(emailid));
				Log.i("Search", searchmodel.getName());
				Log.i("Search", searchmodel.getCellphone());
				Log.i("Search", searchmodel.getCompany());
				Log.i("Search", searchmodel.getEmailid());
				
				customerSearch.add(searchmodel);
				
				}
			
			}

			c.close();
			databasehelper.close();
			return customerSearch;
		}
		
		@Override
		protected void onPostExecute(ArrayList<CustomerSearchModel> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			radioGroup1.clearCheck();
			if (!flag_data) {
				_emtytDisplayext.setVisibility(View.VISIBLE);
				searchlistview.setAdapter(null);
				searchAdapter.notifyDataSetChanged();
				Toast.makeText(SalesGirlsSearch.this, "valid Pnone Number Please",
						Toast.LENGTH_SHORT).show();

			} else {
				_emtytDisplayext.setVisibility(View.VISIBLE);

				if (customerSearch != null) {
					_emtytDisplayext.setVisibility(View.GONE);
					searchlistview.setAdapter(searchAdapter);
					searchAdapter.notifyDataSetChanged();
				} else {
					searchlistview.setAdapter(null);

					searchlistview.setEmptyView(_emtytDisplayext);
					searchAdapter.notifyDataSetChanged();
					customerSearch.clear();

				}

			}
		}

	}
	public class GetSearcByCompanyAsync extends
	AsyncTask<Void, Void, ArrayList<CustomerSearchModel>>{

		String _company_name;
		public GetSearcByCompanyAsync(String company_name) {
			// TODO Auto-generated constructor stub
			_company_name=company_name;
			Log.i("Inside GetSearcByCompanyAsync Contructor", _company_name);
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(SalesGirlsSearch.this);
				progressDialog.setCancelable(false);
				progressDialog.setTitle("please wait...");
				progressDialog.show();
			} else {
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		}

		@Override
		protected ArrayList<CustomerSearchModel> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			DataHelper databasehelper = new DataHelper(SalesGirlsSearch.this);
			databasehelper.open();
			Cursor c= databasehelper.getAllCustomerDetailsByCompany(_company_name);
			
			Log.i("count of column", ""+c.getCount());
			c.getColumnCount();
			
			if(c!=null && c.getCount()>0) {
			
				int nam = c.getColumnIndex(KEY_Name);
				int cellnumber = c.getColumnIndex(KEY_CellPhoneNumber);
				int company = c.getColumnIndex(KEY_CompanyName);
				int emailid = c.getColumnIndex(KEY_Email_id);
				flag_data = true;
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					CustomerSearchModel searchmodel = new CustomerSearchModel();
				searchmodel.setName(c.getString(nam));
				searchmodel.setCellphone(c.getString(cellnumber));
				searchmodel.setCompany(c.getString(company));
				searchmodel.setEmailid(c.getString(emailid));
				Log.i("Search", searchmodel.getName());
				Log.i("Search", searchmodel.getCellphone());
				Log.i("Search", searchmodel.getCompany());
				Log.i("Search", searchmodel.getEmailid());
				
				customerSearch.add(searchmodel);
				
				}
			
			}

			c.close();
			databasehelper.close();
			return customerSearch;
		}
	
		@Override
		protected void onPostExecute(ArrayList<CustomerSearchModel> result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			radioGroup1.clearCheck();
			if (!flag_data) {
				_emtytDisplayext.setVisibility(View.VISIBLE);
				searchlistview.setAdapter(null);
				searchAdapter.notifyDataSetChanged();
				Toast.makeText(SalesGirlsSearch.this, "valid Pnone Number Please",
						Toast.LENGTH_SHORT).show();

			} else {
				_emtytDisplayext.setVisibility(View.VISIBLE);

				if (customerSearch != null) {
					_emtytDisplayext.setVisibility(View.GONE);
					searchlistview.setAdapter(searchAdapter);
					searchAdapter.notifyDataSetChanged();
				} else {
					searchlistview.setAdapter(null);

					searchlistview.setEmptyView(_emtytDisplayext);
					searchAdapter.notifyDataSetChanged();
					customerSearch.clear();

				}

			}
		}

	}

	public class GetSearcByNameAsync extends
			AsyncTask<Void, Void, ArrayList<CustomerSearchModel>> {

		String _name;

		public GetSearcByNameAsync(String name) {
			// TODO Auto-generated constructor stub
			_name = name;
			Log.i("display the name before database call", _name);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(SalesGirlsSearch.this);
				progressDialog.setCancelable(false);
				progressDialog.setTitle("please wait...");
				progressDialog.show();
			} else {
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		}

		@Override
		protected ArrayList<CustomerSearchModel> doInBackground(Void... params) {
			// TODO Auto-generated method stub

			DataHelper databasehelper = new DataHelper(SalesGirlsSearch.this);
			databasehelper.open();
			Cursor c= databasehelper.getAllCustomerDetailsByName(_name);
			
			Log.i("count of column", ""+c.getCount());
			c.getColumnCount();
			
			if(c!=null && c.getCount()>0) {
			
				int nam = c.getColumnIndex(KEY_Name);
				int cellnumber = c.getColumnIndex(KEY_CellPhoneNumber);
				int company = c.getColumnIndex(KEY_CompanyName);
				int emailid = c.getColumnIndex(KEY_Email_id);
				flag_data = true;
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					CustomerSearchModel searchmodel = new CustomerSearchModel();
				searchmodel.setName(c.getString(nam));
				searchmodel.setCellphone(c.getString(cellnumber));
				searchmodel.setCompany(c.getString(company));
				searchmodel.setEmailid(c.getString(emailid));
				Log.i("Search", searchmodel.getName());
				Log.i("Search", searchmodel.getCellphone());
				Log.i("Search", searchmodel.getCompany());
				Log.i("Search", searchmodel.getEmailid());
				
				customerSearch.add(searchmodel);
				
				}
			
			}

			c.close();
			databasehelper.close();
			return customerSearch;
		}

		@Override
		protected void onPostExecute(ArrayList<CustomerSearchModel> result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			radioGroup1.clearCheck();
			if (!flag_data) {
				_emtytDisplayext.setVisibility(View.VISIBLE);
				searchlistview.setAdapter(null);
				searchAdapter.notifyDataSetChanged();
				Toast.makeText(SalesGirlsSearch.this, "valid Name Please",
						Toast.LENGTH_SHORT).show();

			} else {
				_emtytDisplayext.setVisibility(View.VISIBLE);

				if (customerSearch != null) {
					_emtytDisplayext.setVisibility(View.GONE);
					searchlistview.setAdapter(searchAdapter);
					searchAdapter.notifyDataSetChanged();
				} else {
					searchlistview.setAdapter(null);

					searchlistview.setEmptyView(_emtytDisplayext);
					searchAdapter.notifyDataSetChanged();
					customerSearch.clear();

				}

			}
			

		}

	}
}
