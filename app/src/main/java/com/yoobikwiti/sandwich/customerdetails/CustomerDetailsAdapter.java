package com.yoobikwiti.sandwich.customerdetails;

import java.util.ArrayList;

import com.yoobikwiti.sandwich.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomerDetailsAdapter extends BaseAdapter {
	private ArrayList<CustomerDetailsModel> customerDEtailsData;
	private LayoutInflater layoutInflater;
	private Context _context;

	public CustomerDetailsAdapter(Context context,
			ArrayList<CustomerDetailsModel> listData) {
		this.customerDEtailsData = listData;

		_context = context;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return customerDEtailsData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return customerDEtailsData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (layoutInflater == null)
			layoutInflater = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.custom_user_details,
					null);
			holder = new ViewHolder();
			holder.customer_user_name = (TextView) convertView
					.findViewById(R.id.user_name);
			holder.customer_company_name = (TextView) convertView
					.findViewById(R.id.company_name);
			holder.customer_phone_number = (TextView) convertView
					.findViewById(R.id.phone_number);
			holder.customer_emailid_number = (TextView) convertView
					.findViewById(R.id.emailid_number);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final CustomerDetailsModel customDetailItem = (CustomerDetailsModel) customerDEtailsData
				.get(position);

		holder.customer_user_name.setText(customDetailItem.getName());
		holder.customer_company_name.setText(customDetailItem.getCompany());
		holder.customer_phone_number.setText(customDetailItem.getCellphone());
		holder.customer_emailid_number.setText(customDetailItem.getEmailid());
		return convertView;
	}

	static class ViewHolder {
		TextView customer_user_name;
		TextView customer_company_name;
		TextView customer_phone_number;
		TextView customer_emailid_number;
	}

}
