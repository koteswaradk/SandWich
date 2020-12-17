package com.yoobikwiti.sandwich.synchdata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.datahelper.DataHelper;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

public class SynchData extends Activity implements OnClickListener{
	
	EditText _authenticate_et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_syncdata);
		findViewById(R.id.button_home).setOnClickListener(this);
		findViewById(R.id.button_synch).setOnClickListener(this);

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
		case R.id.button_synch:
			LayoutInflater inflater = getLayoutInflater();
			 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));
			 layout.setBackgroundColor(R.color.red);
			 TextView text = (TextView) layout.findViewById(R.id.text);
			 text.setText("your data is synched");
			 text.setTextSize(16);
			 Toast toast = new Toast(getApplicationContext());
			 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			 toast.setDuration(Toast.LENGTH_LONG);
			 toast.setView(layout);
			 toast.show();
			break;

		
		}
		
	}
	public void exportDB(String input) {

        File dbFile=getDatabasePath("MyDBName.db");
        DataHelper dbhelper = new DataHelper(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            DataHelper db = dbhelper.open();
           // Cursor curCSV = db.rawQuery("SELECT * FROM contacts",null);
            Cursor curCSV = db.getAllCustomerDetailsByName(input);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
	/*//new async task for file export to csv
	private class ExportDatabaseCSVTask extends AsyncTask<String, String, Boolean> {
	    private final ProgressDialog dialog = new ProgressDialog(SynchData.this);
	    boolean memoryErr = false;

	    // to show Loading dialog box
	    @Override
	    protected void onPreExecute() {
	        this.dialog.setMessage("Exporting database...");
	        this.dialog.show();
	    }

	    // to write process 
	    protected Boolean doInBackground(final String... args) {

	        boolean success = false;

	        String currentDateString = new SimpleDateFormat(Constants.SimpleDtFrmt_ddMMyyyy).format(new Date());

	        File dbFile = getDatabasePath("HLPL_FRETE.db");
	        Log.v(TAG, "Db path is: " + dbFile); // get the path of db
	        File exportDir = new File(Environment.getExternalStorageDirectory() + File.separator + Constants.FileNm.FILE_DIR_NM, "");

	        long freeBytesInternal = new File(getApplicationContext().getFilesDir().getAbsoluteFile().toString()).getFreeSpace();           
	        long megAvailable = freeBytesInternal / 1048576;

	        if (megAvailable < 0.1) {               
	            System.out.println("Please check"+megAvailable);
	            memoryErr = true;               
	        }else {             
	            exportDirStr = exportDir.toString();// to show in dialogbox
	            Log.v(TAG, "exportDir path::" + exportDir);
	            if (!exportDir.exists()) {
	                exportDir.mkdirs();
	            }   
	            try {
	                List<SalesActivity> listdata = salesLst;
	                SalesActivity sa = null;
	                String lob = null;
	                for (int index = 0; index < listdata.size();) {
	                    sa = listdata.get(index);
	                    lob = sa.getLob();
	                    break;
	                }
	                if (Constants.Common.OCEAN_LOB.equals(lob)) {

	                    file = new File(exportDir, Constants.FileNm.FILE_OFS + currentDateString + ".csv");
	                } else {
	                    file = new File(exportDir, Constants.FileNm.FILE_AFS + currentDateString + ".csv");
	                }
	                file.createNewFile();
	                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));


	                // this is the Column of the table and same for Header of CSV
	                // file
	                if (Constants.Common.OCEAN_LOB.equals(lob)) {
	                    csvWrite.writeNext(Constants.FileNm.CSV_O_HEADER);
	                }else{
	                    csvWrite.writeNext(Constants.FileNm.CSV_A_HEADER);
	                }
	                String arrStr1[] = { "SR.No", "CUTSOMER NAME", "PROSPECT", "PORT OF LOAD", "PORT OF DISCHARGE" };
	                csvWrite.writeNext(arrStr1);

	                if (listdata.size() > 0) {
	                    for (int index = 0; index < listdata.size(); index++) {
	                        sa = listdata.get(index);
	                        String pol;
	                        String pod;
	                        if (Constants.Common.OCEAN_LOB.equals(sa.getLob())) {
	                            pol = sa.getPortOfLoadingOENm();
	                            pod = sa.getPortOfDischargeOENm();
	                        } else {
	                            pol = sa.getAirportOfLoadNm();
	                            pod = sa.getAirportOfDischargeNm();
	                        }
	                        int srNo = index;
	                        String arrStr[] = { String.valueOf(srNo + 1), sa.getCustomerNm(), sa.getProspectNm(), pol, pod };
	                        csvWrite.writeNext(arrStr);
	                    }
	                    success = true;
	                }
	                csvWrite.close();

	            } catch (IOException e) {
	                Log.e("SearchResultActivity", e.getMessage(), e);
	                return success;
	            }
	        }
	        return success;
	    }

	    // close dialog and give msg
	    protected void onPostExecute(Boolean success) {
	        if (this.dialog.isShowing()) {
	            this.dialog.dismiss();
	        }
	        if (success) {
	            dialogBox(Constants.Flag.FLAG_EXPRT_S);
	        } else {                
	            if (memoryErr==true) {
	                dialogBox(Constants.Flag.FLAG_MEMORY_ERR);
	            } else {
	                dialogBox(Constants.Flag.FLAG_EXPRT_F);
	            }
	        }
	    }
	}*/
	}
}
