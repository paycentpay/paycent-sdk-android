package com.paycent.demo.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paycent.demo.app.R;

public class OrderReqRecyclerFragment extends Fragment {

	private AbstractOrderActivity orderActivity;

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity = (AbstractOrderActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.title_fragment_order_req_fragment);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_req_recycler, parent, false);

		Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
		orderActivity.setSupportActionBar(toolbar);
//		orderActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				orderActivity.pay();
			}
		});

		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerContent);

		mLayoutManager = new LinearLayoutManager(getActivity() );

		mRecyclerView.setLayoutManager(mLayoutManager);

		mAdapter = new OrderAdapter(getActivity(), orderActivity.getOrders());
		mRecyclerView.setAdapter(mAdapter);

		return rootView;
	}

}
