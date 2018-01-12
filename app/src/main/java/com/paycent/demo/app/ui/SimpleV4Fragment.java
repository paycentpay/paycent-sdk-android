package com.paycent.demo.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paycent.demo.app.R;
import com.paycent.demo.app.v4.App4Activity;


public class SimpleV4Fragment extends Fragment {

	App4Activity orderActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity = (App4Activity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.title_fragment_old);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_oldapp, parent, false);

		Button button = (Button) v.findViewById(R.id.button_pay);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				orderActivity.pay();
			}
		});

		return v;
	}


}
