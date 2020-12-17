package com.yoobikwiti.sandwich.takeorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




import com.yoobikwiti.sandwich.R;
import com.yoobikwiti.sandwich.R.array;
import com.yoobikwiti.sandwich.customerdetails.CustomerDetalsActivity;
import com.yoobikwiti.sandwich.placeorchangeorder.PlaceOrChangeOrder;
import com.yoobikwiti.sandwich.search.SalesGirlsSearch;
import com.yoobikwiti.sandwich.takeorder.MultiSpinner.MultiSpinnerListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class TakeOrder extends Activity implements OnItemSelectedListener,OnClickListener,MultiSpinnerListener{
	
	Spinner spinner_breadtype_item,spinner_meattype_item,spinner_cheesetype_item,spinner_saasontype_item;
	String s_spinner1,s_spinner2,s_spinner3,s_spinner4,s_spinner5;
	List<String> list;
	MultiSpinner multiSelectionSpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.take_order);
		 list = new ArrayList<String>();
		 list.addAll(Arrays.asList(getResources().getStringArray(R.array.Vegetable_sandwich)));
	       
	        spinner_breadtype_item=(Spinner) findViewById(R.id.breadtype_item);
	        spinner_meattype_item=(Spinner)findViewById(R.id.meattype_item);
	        spinner_cheesetype_item=(Spinner)findViewById(R.id.cheesetype_item);
	        spinner_saasontype_item=(Spinner)findViewById(R.id.saasontype_item);
		
		multiSelectionSpinner = (MultiSpinner) findViewById(R.id.multiSpinner);
		
		findViewById(R.id.button_placeorder).setOnClickListener(this);
		findViewById(R.id.button_home).setOnClickListener(this);
		
		spinner_breadtype_item.setOnItemSelectedListener(this);
		spinner_meattype_item.setOnItemSelectedListener(this);
		spinner_cheesetype_item.setOnItemSelectedListener(this);
		spinner_saasontype_item.setOnItemSelectedListener(this);
		
		
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.teritorry_values,R.layout.spinner_layout);
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		spinner_breadtype_item.setAdapter(adapter);
		
		
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.teritorry_values,R.layout.spinner_layout);
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		spinner_meattype_item.setAdapter(adapter1);
		
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.teritorry_values,R.layout.spinner_layout);
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		spinner_cheesetype_item.setAdapter(adapter2);
		
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.teritorry_values,R.layout.spinner_layout);
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		spinner_saasontype_item.setAdapter(adapter3);
		
		/*ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.teritorry_values,android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		// Apply the adapter to the spinner
		spinner5.setAdapter(adapter4);*/
		
			multiSelectionSpinner.setItems(list, getString(R.string.app_name), new MultiSpinnerListener() {
			
			@Override
			public void onItemsSelected(boolean[] selected) {
				ArrayList<String> data=new ArrayList<String>();
				// your operation with code...
				for(int i=0; i<selected.length; i++) {
					if(selected[i]) {
						//Log.i("TAG", i + " : "+ list.get(i));
						
						data.add(list.get(i));

					}
					
				}
				Log.i("TAG", data+"");
			}
		});
		
		
		
	}
	

	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.breadtype_item:
		
			s_spinner1=parent.getItemAtPosition(position).toString();
			break;
		case R.id.meattype_item:
			
			s_spinner2=parent.getItemAtPosition(position).toString();
			break;
		case R.id.cheesetype_item:
			 
			s_spinner3=parent.getItemAtPosition(position).toString();
			break;
		case R.id.saasontype_item:
			 
			s_spinner4=parent.getItemAtPosition(position).toString();
			break;
		
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_home:
			startActivity(new Intent(this, SalesGirlsSearch.class));
			
			break;
		case R.id.button_placeorder:
			Toast.makeText(this, ""+s_spinner1+""+s_spinner2+""+s_spinner3+""+s_spinner4+""+s_spinner5, Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, PlaceOrChangeOrder.class));
			
			
			break;

		
		}
		
		
	}

	@Override
	public void onItemsSelected(boolean[] selected) {
		// TODO Auto-generated method stub
		
	}

	

	

}
