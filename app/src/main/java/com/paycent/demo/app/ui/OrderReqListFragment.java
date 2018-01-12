package com.paycent.demo.app.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.paycent.demo.app.R;
import com.paycent.demo.app.model.Order;

import java.util.List;

public class OrderReqListFragment extends ListFragment {

	AbstractOrderActivity orderActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity = (AbstractOrderActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.title_fragment_order_req_list);

		List<Order> orders = orderActivity.getOrders();

		OrdersAdapter adapter = new OrdersAdapter(orders);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_req_list, parent, false);

		Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
		orderActivity.setSupportActionBar(toolbar);
		orderActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				orderActivity.pay();
			}
		});
		return v;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Order o = ((OrdersAdapter) getListAdapter()).getItem(position);
		updateUI();
	}

	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}

	private class OrdersAdapter extends ArrayAdapter<Order> {

		public OrdersAdapter(List<Order> orders) {
			super(getActivity(), 0, orders);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_order, null);
			}
			// Configure the view for this Order
			Order o = getItem(position);

			TextView textView = (TextView) convertView.findViewById(R.id.order_list_title);
			textView.setText(o.getTitle() );

			textView = (TextView) convertView.findViewById(R.id.order_list_num);
			textView.setText(o.getNumber() );

			textView = (TextView) convertView.findViewById(R.id.order_list_amount);
			textView.setText(o.getAmount() );

			ImageView iconView = (ImageView) convertView.findViewById(R.id.order_list_icon);
			int resourceId = getResources().getIdentifier(o.getIconName(), "drawable", getActivity().getPackageName());
			iconView.setImageResource(resourceId);

			return convertView;
		}
	}

	public void updateUI() {
		((OrdersAdapter) getListAdapter()).notifyDataSetChanged();
	}

}
