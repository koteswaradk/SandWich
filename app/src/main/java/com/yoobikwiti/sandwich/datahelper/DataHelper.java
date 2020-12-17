package com.yoobikwiti.sandwich.datahelper;

import java.util.ArrayList;

import com.yoobikwiti.sandwich.search.CustomerSearchModel;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataHelper {

	static final String DATABASE_NAME = "Sand.db";
	static final int DATABASE_VERSION = 1;

	static final String TABLENAME_1 = "customerdetailtable"; // ADD BIKE
																// PURCHASE
																// DETAILS
	static final String TABLENAME_2 = "territorytable"; // ADD NEW BIKE DETAILS
	static final String TABLENAME_3 = "logintable"; // ADD BIKE SALE DETAILS
	static final String TABLENAME_4 = "synchtable"; // ADD FILL UP DETAILS
	static final String TABLENAME_5 = "authenticatetable";// ADD EXPENSES
	static final String TABLENAME_6 = "sandwichordertable";// ADD DEPARTURE
															// DETAILS

	// territory coloumn
	public static final String KEY_TerritoryId = "territory_id";
	public static final String KEY_TerritoryName = "territory_name";

	// userdetail coloumn
	public static final String KEY_CustTerritorySerialNumber = "territoryserialnumber";
	public static final String KEY_FirstName = "firstname";
	public static final String KEY_LastName = "lastname";
	public static final String KEY_Name = "name";
	public static final String KEY_TelephoneNumber = "telephonenumber";
	public static final String KEY_CellPhoneNumber = "cellphonenumber";
	public static final String KEY_CompanyName = "companyname";
	public static final String KEY_Email_id = "emailid";
	public static final String KEY_Address = "address";
	public static final String KEY_StreetNumber = "streetnumber";
	public static final String KEY_BuildingNumber = "buildingnumber";
	public static final String KEY_DoorNumber = "doornumber";
	public static final String KEY_FloorNumber = "floornumber";
	public static final String KEY_LandMark = "landmark";
	public static final String KEY_DeliveryDescription = "delidescription";

	public static final String CUSTOMER_DETAILS = "CREATE TABLE IF NOT EXISTS "
			+ TABLENAME_1 + "("
			+ "territoryserialnumber VARCHAR, "
			+ "firstname VARCHAR, " + "lastname VARCHAR, "+ "name VARCHAR, "
			+ "telephonenumber INTEGER, " + "cellphonenumber INTEGER, "
			+ "companyname VARCHAR, " + "emailid VARCHAR, "
			+ "address VARCHAR, " + "streetnumber VARCHAR, "
			+ "buildingnumber VARCHAR, " + "doornumber VARCHAR, "
			+ "floornumber VARCHAR, " + "landmark VARCHAR, "
			+ "delidescription VARCHAR);";
	// login column

	public static final String KEY_LoginUserName = "username";
	public static final String KEY_LoginPassword = "password";

	// authenticate column
	public static final String KEY_Authenticate_securitikey = "securitykey";

	// ordertable column
	public static final String KEY_CurrentTime = "currenttime";
	public static final String KEY_CurrentDate = "currentdate";
	public static final String KEY_CellPhoneNumber_o = "cellphonenumber";
	public static final String KEY_deliveryDate = "deliverydate";
	public static final String KEY_deliveryTime = "deliverytime";

	public static final String KEY_BreadSelection = "breadselection";
	public static final String KEY_MeatSelection = "meatselection";
	public static final String KEY_CheeseSelecton = "cheeseselection";
	public static final String KEY_SeasoningSelection = "seasoningselection";
	public static final String KEY_VegitablesExclusion = "vegitablesexcusion";

	public static Context ourcontext;
	private static SQLiteDatabase mydatabase;
	private static MySandDataBase mysanddatabase;
	
/*	
	public static Context ourcontext;
	private static SQLiteDatabase mydatabase;
	private static BikeTrackermydatabase biketrackermydatabase;
	*/
	
	public DataHelper(Context context)
	{
		ourcontext=context;
		mysanddatabase=new MySandDataBase(context);
	}
	
	public DataHelper open() throws SQLException{
		// TODO Auto-generated method stub
		
		mydatabase=mysanddatabase.getWritableDatabase();	
		return this;
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		mysanddatabase.close();		
	}

	
	private static class MySandDataBase extends SQLiteOpenHelper {

		public MySandDataBase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CUSTOMER_DETAILS);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLENAME_1);

			onCreate(mydatabase);

		}

	}


	public long addCustomerDetails(String territoryserialnumber, String firstname, String lastname,String name,
			String telephonenumber, String cellphonenumber, String companyname,
			String emailid, String address, String streetnumber,
			String buildingnumber, String doornumber, String floornumber, String landmark,String delidescription)
			throws SQLiteConstraintException {

		ContentValues cv = new ContentValues();
		try {
			cv.put(KEY_CustTerritorySerialNumber, territoryserialnumber);
			cv.put(KEY_FirstName, firstname);
			cv.put(KEY_LastName, lastname);
			cv.put(KEY_Name, name);
			cv.put(KEY_TelephoneNumber, telephonenumber);
			cv.put(KEY_CellPhoneNumber, cellphonenumber);
			cv.put(KEY_CompanyName, companyname);
			cv.put(KEY_Email_id, emailid);
			cv.put(KEY_Address, address);
			cv.put(KEY_StreetNumber, streetnumber);
			cv.put(KEY_BuildingNumber, buildingnumber);
			cv.put(KEY_DoorNumber, doornumber);
			cv.put(KEY_FloorNumber, floornumber);
			cv.put(KEY_LandMark, landmark);
			cv.put(KEY_DeliveryDescription, delidescription);

		} catch (SQLiteConstraintException e) {
			// TODO: handle exception

		}
		return mydatabase.insert(TABLENAME_1, null, cv);
	}
	
	public  ArrayList<String> getCustomerDetailsByTelephoneNumber(String telephonenumber){
		
		
		ArrayList<String> customerdetails= new ArrayList<String>();
		
		
		Cursor c= mydatabase.query(TABLENAME_1,new String[] {KEY_CustTerritorySerialNumber,KEY_FirstName,KEY_LastName,
				KEY_TelephoneNumber,KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id,KEY_Address,KEY_StreetNumber,
				KEY_BuildingNumber,KEY_DoorNumber,KEY_FloorNumber,KEY_LandMark,KEY_DeliveryDescription}, KEY_TelephoneNumber + "= '" + telephonenumber + "'", null, null, null, null);
		
		 int territorynumber=c.getColumnIndex(KEY_CustTerritorySerialNumber);
		 int firstname=c.getColumnIndex(KEY_FirstName);
		 int lastname=c.getColumnIndex(KEY_LastName);
		 int telephonumber=c.getColumnIndex(KEY_TelephoneNumber);
		 int cellnumber=c.getColumnIndex(KEY_CellPhoneNumber);
		 int company=c.getColumnIndex(KEY_CompanyName);
		 int emailid=c.getColumnIndex(KEY_Email_id);
		 int address=c.getColumnIndex(KEY_Address);
		 int streetnumber=c.getColumnIndex(KEY_StreetNumber);
		 int buildingnymber=c.getColumnIndex(KEY_BuildingNumber);
		 int doornumber=c.getColumnIndex(KEY_DoorNumber);
		 int floornumber=c.getColumnIndex(KEY_FloorNumber);
		 int landnumber=c.getColumnIndex(KEY_LandMark);
		 int delidesription=c.getColumnIndex(KEY_DeliveryDescription);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	customerdetails.add(c.getString(territorynumber));
	    			customerdetails.add(c.getString(firstname));
	    			customerdetails.add(c.getString(lastname));
	    			customerdetails.add(c.getString(telephonumber));
	    			customerdetails.add(c.getString(cellnumber));
	    			customerdetails.add(c.getString(company));
	    			customerdetails.add(c.getString(emailid));
	    			customerdetails.add(c.getString(address));
	    			customerdetails.add(c.getString(streetnumber));
	    			customerdetails.add(c.getString(buildingnymber));
	    			customerdetails.add(c.getString(doornumber));
	    			customerdetails.add(c.getString(floornumber));
	    			customerdetails.add(c.getString(landnumber));
	    			customerdetails.add(c.getString(delidesription));
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return customerdetails;	
	}
