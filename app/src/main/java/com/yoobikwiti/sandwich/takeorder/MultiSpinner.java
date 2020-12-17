package com.yoobikwiti.sandwich.takeorder;

import java.util.List;


import com.yoobikwiti.sandwich.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MultiSpinner extends Spinner implements
		OnMultiChoiceClickListener, OnCancelListener {

	private List<String> items;
	private boolean[] selected;
	private String defaultText;
	private MultiSpinnerListener listener;
	Context _context;
	public interface MultiSpinnerListener {
		public void onItemsSelected(boolean[] selected);
	}

	public MultiSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		_context=context;
	}

	public MultiSpinner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		_context=context;
	}

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		// TODO Auto-generated method stub
		 if (isChecked)
	            selected[which] = true;
	        else
	            selected[which] = false;

	}
	

	@Override
	public void onCancel(DialogInterface dialog) {
		// refresh text on spinner
		StringBuffer spinnerBuffer = new StringBuffer();
		boolean someUnselected = false;
		for (int i = 0; i < items.size(); i++) {
			if (selected[i] == true) {
				spinnerBuffer.append(items.get(i));
				spinnerBuffer.append(", ");
			} else {
				someUnselected = true;
			}
		}
		String spinnerText;
		if (someUnselected) {
			spinnerText = spinnerBuffer.toString();
			if (spinnerText.length() > 2)
				spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
		} else {
			
			spinnerText = defaultText;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_layout,new String[]{ spinnerText });
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		setAdapter(adapter);
		listener.onItemsSelected(selected);
	}
	public void getSelectedvalues() {
		
	}

	@Override
	public boolean performClick() {
		
		
		
		//AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.DialogUplatform);
		
		builder.setTitle(R.string.selectsandwich);
		
		
		
		
		builder.setMultiChoiceItems(items.toArray(new CharSequence[items.size()]), selected, this);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.setOnCancelListener(this);
		builder.show();
		return true;
	}

	public void setItems(List<String> items, String allText,
			MultiSpinnerListener listener) {
		this.items = items;
		this.defaultText = allText;
		this.listener = listener;

		// all selected by default
		selected = new boolean[items.size()];
		for (int i = 0; i < selected.length; i++)
			selected[i] = true;

		// all text on the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
				R.layout.spinner_layout, new String[] { allText });
		setAdapter(adapter);
	}

}
