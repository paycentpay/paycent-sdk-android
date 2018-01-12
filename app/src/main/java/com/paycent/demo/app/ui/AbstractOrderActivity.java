package com.paycent.demo.app.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.paycent.demo.app.model.Configuration;
import com.paycent.demo.app.model.Order;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.android.sdk.SdkPayRequest;
import com.paycent.android.sdk.SdkPayResult;
import com.paycent.android.sdk.SdkPayTask;
import com.paycent.android.sdk.SdkPayTaskFactory;

import java.util.List;

public abstract class AbstractOrderActivity extends SingleFragmentActivity {

	String TAG = this.getClass().getSimpleName();

	OrderFactory orderFactory;

	Configuration configuration;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		orderFactory = new OrderFactory();

		configuration = new Configuration(this);

		super.onCreate(savedInstanceState);
	}

	public List<Order> getOrders() {
		return orderFactory.getOrders();
	}

	private void showNewFragment(Fragment fragment) {

		try{

			FragmentManager fm = getFragmentManager();

			FragmentTransaction ft = fm.beginTransaction();

			ft.replace(getFragmentResId(), fragment);

			ft.commitAllowingStateLoss();

		}catch(Throwable tr){
			Log.i(TAG, tr.toString());
		}
	}

	public void showOrderResultFragment(SdkPayResult result){
		showNewFragment(OrderResultFragment.newInstance(result) );
	}

	public void showOrderReqRecyclerFragment(){
		showNewFragment(new OrderReqRecyclerFragment() );
	}


	private Handler mHandler = new Handler() {


		public void handleMessage(Message msg) {

			SdkPayResult result = (SdkPayResult) msg.obj;

			Log.d(TAG, result.toString() );

			showOrderResultFragment(result);
		}
	};

	public void pay() {


		final SdkPayRequest req = configuration.createSdkRequest();

		req.setOrderNo(String.valueOf(System.currentTimeMillis()));
		req.setAmount("5000");
		req.setCurrency("360");
		req.setProductName("SDK Game Card");
		req.setMp("");
		req.setOrderPeriod("30");
		req.setPayerLoginName("");

		SdkPayTask sdkTask = SdkPayTaskFactory.getInstance(this);

		sdkTask.pay(req, mHandler);
	}

}
