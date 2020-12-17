package com.yoobikwiti.sandwich.takeorder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Spinner;

public class SingleSpinner extends Spinner  
{

	public SingleSpinner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	@Override 
    public boolean performClick() {
        new AlertDialog.Builder(getContext()).setAdapter((ListAdapter) getAdapter(), 
            new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    setSelection(which);
                    dialog.dismiss();
                }
            }).create().show();
        return true;
    }
	

	
}