public  ArrayList<String> getCustomerDetailsByCellNumber(String cellphonenumber){
		
		
		ArrayList<String> customerdetails= new ArrayList<String>();
		
		
		Cursor c= mydatabase.query(TABLENAME_1,new String[] {KEY_CustTerritorySerialNumber,KEY_FirstName,KEY_LastName,
				KEY_TelephoneNumber,KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id,KEY_Address,KEY_StreetNumber,
				KEY_BuildingNumber,KEY_DoorNumber,KEY_FloorNumber,KEY_LandMark,KEY_DeliveryDescription}, KEY_CellPhoneNumber + "= '" + cellphonenumber + "'", null, null, null, null);
		
		 int territorynumber=c.getColumnIndex(KEY_CustTerritorySerialNumber);
		 int firstname=c.getColumnIndex(KEY_FirstName);
		 int lastname=c.getColumnIndex(KEY_LastName);
		 int telephonenumber=c.getColumnIndex(KEY_TelephoneNumber);
		 int cellnumber=c.getColumnIndex(KEY_CellPhoneNumber);
		 int company=c.getColumnIndex(KEY_CompanyName);
		 int emailid=c.getColumnIndex(KEY_Email_id);
		 int address=c.getColumnIndex(KEY_Address);
		 int streetnumber=c.getColumnIndex(KEY_StreetNumber);
		 int buildingnymber=c.getColumnIndex(KEY_BuildingNumber);
		 int doornumber=c.getColumnIndex(KEY_DoorNumber);
		 int floornumber=c.getColumnIndex(KEY_FloorNumber);
		 int landnumber=c.getColumnIndex(KEY_LandMark);
		 int delidesription=c.getColumnIndex(KEY_DeliveryDescription);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	customerdetails.add(c.getString(territorynumber));
	    			customerdetails.add(c.getString(firstname));
	    			customerdetails.add(c.getString(lastname));
	    			customerdetails.add(c.getString(telephonenumber));
	    			customerdetails.add(c.getString(cellnumber));
	    			customerdetails.add(c.getString(company));
	    			customerdetails.add(c.getString(emailid));
	    			customerdetails.add(c.getString(address));
	    			customerdetails.add(c.getString(streetnumber));
	    			customerdetails.add(c.getString(buildingnymber));
	    			customerdetails.add(c.getString(doornumber));
	    			customerdetails.add(c.getString(floornumber));
	    			customerdetails.add(c.getString(landnumber));
	    			customerdetails.add(c.getString(delidesription));
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return customerdetails;	
	}

public  Cursor getCustomerDetailsByName(String name){
		

		return mydatabase.query(TABLENAME_1,new String[] {KEY_Name,
				KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id}, KEY_Name + "= '" + name + "'", null, null, null, null);
		
	
	}
public Cursor getAllCustomerDetailsByCompany(String company) {
	return mydatabase.query(true, TABLENAME_1, new String[] { KEY_Name,
			KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id }, KEY_CompanyName + " LIKE ?",
            new String[] {"%"+ company+ "%" }, null, null, null,
            null);
	 
	
}
public Cursor getAllCustomerDetailsByCellPhoneNumber(String cellPhoneNumber) {
	return mydatabase.query(true, TABLENAME_1, new String[] { KEY_Name,
			KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id }, KEY_CellPhoneNumber + " LIKE ?",
            new String[] {"%"+ cellPhoneNumber+ "%" }, null, null, null,
            null);
	 
	
}
public  Cursor getAllCustomerDetailsByName(String name){
		
	 
	/*mDb.query(true, DATABASE_NAMES_TABLE, new String[] { KEY_ROWID,
            KEY_NAME }, KEY_NAME + " LIKE ?",
            new String[] { filter+"%" }, null, null, null,
            null);*/
	/*mDb.query(true, DATABASE_NAMES_TABLE, new String[] { KEY_ROWID,
            KEY_NAME }, KEY_NAME + " LIKE ?",
            new String[] {"%"+ filter+ "%" }, null, null, null,
            null);*/
	
	
	Cursor c= mydatabase.query(true, TABLENAME_1, new String[] { KEY_Name,
			KEY_CellPhoneNumber,KEY_CompanyName,KEY_Email_id }, KEY_Name + " LIKE ?",
            new String[] {"%"+ name+ "%" }, null, null, null,
            null);
	 
		  
	
	/*int nam = c.getColumnIndex(KEY_Name);
	int cellnumber = c.getColumnIndex(KEY_CellPhoneNumber);
	int company = c.getColumnIndex(KEY_CompanyName);
	int emailid = c.getColumnIndex(KEY_Email_id);
	CustomerSearchModel searchmodel = new CustomerSearchModel();
	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
	{
		Search.flag_data = true;
		searchmodel.setName(c.getString(nam));
		searchmodel.setCellphone(c.getString(cellnumber));
		searchmodel.setCompany(c.getString(company));
		searchmodel.setEmailid(c.getString(emailid));
		Log.i("Search", searchmodel.getName());
		Log.i("Search", searchmodel.getCellphone());
		Log.i("Search", searchmodel.getCompany());
		Log.i("Search", searchmodel.getEmailid());
			
	}
	customerSearch.add(searchmodel);
	*/
	
	
	
	/*Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { SUBCATEGORY,MAIN_CATEGORY,PRODUCT_ID,
            PRODUCT_NAME,BRAND,PACKAGE_SIZE,PRICE}, SUBCATEGORY + " LIKE '%" + subcategory + "%'",
            null, null, null, null, null);*/
	
	/*return mydatabase.query(TABLENAME_1, new String[] { KEY_Name,KEY_CellPhoneNumber,KEY_CompanyName,
			KEY_Email_id}, KEY_Name + " LIKE '%" + name + "%'",
              null, null, null, null, null);*/
	
	 return c;

}

}
